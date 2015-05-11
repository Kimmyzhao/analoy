package cn.epalmpay.analoy.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;

public class POIUtils {

	/**
	 * 创建表头
	 * 
	 * @param wb工作薄
	 * @param sheet工作表
	 * @param head表头
	 * @param index列索引
	 *            (从0开始,6表示第七列)<br/>
	 */
	public static void createHead(HSSFWorkbook wb, HSSFSheet sheet, String head, int index) {
		// 设置第一行
		HSSFRow row = sheet.createRow(0);
		HSSFCell cell = row.createCell(0);

		// 定义单元格为字符串类型
		cell.setCellType(HSSFCell.ENCODING_UTF_16);
		cell.setCellValue(head);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, index));

		// 定义单元格样式
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 设置单元格居中对齐
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 设置单元格垂直居中对齐
		style.setWrapText(true);// 指定单元格自动换行

		// 设置单元格字体
		HSSFFont font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("宋体");
		font.setFontHeight((short) 250);
		style.setFont(font);
		cell.setCellStyle(style);
	}

	/**
	 * 设置报表标题
	 * 
	 * @param columHeader标题字符串数组
	 */

	/**
	 * 设置报表标题
	 * 
	 * @param wb工作薄
	 * @param sheet工作表
	 * @param index行索引
	 *            (从0开始,6表示从第七行)
	 * @param columHeader
	 */
	public static void createTitle(HSSFWorkbook wb, HSSFSheet sheet, int index, String[] columHeader) {
		HSSFCell cell = null;
		if (columHeader != null && columHeader.length > 0) {
			HSSFCellStyle titleStyle = wb.createCellStyle();
			// 指定单元格居中对齐
			titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			// 指定单元格垂直居中对齐
			titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			// 指定当单元格内容显示不下时自动换行
			titleStyle.setWrapText(true);

			// 设置单元格字体
			HSSFFont font = wb.createFont();
			font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
			font.setFontName("宋体");
			font.setFontHeight((short) 200);
			titleStyle.setFont(font);

			HSSFRow row = sheet.createRow(index);// 得到Excel工作表的行

			for (int i = 0, j = columHeader.length; i < j; i++) {
				cell = row.createCell(i);// 得到Excel工作表指定行的单元格
				cell.setCellStyle(titleStyle);
				cell.setCellValue(new HSSFRichTextString(columHeader[i]));
			}
		}

	}

	/**
	 * 
	 * @param wb
	 * @param sheet
	 * @param row
	 * @param col
	 * @param val
	 */
	public static void createCell(HSSFWorkbook wb, HSSFSheet sheet, HSSFRow row, int col, String val) {
		HSSFCell cell = null;
		HSSFCellStyle style = wb.createCellStyle();
		// 指定单元格居中对齐
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 指定单元格垂直居中对齐
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 指定当单元格内容显示不下时自动换行
		style.setWrapText(false);
		// 设置单元格字体
		HSSFFont font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		font.setFontName("宋体");
		font.setFontHeight((short) 200);
		style.setFont(font);

		cell = row.createCell(col);
		cell.setCellStyle(style);
		cell.setCellValue(new HSSFRichTextString(val));
	}

	/**
	 * 
	 * @param wb
	 * @param sheet
	 * @param row
	 * @param col
	 * @param val
	 */
	public static void createCell(HSSFWorkbook wb, HSSFSheet sheet, HSSFRow row, int col, int val) {
		HSSFCell cell = null;
		HSSFCellStyle style = wb.createCellStyle();
		// 指定单元格居中对齐
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 指定单元格垂直居中对齐
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 指定当单元格内容显示不下时自动换行
		style.setWrapText(false);
		// 设置单元格字体
		HSSFFont font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		font.setFontName("宋体");
		font.setFontHeight((short) 200);
		style.setFont(font);

		cell = row.createCell(col);
		cell.setCellStyle(style);
		cell.setCellValue(new HSSFRichTextString(val + ""));
	}

	public static void main(String[] args) throws IOException {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(new SimpleDateFormat("yyyy-mm-dd").format(new Date()));
		createHead(wb, sheet, "生成EXCEL文件测试", 6);
		createTitle(wb, sheet, 3, new String[] { "测试A", "测试B", "测试C" });

		FileOutputStream os = new FileOutputStream("D:\\report\\workbook.xls");
		wb.write(os);
		os.close();
		System.out.println("测试成功");
	}

	/**
	 * 由于Excel当中的单元格Cell存在类型,若获取类型错误就会产生异常, 所以通过此方法将Cell内容全部转换为String类型
	 * 
	 * @param cell
	 * @return
	 */
	public static String getCellValue(Cell cell) {
		String str = null;
		if (cell != null) {
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_BLANK:
				str = "";
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				str = String.valueOf(cell.getBooleanCellValue());
				break;
			case Cell.CELL_TYPE_FORMULA:
				str = String.valueOf(cell.getCellFormula());
				break;
			case Cell.CELL_TYPE_NUMERIC:
				if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
					str = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(cell.getDateCellValue());
				} else {
					str = String.valueOf((long) cell.getNumericCellValue());
				}
				break;
			case Cell.CELL_TYPE_STRING:
				str = String.valueOf(cell.getStringCellValue());
				break;
			default:
				str = null;
				break;
			}
		}
		return str;
	}
}
