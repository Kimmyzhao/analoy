package cn.epalmpay.analoy.export;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.stringtemplate.StringTemplate;
import org.apache.log4j.Logger;

public class ExportExcelUtil {
	private static Logger logger = Logger.getLogger(ExportExcelUtil.class);

	private Map<String, String> updateTemplate(int rows) throws IOException {
		logger.debug("更新导出版本，行数：" + rows);
		Map<String, String> result = new HashMap<String, String>();
		StringBuffer body = new StringBuffer(ExcelTemplate.body);
		StringBuffer row = new StringBuffer(ExcelTemplate.row);
		body.delete(body.toString().indexOf("<Cell>"), body.toString().lastIndexOf("</Row>"));
		row.delete(row.toString().indexOf("<Cell>"), row.toString().lastIndexOf("</Row>"));
		StringBuffer template = new StringBuffer();
		for (int i = 0; i < rows; i++) {
			template.append("\n<Cell><Data ss:Type=\"String\">$it.col" + (i + 1) + "$</Data></Cell>");
		}
		template.append("\n");
		body.insert(body.toString().indexOf("<Row>") + 5, template.toString());
		row.insert(row.toString().indexOf("<Row>") + 5, template.toString());
		result.put("body", body.toString());
		result.put("row", row.toString());
		logger.debug(("更新导出模板成功"));
		return result;
	}

	/**
	 * 将数据导出到excel
	 * 
	 * @param beans
	 *            导出的每个sheet对应的实体类的Class
	 * @param file
	 *            导出的最终文件
	 * @param dataList
	 *            将要导出的数据，dataList中每一个List对应一个sheet
	 * @param sheetMaxRow
	 *            每个sheet最大的行数，如果dataList中的List中对象数超过此值，会自动的分成多个sheet
	 * @param cache
	 *            内存中最大保存的row数量，当超过此值时自动刷新到磁盘
	 * @throws IOException
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	public void export(List<Class> beans, File file, List<List> dataList, int sheetMaxRow, int cache) throws IOException {
		List<String> sheet = new ArrayList<String>();
		List<List<String>> methods = new ArrayList<List<String>>();
		List<List<String>> heads = new ArrayList<List<String>>();

		for (int i = 0; i < beans.size(); i++) {
			Class bean = beans.get(i);
			List<BeanAnnotation> beaninfo = BeanUtilsBean.parse(bean);
			sheet.add(beaninfo.get(0).sheetName);
			List<String> m = new ArrayList<String>();
			List<String> h = new ArrayList<String>();
			for (int j = 1; j < beaninfo.size(); j++) {
				m.add(beaninfo.get(j).methodName);
				h.add(beaninfo.get(j).head);
			}
			methods.add(m);
			heads.add(h);
		}

		if (dataList == null) {
			throw new ExportException("dataList为null");
		}
		if (sheet.size() != methods.size() || sheet.size() != dataList.size() || (heads != null && sheet.size() != heads.size()) || dataList.size() != methods.size() || (heads != null && dataList.size() != heads.size()) || (heads != null && methods.size() != heads.size())) {
			if (heads != null) {
				throw new ExportException("sheet,dataList,methods,heads中某两个长度不等!");
			} else {
				throw new ExportException("sheet,dataList,feild中某两个长度不等!");
			}
		}
		List<Integer> cols = new ArrayList<Integer>();
		for (int i = 0; i < methods.size(); i++) {
			cols.add(methods.get(i).size());
		}

		long startTimne = System.currentTimeMillis();

		// 写入excel文件头部信息
		
		StringTemplate head = new StringTemplate(ExcelTemplate.head);
		PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(file)));
		writer.print(head.toString());
		writer.flush();

		// 写入excel文件数据信息

		for (int i = 0; i < sheet.size(); i++) {
			List datas = dataList.get(i);
			List<String> ms = methods.get(i);
			List<String> sheetHead = null;
			if (heads != null) {
				sheetHead = heads.get(i);
			}
			Map<String, String> t = updateTemplate(cols.get(i));
			for (int k = 0; k < datas.size() / sheetMaxRow + 1; k++) {
				// 防止datas.size()刚好能整除sheetMaxRow的时候，多出一个空页

				if (datas.size() % sheetMaxRow == 0 && k == (datas.size() / sheetMaxRow)) {
					break;
				}
				StringTemplate body = new StringTemplate(t.get("body"));
				Worksheet worksheet = new Worksheet();
				if (k == 0) {
					worksheet.setSheet(sheet.get(i));
				} else {
					worksheet.setSheet(sheet.get(i) + k);
				}
				worksheet.setColumnNum(cols.get(i));
				worksheet.setRowNum(sheetMaxRow);
				List<Row> rows = new ArrayList<Row>();
				if (sheetHead != null) {
					rows.add(new Row().initHead(sheetHead));
				}
				if (k == datas.size() / sheetMaxRow) {
					for (int j = k * sheetMaxRow; j < datas.size(); j++) {
						Row row = new Row();
						rows.add(row.initByMethods(datas.get(j), ms));
						row = null;
						if (j % cache == 0) {
							worksheet.setRows(rows);
							body.setAttribute("worksheet", worksheet);
							// writer.print(body.toString());

							writer.append(body.toString());
							writer.flush();
							rows.clear();
							body = new StringTemplate(t.get("row"));
						}
					}
				} else {
					for (int j = k * sheetMaxRow; j < (k + 1) * sheetMaxRow; j++) {
						Row row = new Row();
						rows.add(row.initByMethods(datas.get(j), methods.get(i)));
						row = null;
						if (j % cache == 0) {
							worksheet.setRows(rows);
							body.setAttribute("worksheet", worksheet);
							// writer.print(body.toString());

							writer.append(body.toString());
							writer.flush();
							rows.clear();
							body = new StringTemplate(t.get("row"));
						}
					}
				}
				worksheet.setRows(rows);
				System.out.println("正在生成excel文件的 sheet: " + worksheet.getSheet());
				body.setAttribute("worksheet", worksheet);
				// writer.print(body.toString());

				writer.append(body.toString());
				writer.append("</Table></Worksheet>");
				writer.flush();
				rows.clear();
				rows = null;
				worksheet = null;
				body = null;
				Runtime.getRuntime().gc();
			}
		}

		// 写入excel文件尾部

		writer.print("</Workbook>");
		writer.flush();
		writer.close();
		System.out.println("生成excel文件完成");
		long endTime = System.currentTimeMillis();
		System.out.println("用时=" + ((endTime - startTimne) / 1000) + "秒");
	}

	@SuppressWarnings("rawtypes")
	public void export(Class bean, File file, List dataList, int sheetMaxRow, int cache) throws IOException {
		List<Class> beans = new ArrayList<Class>();
		beans.add(bean);
		List<List> datas = new ArrayList<List>();
		datas.add(dataList);
		export(beans, file, datas, sheetMaxRow, cache);
	}

}
