package com.springboot.util;

import java.util.Calendar;
import java.util.Date;

import com.springboot.constant.DateFormatConstant;

/**
 * 日期工具类
 * @author ZengYanyu
 * @Description
 * @Date 2020年4月25日 下午6:44:23
 */
public class DateUtil {

	//构造器私有化
	private DateUtil() {
	}

	/**
	 * 获取到当前日期的开始时间
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年4月25日 下午6:45:46
	 * @param currDate
	 * @return
	 */
	public static Date getBeginDate(Date currDate) {
		Calendar instance = Calendar.getInstance();
		instance.setTime(currDate);
		instance.set(Calendar.HOUR_OF_DAY, 0);//时
		instance.set(Calendar.MINUTE, 0);//分
		instance.set(Calendar.SECOND, 0);//秒
		return instance.getTime();
	}

	/**
	 * 获取到当前日期的结束日期
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年4月25日 下午6:46:03
	 * @param currDate
	 * @return
	 */
	public static Date getEndDate(Date currDate) {
		Calendar instance = Calendar.getInstance();
		instance.setTime(currDate);
		instance.set(Calendar.HOUR_OF_DAY, 23);//时
		instance.set(Calendar.MINUTE, 59);//分
		instance.set(Calendar.SECOND, 59);//秒
		return instance.getTime();
	}

	/**
	 * 获取字符串类型的当前时间
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年5月10日 下午4:35:27
	 * @return
	 */
	public static String getCurrentDate() {
		return DateFormatConstant.DATE_FORMAT2.format(new Date());
	}

	/**
	 * 获取当前时间
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年5月24日 下午5:58:25
	 * @return  返回Date类型的值
	 */
	public static Date getCurrDate() {
		return new Date();
	}

	public static void main(String[] args) {
		test1();
	}

	private static void test1() {
		System.out.println("currentDateStr = " + getCurrentDate());
		System.out.println("==================================");
		Date beginDate = getBeginDate(getCurrDate());
		String beginDateStr = DateFormatConstant.DATE_FORMAT2.format(beginDate);
		System.out.println("beginDateStr = " + beginDateStr);
		Date endDate = getEndDate(new Date());
		String endDateStr = DateFormatConstant.DATE_FORMAT2.format(endDate);
		System.out.println("endDateStr   = " + endDateStr);
	}

}
