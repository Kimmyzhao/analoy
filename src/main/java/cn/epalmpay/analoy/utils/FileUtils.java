package cn.epalmpay.analoy.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

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

	public static void parseFile(File file) throws Exception {
		try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
			String line = raf.readLine();
			while (line != null) {
				System.out.println(line);
				line = raf.readLine();
			}
		}

	}
	
	public static void main(String[] args) throws Exception {
		parseFile(new File("E:\\studyfile\\jquery-easyui-1.4\\readme.txt"));
	}
}
