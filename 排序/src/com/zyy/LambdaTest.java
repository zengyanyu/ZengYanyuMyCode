package com.zyy;

import java.util.Comparator;

//lambda 表达式语法
public class LambdaTest {

	public static void main(String[] args) {
		test1();
	}

	public static void test1() {
		Comparator<String> comp = (first, second) //
		-> Integer.compare(first.length(), second.length());
	}

}
