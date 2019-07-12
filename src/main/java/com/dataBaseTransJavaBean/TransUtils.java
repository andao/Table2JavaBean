package com.dataBaseTransJavaBean;

import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class TransUtils {

	public static String rn = "\n\r";
	public static String n = "\n";
	public static String tab = "    ";
	
	private static Properties props = new Properties();
	static {
		try {
			props.load(new InputStreamReader(TransUtils.class.getClassLoader().getResourceAsStream("conf.properties"),"UTF-8"));
		} catch (Exception e) {}
	}
	
	public static String get(String key, String ...defaultValue) {
		String value = props.getProperty(key);
		if (null == value && null != defaultValue && defaultValue.length>0) {
			value = defaultValue[0];
		}
		return value;
	}
	
	public static int getInt(String key, int defaultValue) {
		String value = props.getProperty(key);
		if(value != null) return Integer.parseInt(value);
		return defaultValue;
	}
	
	public static boolean getBoolean(String key, boolean defaultValue) {
		String value = props.getProperty(key);
		if(value != null) return Boolean.parseBoolean(value);
		return defaultValue;
	}
	
	public static String upperFirstLetter(String str) {
		if (null == str || str.isEmpty()) {
			return "";
		}
		String r =str.substring(0, 1);
		r = r.toUpperCase();
		if (str.length() > 1) {
			r = r + str.substring(1);
		}
		return r;
	}
	
	public static String lowerFirstLetter(String str) {
		if (null == str || str.isEmpty()) {
			return "";
		}
		String r =str.substring(0, 1);
		r = r.toLowerCase();
		if (str.length() > 1) {
			r = r + str.substring(1);
		}
		return r;
	}
	
	/**
	 *  将下划线分隔转为上驼峰
	 * @param str
	 * @return
	 * @author anjiheng 2019年6月20日
	 */
	public static String transSortLineToUpper(String str) {
		if (null == str || str.isEmpty()) {
			return "";
		}
		
		String[] strArray = str.split("_");
		StringBuilder sb = new StringBuilder("");
		for (String s : strArray) {
			if (!s.isEmpty()) {
				sb.append(upperFirstLetter(s));
			}
		}
		return sb.toString();
	}
	
	/**
	 *  将下划线分隔转为上驼峰
	 * @param str
	 * @return
	 * @author anjiheng 2019年6月20日
	 */
	public static String transSortLineToLower(String str) {
		if (null == str || str.isEmpty()) {
			return "";
		}
		
		String[] strArray = str.split("_");
		StringBuilder sb = new StringBuilder("");
		for (String s : strArray) {
			if (!s.isEmpty()) {
				sb.append(upperFirstLetter(s));
			}
		}
		return lowerFirstLetter(sb.toString());
	}
	
	public static String formatDate(Date date) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sf.format(date);
	}
}
