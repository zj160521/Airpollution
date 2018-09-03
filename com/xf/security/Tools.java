package com.xf.security;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;


@Component
public class Tools {
	static Pattern pattern1 = Pattern.compile("-?[0-9]+.?[0-9]*");

	public static boolean isNumeric(String str) {
		Matcher isNum = pattern1.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	static Pattern pattern2 = Pattern.compile("-?[0-9]+");

	public static boolean isInteger(String str) {
		if (str == null || str.isEmpty())
			return false;
		Matcher isNum = pattern2.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	static String format = "yyyy-MM-dd HH:mm:ss";
	static SimpleDateFormat sdf = new SimpleDateFormat(format);

	public static String ToDateStr(Calendar cal) {
		return sdf.format(cal.getTime());
	}

	/**
	 * 时间戳转换成日期格式字符串
	 * 
	 * @param seconds
	 *            精确到秒的字符串
	 * @param formatStr
	 * @return
	 */
	public static String timeStamp2Date(String seconds, String format) {
		if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
			return "";
		}
		if (format == null || format.isEmpty())
			format = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date(Long.valueOf(seconds + "000")));
	}

	/**
	 * 日期格式字符串转换成时间戳
	 * 
	 * @param date
	 *            字符串日期
	 * @param format
	 *            如：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String date2TimeStamp(String date_str, String format) {
		try {
			if (format == null || format.isEmpty())
				format = "yyyy-MM-dd HH:mm:ss";
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return String.valueOf(sdf.parse(date_str).getTime() / 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 取得当前时间戳（精确到秒）
	 * 
	 * @return
	 */
	public static String timeStamp() {
		long time = System.currentTimeMillis();
		String t = String.valueOf(time / 1000);
		return t;
	}

	public static void sort(List<Integer> list) {
		Collections.sort(list, new Comparator<Integer>() {

			public int compare(Integer lhs, Integer rhs) {

				// 降序
				if (lhs > rhs) {

					return -1;
				} else if (lhs < rhs) {

					return 1;
				} else {

					return 0;
				}
			}
		});
	}
}
