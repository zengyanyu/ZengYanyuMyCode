package com.hibernate.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class DateScheduled {

	private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	//每秒执行一次
	//@Scheduled(cron = "00/1 * * * * ?")
	public void test() {
		String format = dateFormat.format(new Date());
		System.out.println("current date = " + format);
	}

}
