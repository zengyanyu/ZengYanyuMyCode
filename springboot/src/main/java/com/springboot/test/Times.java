package com.springboot.test;

import java.text.SimpleDateFormat;

public class Times {

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:sss");

	public interface Task {
		void execute();
	}

	public interface ReturnTask {
		Object execute();
	}

	public static void test(String title, Task task) {
		if (task == null)
			return;
		printTitle(title);

		long begin = System.currentTimeMillis();
		printBegin();
		task.execute();
		printEnd();
		long end = System.currentTimeMillis();

		printDuration(begin, end);
	}

	public static Object test(String title, ReturnTask task) {
		if (task == null)
			return null;
		printTitle(title);
		long begin = System.currentTimeMillis();
		printBegin();
		Object result = task.execute();
		printEnd();
		long end = System.currentTimeMillis();
		printDuration(begin, end);
		return result;
	}

	private static void printDuration(long begin, long end) {
		long longDate = end - begin;
		System.out.println(longDate);
	}

	private static void printEnd() {
		long currentTimeMillis = System.currentTimeMillis();
		String format = sdf.format(currentTimeMillis);
		System.out.println(format);
	}

	private static void printBegin() {
		long currentTimeMillis = System.currentTimeMillis();
		String format = sdf.format(currentTimeMillis);
		System.out.println(format);
	}

	private static void printTitle(String title) {
		System.out.println("[" + title + "]");
	}

}
