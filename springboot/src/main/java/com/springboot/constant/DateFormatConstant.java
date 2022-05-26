package com.springboot.constant;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * 日期/时间格式化常量
 * @author ZengYanyu
 * @Description
 * @Date 2020年4月25日 下午6:48:30
 */
public class DateFormatConstant {

	private DateFormatConstant() {
	}

	/**
	 * 年-月-日
	 * @format yyyy-MM-dd
	 */
	public static final DateFormat DATE_FORMAT1 = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 年-月-日 时-分-秒
	 * @format yyyy-MM-dd HH:mm:ss(HH:表示24时置)
	 */
	public static final DateFormat DATE_FORMAT2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 时-分-秒(hh:表示12小时置) a:表示上午/下午  :如:  06:55:45 下午
	 * @format hh:mm:ss a
	 */
	public static final DateFormat DATE_FORMAT3 = new SimpleDateFormat("hh:mm:ss a");

}
