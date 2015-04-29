package cn.epalmpay.analoy.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

public class POIUtils {

	private HSSFWorkbook wb = null;
	private HSSFSheet sheet = null;

	/**
	 * 创建表头
	 * 
	 * @param head表头
	 * @param col报表的头数
	 */
	public void createHead(String head, int col) {
		// 设置第一行
		HSSFRow row = sheet.createRow(0);
		HSSFCell cell = row.createCell(0);

		// 定义单元格为字符串类型
		cell.setCellType(HSSFCell.ENCODING_UTF_16);
		cell.setCellValue(head);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, col));
	}

	public POIUtils() {
		super();
	}

	public POIUtils(HSSFWorkbook wb, HSSFSheet sheet) {
		this.wb = wb;
		this.sheet = sheet;
	}

	public static void main(String[] args) {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(new SimpleDateFormat("yyyy-mm-dd").format(new Date()));
		new POIUtils(wb, sheet).createHead("这是一个测试", 5);
		System.out.println("测试成功");
	}
}
