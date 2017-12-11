package com.gtercn.carhome.web.api.utils;

import java.util.*;
import java.text.*;

/**
 * 日期转换类
 * 
 * @author Administrator
 * 
 *         2016-7-29 上午08:44:25
 */
public final class DateFormateUtil {

	/**
	 * 格式化日期为字符串 "yyyy-MM-dd hh:mm"
	 * 
	 * @param basicDate
	 * @param strFormat
	 * @return String
	 */
	public static String formatDateTime(Date basicDate, String strFormat) {
		SimpleDateFormat df = new SimpleDateFormat(strFormat);
		return df.format(basicDate);
	}

	/**
	 * 格式化日期为字符串 "yyyy-MM-dd hh:mm"
	 * 
	 * @param basicDate
	 * @param strFormat
	 * @return String
	 */
	public static String formatDateTime(String basicDate, String strFormat) {
		SimpleDateFormat df = new SimpleDateFormat(strFormat);
		Date tmpDate = null;
		try {
			tmpDate = df.parse(basicDate);
		} catch (Exception e) {
			// 日期型字符串格式错误
		}
		return df.format(tmpDate);
	}

	/**
	 * 当前日期，返回String (yyyy-mm-dd HH:mm:ss.SSS)
	 * 
	 * @return
	 */
	public static String getNowTime(String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		Calendar rightNow = Calendar.getInstance();
		return df.format(rightNow.getTime());
	}

	// 当前日期，返回String (yyyy-mm-dd)
	public static String getNowTimeHms() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Calendar rightNow = Calendar.getInstance();
		return df.format(rightNow.getTime());
	}

	/**
	 * 返回当前日期
	 * 
	 * @return Date
	 */
	public static Date getCurrentDate() {
		Date rightNow = Calendar.getInstance().getTime();
		return rightNow;
	}

	// 当前日期加减n天后的日期，返回String (yyyy-mm-dd)
	public static String nDaysAftertoday(int n) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar rightNow = Calendar.getInstance();
		// rightNow.add(Calendar.DAY_OF_MONTH,-1);
		rightNow.add(Calendar.DAY_OF_MONTH, +n);
		return df.format(rightNow.getTime());
	}

	// 当前日期加减n天后的日期，返回String (yyyy-mm-dd)
	public static Date nDaysAfterNowDate(int n) {
		Calendar rightNow = Calendar.getInstance();
		// rightNow.add(Calendar.DAY_OF_MONTH,-1);
		rightNow.add(Calendar.DAY_OF_MONTH, +n);
		return rightNow.getTime();
	}

	// 给定一个日期型字符串，返回加减n天后的日期型字符串
	public static String nDaysAfterOneDateString(String basicDate, int n) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date tmpDate = null;
		try {
			tmpDate = df.parse(basicDate);
		} catch (Exception e) {
			// 日期型字符串格式错误
		}
		long nDay = (tmpDate.getTime() / (24 * 60 * 60 * 1000) + 1 + n)
				* (24 * 60 * 60 * 1000);
		tmpDate.setTime(nDay);

		return df.format(tmpDate);
	}

	// 给定一个日期，返回加减n天后的日期
	public static Date nDaysAfterOneDate(Date basicDate, int n) {
		long nDay = (basicDate.getTime() / (24 * 60 * 60 * 1000) + 1 + n)
				* (24 * 60 * 60 * 1000);
		basicDate.setTime(nDay);
		return basicDate;
	}

	// 计算两个日期相隔的天数
	public static int nDaysBetweenTwoDate(Date firstDate, Date secondDate) {
		int nDay = (int) ((secondDate.getTime() - firstDate.getTime()) / (24 * 60 * 60 * 1000));
		return nDay;
	}

	// 计算两个日期相隔的天数
	public static int nDaysBetweenTwoDate(String firstString,
			String secondString) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date firstDate = null;
		Date secondDate = null;
		try {
			firstDate = df.parse(firstString);
			secondDate = df.parse(secondString);
		} catch (Exception e) {
			// 日期型字符串格式错误
		}

		int nDay = (int) ((secondDate.getTime() - firstDate.getTime()) / (24 * 60 * 60 * 1000));
		return nDay;
	}

	// 给定一个日期，返回加减指定 （ 年/月/周/天） 后的日期
	public static String getDateByStartAndInterval(String startDate,
			String intervalType, int interval) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dt = sdf.parse(startDate);
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(dt);
		// 计算间隔后的时间
		if (intervalType.equals("1")) {// 年
			rightNow.add(Calendar.YEAR, interval);// 日期减?年
		} else if (intervalType.equals("2")) {// 月
			rightNow.add(Calendar.MONTH, interval);// 日期加?个月
		} else if (intervalType.equals("3")) {// 周
			rightNow.add(Calendar.DAY_OF_YEAR, interval * 7);// 日期加?周
		} else if (intervalType.equals("4")) {// 天
			rightNow.add(Calendar.DAY_OF_YEAR, interval);// 日期加?天
		}
		Date resultDate = rightNow.getTime();
		String reStr = sdf.format(resultDate);
		return reStr;
	}

	public static void main(String[] args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String str = "20110823";
		Date dt = sdf.parse(str);
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(dt);
		rightNow.add(Calendar.YEAR, -1);// 日期减1年
		rightNow.add(Calendar.MONTH, 3);// 日期加3个月
		rightNow.add(Calendar.DAY_OF_YEAR, 10);// 日期加10天
		Date dt1 = rightNow.getTime();
		String reStr = sdf.format(dt1);
		System.out.println(reStr);

		// Calendar用法
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00")); // 获取东八区时间
		int year = c.get(Calendar.YEAR); // 获取年
		int month = c.get(Calendar.MONTH) + 1; // 获取月份，0表示1月份
		int day = c.get(Calendar.DAY_OF_MONTH); // 获取当前天数
		int first = c.getActualMinimum(Calendar.DAY_OF_MONTH); // 获取本月最小天数
		int last = c.getActualMaximum(Calendar.DAY_OF_MONTH); // 获取本月最大天数
		int time = c.get(Calendar.HOUR_OF_DAY); // 获取当前小时
		int min = c.get(Calendar.MINUTE); // 获取当前分钟
		int xx = c.get(Calendar.SECOND); // 获取当前秒

		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String curDate = s.format(c.getTime()); // 当前日期
		System.out.println("当前时间：" + year + "-" + month + "-" + day + " "
				+ time + ":" + min + ":" + xx);
		System.out.println("第一天和最后天：" + first + "," + last);
		System.out.println("当前日期curDate====：" + curDate);

		/*
		 * 输出结果： 当前时间：2012-9-25 22:50:54 第一天和最后天：1,30 当前日期curDate：2012-09-25
		 * 22:50:54
		 */

		// Calendar的计算
		c.add(Calendar.YEAR, 1);
		c.add(Calendar.MONTH, 2);
		c.add(Calendar.DAY_OF_MONTH, 0);
		int year2 = c.get(Calendar.YEAR);
		int month2 = c.get(Calendar.MONTH) + 1;
		int day2 = c.get(Calendar.DAY_OF_MONTH);
		int firstD = c.getActualMinimum(Calendar.DAY_OF_MONTH);
		int lastD = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		System.out.println("当前时间：" + year2 + "-" + month2 + "-" + day2 + " "
				+ time + ":" + min + ":" + xx);
		System.out.println("第一天和最后天：" + firstD + "," + lastD);
		/*
		 * 输出结果： 当前时间：2013-10-26 23:4:3 第一天和最后天：1,31
		 */
		System.out.println(DateFormateUtil.getDateByStartAndInterval(curDate,
				"2", 2)
				+ "*********____________");
	}
}
