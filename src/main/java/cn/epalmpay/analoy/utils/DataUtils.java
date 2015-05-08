package cn.epalmpay.analoy.utils;

import java.text.DecimalFormat;
import java.util.Random;

public class DataUtils {
	/**
	 * 产生一个随机数
	 * 
	 * @param n
	 * @return
	 */
	public static int generateInt(int n) {
		return new Random().nextInt(n);
	}

	/**
	 * 产生一个随机数
	 * 
	 * @param money
	 * @return
	 */
	public static String generateDouble(double money) {
		DecimalFormat format = new DecimalFormat("######0.0000");
		return format.format(new Random().nextDouble() * money);

	}

	/**
	 * 格式化,保留四位小数
	 * 
	 * @param d
	 * @return
	 */
	public static String format(double d) {
		return new DecimalFormat("######0.0000").format(d);
	}
}
