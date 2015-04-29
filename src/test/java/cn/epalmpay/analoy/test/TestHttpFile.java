package cn.epalmpay.analoy.test;


import org.junit.Test;

import cn.epalmpay.analoy.utils.FileUtils;


public class TestHttpFile {

	/*@Test
	public void testHttpFile() throws HttpException, IOException {
		HttpFile.postHttp("http://127.0.0.1:8080/ZFTiming/api/zhonghui/orders/import/", "d:/zhonghui/file/", new File("d:/upload/file/2015-04-21.txt"));
	}*/
	
	@Test
	public void testReadFile(){
		FileUtils.readFile("d:/upload/file/2015-04-21.txt");
	}
}
