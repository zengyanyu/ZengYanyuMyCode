package com.springboot.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 并发时间工具类
 * @author ZengYanyu
 * @Description
 * @Date 2020年4月25日 下午6:56:04
 */
public class ConcurrentDateUtil {

	//constructor private
	private ConcurrentDateUtil() {
	}

	private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
	};

	/**
	 * 将String类型的date解析成Date类型
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年4月25日 下午6:56:12
	 * @param dateStr
	 * @return
	 * @throws Exception
	 */
	public static Date parse(String dateStr) {
		try {
			return threadLocal.get().parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 将Date类型的时间格式化为String类型
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年4月25日 下午6:56:27
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static String format(Date date) {
		return threadLocal.get().format(date);
	}

}
