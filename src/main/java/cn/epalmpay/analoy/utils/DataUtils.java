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

	public static String getBankName(int index) {
		String bankName = "";
		switch (index) {
		case 1:
			bankName = "中国工商银行";
			break;
		case 2:
			bankName = "交通银行";
			break;
		case 3:
			bankName = "广发银行";
			break;
		case 4:
			bankName = "中国农业银行";
			break;
		case 5:
			bankName = "招商银行";
			break;
		case 6:
			bankName = "平安银行";
			break;
		case 7:
			bankName = "中国邮政储蓄银行";
			break;
		case 8:
			bankName = "中国建设银行";
			break;
		default:
			bankName = "中国银行";
			break;
		}
		return bankName;
	}

}
