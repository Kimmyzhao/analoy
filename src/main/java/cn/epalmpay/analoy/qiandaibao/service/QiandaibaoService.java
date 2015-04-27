package cn.epalmpay.analoy.qiandaibao.service;

import java.io.File;
import java.io.FileNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import cn.epalmpay.analoy.qiandaibao.constant.Constant;
import cn.epalmpay.analoy.utils.FileUtils;

@Service
public class QiandaibaoService {

	public void sayHello() {
		System.out.println("Hello World!");
	}

	public String getTradeRecord() {
		File file = null;
		String result = "";
		try {
			file = ResourceUtils.getFile(Constant.FILE_QIANDAIBAO);
			// System.out.println(file.getPath());
			result = FileUtils.txt2String(file);
			System.out.println(result);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public String queryPosState() {
		File file = null;
		String result = "";
		try {
			file = ResourceUtils.getFile(Constant.FILE_QIANDAIBAO_QUERYPOS);
			// System.out.println(file.getPath());
			result = FileUtils.txt2String(file);
			System.out.println(result);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}

}
