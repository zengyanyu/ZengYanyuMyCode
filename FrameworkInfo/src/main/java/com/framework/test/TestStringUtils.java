package com.framework.test;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

public class TestStringUtils {

	public static void main(String[] args) {
		testStringUtils();
	}

	private static void testStringUtils() {
		String str = "Hello-StringUtils!";

		String str2 = "Hello";

		boolean blank = StringUtils.isBlank(str);

		boolean empty = StringUtils.isEmpty(str);

		boolean equals = StringUtils.equals(str, str2);

		String join = StringUtils.join(Arrays.asList("hello", "StringUtils", "!"));
		System.out.println("输出结果为： [hello, StringUtils, !] ");

		// 字符串分割：默认是按空格分割
		String[] splitStrBlank = StringUtils.split(str);
		for (String s : splitStrBlank) {
			System.out.println(s);
		}
		System.out.println("--------------");
		// 自定义风格符
		String[] splitStrKey = StringUtils.split(str, "-");
		for (String s : splitStrKey) {
			System.out.println(s);
		}
		System.out.println("--------------");
		// 返回空字符串
		String emptyStr = StringUtils.EMPTY;
		System.out.println(emptyStr);
		System.out.println("--------------");
		// 替换字符串
		String replaceStr2 = StringUtils.replace(str2, "Hello", "你好！");
		System.out.println(replaceStr2);
		System.out.println("--------------");
		// 首字母大写
		String capitalizeStr2 = StringUtils.capitalize(str2.toLowerCase());
		System.out.println("--------------");
		System.out.println(capitalizeStr2);
		boolean empty2 = StringUtils.isEmpty(" ");
		System.out.println(empty2);
		System.out.println("--------------");
		boolean blank2 = StringUtils.isBlank(" ");
		System.out.println(blank2);
		System.out.println("--------------");
		boolean empty3 = com.framework.util.StringUtils.isEmpty(" ");
		System.out.println(empty3);
	}

}
