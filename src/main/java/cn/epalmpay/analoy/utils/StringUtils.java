package cn.epalmpay.analoy.utils;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class StringUtils {
	private static final char[] chars = "1234567890abcdef".toCharArray();
	private static final Random random = new Random();

	public static String randomString(int length) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < length; ++i) {
			int pos = random.nextInt(chars.length);
			builder.append(chars[pos]);
		}
		return builder.toString();
	}

	/**
	 * 将目标字符串加密
	 * 
	 * @param target
	 *            : 要加密的字符串
	 * @param pattern
	 *            : 加密方式，如MD5，SHA等
	 * @return 加密后的字符串
	 */
	public static String encryption(String target, String pattern) {
		if (target == null || pattern == null || "".equals(target.trim()) || "".equals(pattern.trim())) {
			return "";
		} else {
			String re_md5 = null;
			try {
				MessageDigest md = MessageDigest.getInstance(pattern);
				md.update(target.getBytes());
				byte b[] = md.digest();

				int i;

				StringBuffer buf = new StringBuffer("");
				for (int offset = 0; offset < b.length; offset++) {
					i = b[offset];
					if (i < 0)
						i += 256;
					if (i < 16)
						buf.append("0");
					buf.append(Integer.toHexString(i));
				}

				re_md5 = buf.toString();

			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			return re_md5;

		}
	}

	/**
	 * 将json字符串转换成java对象
	 * 
	 * @param json
	 * @param object
	 * @return
	 */
	public static Object parseJSONStringToObject(String json, Object object) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(json, object.getClass());
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将json字符串转换成List
	 * 
	 * @param json
	 * @param object
	 * @return
	 */
	public static List<?> parseJSONStringToList(String json, Class<?> object) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(json, mapper.getTypeFactory().constructParametricType(List.class, object));
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将java对象转换成json字符串
	 * 
	 * @param object
	 * @return
	 */
	public static String parseObjectToJSONString(Object object) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static BigDecimal unitScale(String target) {
		if (target == null || "".equals(target.trim())) {
			return null;
		}
		return new BigDecimal(target).multiply(new BigDecimal("100"));
	}

	public static String toProSub(String cardno) {
		if (cardno.length() > 8) {
			int length = cardno.length();
			String s1 = cardno.substring(0, length - 8);
			String s2 = cardno.substring(length - 4, length);
			cardno = s1 + "****" + s2;
		}

		return cardno;
	}

	/**
	 * 格式化字符串
	 * 
	 * 例：formateString("xxx{0}bbb",1) = xxx1bbb
	 * 
	 * @param str
	 * @param params
	 * @return
	 */
	public static String formateString(String str, String... params) {
		for (int i = 0; i < params.length; i++) {
			str = str.replace("{" + i + "}", params[i] == null ? "" : params[i]);
		}
		return str;
	}

	/**
	 * 日期转字符串
	 * 
	 * @param date
	 *            日期
	 * @param pattern
	 *            格式
	 * @return
	 */
	public static String dateToString(Date date, String pattern) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.format(date);
		}
		return "";
	}

	/**
	 * 日期转字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date) {
		return dateToString(date, "yyyy-MM-dd hh:mm:ss");
	}

	/** 为以逗号分隔的字符串的每个单元加入引号,如:aa,bb-->'aa','bb' */
	public static String formatString(String src) {
		StringBuffer result = new StringBuffer();
		result.append("");
		if (src != null) {
			String[] tmp = src.split(",");
			result.append("'" + tmp[0] + "'");
			for (int i = 1; i < tmp.length; i++) {
				result.append(",'" + tmp[i] + "'");
			}
		}
		return result.toString();
	}

	/** 生成编号 */
	public static String formatCode(String code) {
		try {
			int length = code.length();
			Integer num = Integer.valueOf(code.substring(length - 4, length)) + 1;
			String codenum = num.toString();
			int codelength = codenum.length();
			for (int i = 4; i > codelength; i--) {
				codenum = "0" + codenum;
			}
			return codenum;
		} catch (NumberFormatException e) {
			return "0100";
		}
	}
}
