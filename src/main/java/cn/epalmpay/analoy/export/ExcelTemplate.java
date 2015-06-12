package cn.epalmpay.analoy.export;

public class ExcelTemplate {
	public static String body = "$worksheet:{\n" + "	<Worksheet ss:Name=\"$it.sheet$\">\n" + "	<Table ss:ExpandedColumnCount=\"$it.columnNum$\" ss:ExpandedRowCount=\"$it.rowNum$\" x:FullColumns=\"1\"\n" + "		x:FullRows=\"1\" ss:DefaultColumnWidth=\"54\" ss:DefaultRowHeight=\"14.25\">\n" + "	$it.rows:{\n" + "		<Row>\n" + "			<Cell><Data ss:Type=\"String\">$it.col1$</Data></Cell>\n" + "		</Row>\n" + "	}$\n" + "}$";

	public static String row = "$worksheet:{\n" + "	$it.rows:{\n" + "		<Row>\n" + "			<Cell><Data ss:Type=\"String\">$it.col1$</Data></Cell>\n" + "		</Row>\n" + "	}$\n" + "}$\n";

	public static String head = "<?xml version=\"1.0\" encoding='gbk'?>\n" + "<?mso-application progid=\"Excel.Sheet\"?>\n" + "<Workbook xmlns=\"urn:schemas-microsoft-com:office:spreadsheet\"\n" + "xmlns:o=\"urn:schemas-microsoft-com:office:office\"\n" + "xmlns:x=\"urn:schemas-microsoft-com:office:excel\"\n" + "xmlns:ss=\"urn:schemas-microsoft-com:office:spreadsheet\"\n" + "xmlns:html=\"http://www.w3.org/TR/REC-html40\">\n" + "<DocumentProperties xmlns=\"urn:schemas-microsoft-com:office:office\">\n" + "<Created>1996-12-17T01:32:42Z</Created>\n" + "<LastSaved>2013-08-02T09:21:24Z</LastSaved>\n" + "<Version>11.9999</Version>\n" + "</DocumentProperties>\n" + "<OfficeDocumentSettings xmlns=\"urn:schemas-microsoft-com:office:office\">\n" + "<RemovePersonalInformation/>\n"
			+ "</OfficeDocumentSettings>\n" + "<ExcelWorkbook xmlns=\"urn:schemas-microsoft-com:office:excel\">\n" + "<WindowHeight>4530</WindowHeight>\n" + "<WindowWidth>8505</WindowWidth>\n" + "<WindowTopX>480</WindowTopX>\n" + "<WindowTopY>120</WindowTopY>\n" + "<AcceptLabelsInFormulas/>\n" + "<ProtectStructure>False</ProtectStructure>\n" + "<ProtectWindows>False</ProtectWindows>\n" + "</ExcelWorkbook>\n" + "<Styles>\n" + "<Style ss:ID=\"Default\" ss:Name=\"Normal\">\n" + "<Alignment ss:Vertical=\"Bottom\"/>\n" + "<Borders/>\n" + "<Font ss:FontName=\"宋体\" x:CharSet=\"134\" ss:Size=\"12\"/>\n" + "<Interior/>\n" + "<NumberFormat/>\n" + "<Protection/>\n" + "</Style>\n" + "</Styles>\n";

}
