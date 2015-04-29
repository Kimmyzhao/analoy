package cn.epalmpay.analoy.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;

public class FileUtils {
	public static String txt2String(File file) {
		String result = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));// 构造一个BufferedReader类来读取文件
			String s = null;
			while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
				result = result + s;
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String readFile(String path) {
		String filecontent = "";
		BufferedReader reader = null;
		try {
			File file = new File(path);
			if (file.isFile() && file.exists()) {
				reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
				String line = null;
				while ((line = reader.readLine()) != null) {
					filecontent += line + "\n";
				}
				reader.close();
			}
		} catch (Exception e) {
		}
		System.out.println(filecontent);
		return null;
	}
}
