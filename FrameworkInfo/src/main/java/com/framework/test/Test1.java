package com.framework.test;

import java.sql.Date;

import com.framework.constant.DateFormatConstant;

public class Test1 {

	//从util.Date转为sql.Date
	public static void main(String[] args) {
		Date date = new Date(new java.util.Date().getTime());
		System.out.println(date);
		String format = DateFormatConstant.DATE_FORMAT.format(date);
		System.out.println(format);
	}

}
