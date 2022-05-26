package com.jpa.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2021年7月3日 上午1:06:35
 */
public class TimeUtils {

	public static final String COMMON_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public static final String COMMON_DATE_PATTERN = "yyyy-MM-dd";
	public static final String COMMON_TIME_EXP = "^((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29))\\s+([0-1]?[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$";
	public static final String COMMON_DATE_EXP = "^((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29))";

	public static boolean matchCommonDatePattern(String text) {
		return text.matches(
				"^((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29))");
	}

	public static boolean matchCommonTimePattern(String text) {
		return text.matches(
				"^((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29))\\s+([0-1]?[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$");
	}

	public static Date toDate(String text, String pattern) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.parse(text);
	}

	public static Date toDate(Calendar date) {
		return date == null ? null : date.getTime();
	}

	public static Date toDate(long time) {
		return new Date(time);
	}

	public static Calendar toCalendar(String text, String pattern) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		Date parse = dateFormat.parse(text);
		Calendar instance = Calendar.getInstance();
		instance.setTime(parse);
		return instance;
	}

	public static Calendar toCalendar(Date date) {
		return date == null ? null : toCalendar(date.getTime());
	}

	public static Calendar toCalendar(long time) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(time);
		return calendar;
	}

	public static String parseText(Date date, String pattern) {
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(date);
	}

	public static String parseText(Calendar date, String pattern) {
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(date);
	}

	public static int getYear(Date date) {
		return getYear(toCalendar(date));
	}

	public static int getMonth(Date date) {
		return getMonth(toCalendar(date));
	}

	public static int getDayOfYear(Date date) {
		return getDayOfYear(toCalendar(date));
	}

	public static int getDayOfMonth(Date date) {
		return getDayOfMonth(toCalendar(date));
	}

	public static int getDayOfWeek(Date date) {
		return getDayOfWeek(toCalendar(date));
	}

	public static int getHour(Date date) {
		return getHour(toCalendar(date));
	}

	public static int getMinute(Date date) {
		return getMinute(toCalendar(date));
	}

	public static int getSecond(Date date) {
		return getSecond(toCalendar(date));
	}

	public static int getYear(Calendar date) {
		return get((Calendar) date, 1);
	}

	public static int getMonth(Calendar date) {
		return get((Calendar) date, 2);
	}

	public static int getDayOfYear(Calendar date) {
		return get((Calendar) date, 6);
	}

	public static int getDayOfMonth(Calendar date) {
		return get((Calendar) date, 5);
	}

	public static int getDayOfWeek(Calendar date) {
		return get((Calendar) date, 7);
	}

	public static int getHour(Calendar date) {
		return get((Calendar) date, 11);
	}

	public static int getMinute(Calendar date) {
		return get((Calendar) date, 12);
	}

	public static int getSecond(Calendar date) {
		return get((Calendar) date, 13);
	}

	public static long get(Date date, int field) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return (long) get(calendar, field);
	}

	public static int get(Calendar c, int field) {
		return c.get(field);
	}

	public static long second(Date date) {
		return TimeUnit.MILLISECONDS.toSeconds(date.getTime());
	}

	public static long second(Calendar date) {
		return TimeUnit.MILLISECONDS.toSeconds(date.getTimeInMillis());
	}

	public static long millisecond(Date date) {
		return date.getTime();
	}

	public static long millisecond(Calendar date) {
		return date.getTimeInMillis();
	}

	public static long intervalSecond(Date begin, Date end) {
		return interval(begin, end, TimeUnit.SECONDS);
	}

	public static long intervalMilliseconds(Date begin, Date end) {
		return interval(begin, end, TimeUnit.SECONDS);
	}

	public static long interval(Date begin, Date end, TimeUnit timeUnit) {
		return timeUnit.convert(end.getTime() - begin.getTime(), TimeUnit.MILLISECONDS);
	}

	public static long interval(Calendar begin, Calendar end, TimeUnit timeUnit) {
		return timeUnit.convert(end.getTimeInMillis() - begin.getTimeInMillis(), TimeUnit.MILLISECONDS);
	}

	public static void addYear(Date date, int amount) {
		add((Date) date, 1, amount);
	}

	public static void addMonth(Date date, int amount) {
		add((Date) date, 2, amount);
	}

	public static void addWeek(Date date, int amount) {
		add((Date) date, 3, amount);
	}

	public static void addDay(Date date, int amount) {
		add((Date) date, 6, amount);
	}

	public static void addHour(Date date, int amount) {
		add((Date) date, 11, amount);
	}

	public static void addMinute(Date date, int amount) {
		add((Date) date, 12, amount);
	}

	public static void addSecond(Date date, int amount) {
		add((Date) date, 13, amount);
	}

	public static void addMillisecond(Date date, int amount) {
		add((Date) date, 14, amount);
	}

	public static void add(Date date, int field, int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		add(calendar, field, amount);
		date.setTime(calendar.getTimeInMillis());
	}

	public static void addYear(Calendar date, int amount) {
		add((Calendar) date, 1, amount);
	}

	public static void addMonth(Calendar date, int amount) {
		add((Calendar) date, 2, amount);
	}

	public static void addWeek(Calendar date, int amount) {
		add((Calendar) date, 3, amount);
	}

	public static void addDay(Calendar date, int amount) {
		add((Calendar) date, 6, amount);
	}

	public static void addHour(Calendar date, int amount) {
		add((Calendar) date, 11, amount);
	}

	public static void addMinute(Calendar date, int amount) {
		add((Calendar) date, 12, amount);
	}

	public static void addSecond(Calendar date, int amount) {
		add((Calendar) date, 13, amount);
	}

	public static void addMillisecond(Calendar date, int amount) {
		add((Calendar) date, 14, amount);
	}

	public static void add(Calendar date, int field, int amount) {
		date.add(field, amount);
	}

}
