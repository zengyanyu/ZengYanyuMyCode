package com.mj.algo;

//水仙花数算法
public class Narcissus {

	public static void main(String[] args) {
		test1();
	}

	/**
	 * 所谓 "水仙花数 "是指一个三位数，其各位数字立方和等于该数本身。例如：153是一个 "水仙花数 "，
	 * 因为153=1的三次方＋5的三次方＋3的三次方
	 */
	public static void test1() {
		int a, b, c;
		for (int i = 101; i < 1000; i++) {
			a = i % 10;
			b = i / 10 % 10;
			c = i / 100;
			if (a * a * a + b * b * b + c * c * c == i)
				System.out.println(i);
		}
	}
}
