package org.orion.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateUtil {

	public static final String FORMAT_YMD = "yyyy-MM-dd";
	public static final String FORMAT_YMDHM = "yyyy-MM-dd HH:mm";
	public static final String FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_YMDHMSZ = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

	/**
	 * 字串转为日期
	 *
	 * @param dateStr
	 * @return
	 */
	public static Date getDateFromString(String dateStr) {
		return getDateFromString(dateStr, null);
	}

	/**
	 * 字串转为日期
	 *
	 * @param dateStr
	 * @param pattern
	 * @return
	 */
	public static Date getDateFromString(String dateStr, String pattern) {
		if ((pattern == null) || ("".equals(pattern))) {
			pattern = "yyyy-MM-dd";
		}
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = format.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			date = null;
		}
		return date;
	}

	/**
	 * 字串转为日期
	 *
	 * @param dateStr
	 * @param pattern
	 * @param locale
	 * @return
	 */
	public static Date getDateFromString(String dateStr, String pattern, Locale locale) {
		if ((pattern == null) || ("".equals(pattern))) {
			pattern = "yyyy-MM-dd";
		}
		SimpleDateFormat format = new SimpleDateFormat(pattern, locale);
		Date date;
		try {
			date = format.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			date = null;
		}
		return date;
	}
	
	/**
	 * 日期转指定格式日期
	 *
	 * @param defaultDate
	 * @param pattern
	 * @return
	 */
	public static Date getDateFromDate(Date defaultDate, String pattern) {
		return getDateFromString(getStrFromDate(defaultDate,pattern),pattern);
	}	

	/**
	 * 日期转为字串
	 *
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String getStrFromDate(Date date, String pattern) {
		DateFormat df = new SimpleDateFormat(pattern);
		String s = df.format(date);
		return s;
	}

	/**
	 * 日期转字串
	 *
	 * @param date
	 * @return
	 */
	public static String getLongStrFromDate(Date date) {
		return getStrFromDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 增加月份
	 * 
	 * @param date
	 * @param month
	 * @return
	 */
	public static Date addMonth(Date date, int month) {
		if (date == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, month);
		return cal.getTime();
	}

	/**
	 * 指定日期加上一个值
	 *
	 * @param date
	 * @param field
	 * @param amount
	 * @return
	 */
	public static Date optTime(Date date, int field, int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(field, amount);
		return calendar.getTime();
	}

	/**
	 * 返回两个日期时间差 ms
	 *
	 * @param first
	 * @param second
	 * @return
	 */
	public static long distance(Date first, Date second) {
		return second.getTime() - first.getTime();
	}

	/**
	 * 时间差s
	 *
	 * @param first
	 * @param second
	 * @return
	 */
	public static int distanceSec(Date first, Date second) {
		Long sec = Long.valueOf((second.getTime() - first.getTime()) / 1000L);
		return sec.intValue();
	}

	/**
	 * 返回时间差m
	 *
	 * @param first
	 * @param second
	 * @return
	 */
	public static int distanceMin(Date first, Date second) {
		return distanceSec(first, second) / 60;
	}

	/**
	 * 返回时间差h
	 *
	 * @param first
	 * @param second
	 *            被减数
	 * @return
	 */
	public static int distanceHour(Date first, Date second) {
		return distanceMin(first, second) / 60;
	}

	/**
	 * 返回时间差d
	 *
	 * @param first
	 * @param second
	 *            被减数
	 * @return
	 */
	public static int distanceDay(Date first, Date second) {
		return distanceHour(first, second) / 24;
	}

	/**
	 * 返回月份差
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static int distanceMonth(Date start, Date end) {
		if (start.after(end)) {
			Date t = start;
			start = end;
			end = t;
		}
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(start);
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(end);
		Calendar temp = Calendar.getInstance();
		temp.setTime(end);
		temp.add(Calendar.DATE, 1);

		int year = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
		int month = endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);

		if ((startCalendar.get(Calendar.DATE) == 1) && (temp.get(Calendar.DATE) == 1)) {
			return year * 12 + month + 1;
		} else if ((startCalendar.get(Calendar.DATE) != 1) && (temp.get(Calendar.DATE) == 1)) {
			return year * 12 + month;
		} else if ((startCalendar.get(Calendar.DATE) == 1) && (temp.get(Calendar.DATE) != 1)) {
			return year * 12 + month;
		} else {
			return (year * 12 + month - 1) < 0 ? 0 : (year * 12 + month);
		}
	}

	public static Date getCurrentDate() {
		return new Date(System.currentTimeMillis());
	}

	/**
	 * 将时间往后推几天
	 * 
	 * @param days
	 *            代表天数
	 * @return
	 */
	public static Date getPushBackDate(int days, Date date) {

		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);// 把日期往后增加一天.整数往后推,负数往前移动
		date = calendar.getTime(); // 这个时间就是日期往后推一天的结果

		return date;
	}
	
	/**
	 * 将时间往后推几秒
	 * 
	 * @param seconds
	 *            代表秒数
	 * @return
	 */
	public static Date getPushBackSecond(int seconds, Date date){
		
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND, seconds);// 把日期往后增加一天.整数往后推,负数往前移动
		date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
		
		return date;
	}
	public static Date getStartTime() {
		Calendar todayStart = Calendar.getInstance();
		todayStart.set(Calendar.HOUR_OF_DAY,0);
		todayStart.set(Calendar.MINUTE, 0);
		todayStart.set(Calendar.SECOND, 0);
		todayStart.set(Calendar.MILLISECOND, 0);
		return todayStart.getTime();
	}
	
	public static Date getStartTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY,0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public static Date getEndTime() {
		Calendar todayEnd = Calendar.getInstance();
		todayEnd.set(Calendar.HOUR_OF_DAY,23);
		todayEnd.set(Calendar.MINUTE, 59);
		todayEnd.set(Calendar.SECOND, 59);
		todayEnd.set(Calendar.MILLISECOND, 999);
		return todayEnd.getTime();
	}
	
	public static Date getEndTime(Date date) {
		Calendar end = Calendar.getInstance();
		end.setTime(date);
		end.set(Calendar.HOUR_OF_DAY,23);
		end.set(Calendar.MINUTE, 59);
		end.set(Calendar.SECOND, 59);
		end.set(Calendar.MILLISECOND, 999);
		return end.getTime();
	}
	
	/**
	 * 判断是时间是否在两个整点之间
	 * @param date
	 * @param startHour 开始整点
	 * @param endHour 结束整点
	 * @return true 在  false 不在
	 */
	public static boolean isInTime(Date date, int startHour, int endHour){
		Calendar cal= Calendar.getInstance();
		cal.setTime(date);
		int hour = cal.get(Calendar.HOUR_OF_DAY);// 获取小时
		int minute = cal.get(Calendar.MINUTE);// 获取分钟
		int second = cal.get(Calendar.SECOND);//获取秒数
		int secondOfDay = (hour * 60 + minute) * 60 + second;// 从0:00分开是到目前为止的秒数
		int start = startHour * 60 * 60 ;// 起始时间 startHour的秒数
		int end = endHour * 60 * 60;// 结束时间 endHour的秒数
		if (secondOfDay >= start && secondOfDay <= end) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 获取上个月的第一天
	 * @return
	 */
	public static Date getLastMonthFirstDay(){
        //获取前月的第一天
        Calendar cale=Calendar.getInstance();//获取当前日期 
        cale.add(Calendar.MONTH, -1);
        cale.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
        return cale.getTime();		
	}
	
	/**
	 * 获取上个月的最后一天
	 * @return
	 */
	public static Date getLastMonthEndDay(){
        Calendar cale = Calendar.getInstance();   
        cale.set(Calendar.DAY_OF_MONTH,0);//设置为1号,当前日期既为本月第一天 
        return cale.getTime();		
	}
}