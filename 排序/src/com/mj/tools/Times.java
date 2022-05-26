package com.mj.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Times {

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

	public interface Task {
		void execute();
	}

	public static void test(String title, Task task) {
		if (task == null)
			return;
		title = (title == null) ? "" : ("[[" + title + "]]");
		System.out.println(title);
		System.out.println("开始: " + sdf.format(new Date()));
		long begin = System.currentTimeMillis();
		task.execute();
		long end = System.currentTimeMillis();
		System.out.println("结束: " + sdf.format(new Date()));
		double delta = (end - begin) / 1000.0;
		System.out.println("耗时: " + delta + "秒(" + (end - begin) + "ms)");
		System.out.println("-------------------------------");
	}

	//test method demo
	public static void main(String[] args) {
		test("", () -> {
			System.out.println("==========");
		});
	}

}
