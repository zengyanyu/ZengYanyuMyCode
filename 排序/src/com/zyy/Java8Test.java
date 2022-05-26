package com.zyy;

import java.util.Comparator;

public class Java8Test {

	public static void main(String[] args) {
		//类型声明
		MathOperation add = (int a, int b) -> a + b;
		//System.out.println(add);
		int operation = add.operation(0, 5);
		System.out.println(operation);

		//不用声明类型
		MathOperation subtraction = (a, b) -> a - b;
		System.out.println(subtraction.operation(5, 3));

		// 大括号中的返回语句
		MathOperation multiplication = (int a, int b) -> {
			return a * b;
		};
		System.out.println(multiplication.operation(5, 5));

		// 没有大括号及返回语句
		MathOperation division = (int a, int b) -> a / b;
		System.out.println(division.operation(5, 5));

		Comparator<String> comparator = //
		(first, second) -> Integer.compare(first.length(), second.length());
		System.out.println(comparator);
	}

}
