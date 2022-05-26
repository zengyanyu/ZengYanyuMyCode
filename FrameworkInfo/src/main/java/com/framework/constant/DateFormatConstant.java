package com.framework.constant;

import java.text.SimpleDateFormat;

import com.framework.util.DateUtil;

/**
 * 日期/时间格式化常量
 * @author ZengYanyu
 * @Description
 * @Date 2020年4月25日 下午6:48:30
 */
public class DateFormatConstant {

	//构造器私有化
	private DateFormatConstant() {
	}

	/**
	 * 年-月-日 时-分-秒
	 * @format yyyy-MM-dd HH:mm:ss
	 */
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 年-月-日
	 * @format yyyy-MM-dd
	 */
	public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	public static void main(String[] args) {
		String format = DATE_FORMAT.format(DateUtil.getCurrDate());
		System.out.println(format);
	}

}
