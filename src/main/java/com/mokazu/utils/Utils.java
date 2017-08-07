package com.mokazu.utils;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Utils {

	private static final Logger	logger	= LogManager.getLogger(Utils.class);

	private static Pattern		pattern	= Pattern.compile("^-?[0-9]*\\.?[0-9]*");

	public static boolean isDigit(String value) {
		if (StringUtils.isEmpty(value)) {
			return false;
		}
		String mstr = value.toString();
		return pattern.matcher(mstr).matches();
	}

	public static Integer toInt(String value, int defaultV) {
		if (isDigit(value)) {
			return Double.valueOf(value).intValue();
		} else {
			return defaultV;
		}
	}

	/**
	 * 获取当前时间前后天数的长整型秒
	 * 
	 * @param days
	 * @return
	 */
	public static long getLongTime(Integer days) {
		days = days == null ? 0 : days;
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, days);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTimeInMillis();
	}

	/**
	 * 占位符替换
	 * 
	 * @param str
	 * @param args
	 * @return
	 */
	public static String replace(String str, Map<String, String> args) {
		String result = str;
		int pos_start = str.indexOf("${");
		if (pos_start >= 0) {
			StringBuilder builder = new StringBuilder();
			int pos_end = -1;
			while (pos_start >= 0) {
				builder.append(str, pos_end + 1, pos_start);
				pos_end = str.indexOf('}', pos_start + 2);
				if (pos_end < 0) {
					pos_end = pos_start - 1;
					break;
				}
				String propName = str.substring(pos_start + 2, pos_end);
				String replacement = null;
				if (propName.length() == 0) {
					replacement = null;
				} else if (args != null && args.containsKey(propName)) {
					replacement = args.get(propName);
				} else {
					replacement = null;
				}
				if (replacement != null) {
					builder.append(replacement);
				} else {
					builder.append(str, pos_start, pos_end + 1);
					logger.warn("not found the value of {}", propName);
				}
				pos_start = str.indexOf("${", pos_end + 1);
			}
			builder.append(str, pos_end + 1, str.length());
			result = builder.toString();
		}
		return result;
	}

	public static void main(String[] args) {
		Map<String, String> a = new HashMap<String, String>();
		a.put("1", "sdf");
		System.out.println(replace("test ${1} ${2} ${1}", a));
		System.out.println(toInt("15.", -1));
	}
}
