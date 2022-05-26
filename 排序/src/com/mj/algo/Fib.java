package com.mj.algo;

import com.mj.tools.Times;

/**
 * 斐波那契数列
 * 1  1  2  3  5  8  13  21
 * @author ZengYanyu
 * @Description
 * @Date 2020年6月20日 下午11:39:05
 * @see com.springboot.test.TestFib.java
 */
public class Fib {

	public static void main(String[] args) {
		int n = 64;
		int fib6 = fib6(n);
		System.out.println(fib6);
		int fib7 = fib7(n);
		System.out.println(fib7);
		System.out.println("--------------");
		Times.test("fib1", () -> {
			//System.out.println(fib1(n));
		});
		Times.test("fib6", () -> {
			System.out.println(fib6(n));
		});
		Times.test("fib7", () -> {
			System.out.println(fib7(n));
		});
		Times.test("fib8", () -> {
			System.out.println(fib8(n));
		});
	}

	public static int fib1(int n) {
		if (n <= 2)
			return 1;
		return fib1(n - 1) + fib1(n - 2);
	}

	//第二种方式.用组数存放计算过的结果,避免重复计算(这是一个自顶向上的过程)
	private static int fib2(int n) {
		int[] array = new int[n + 1];
		array[1] = array[2] = 1;
		return fib2(n, array);
	}

	private static int fib2(int n, int[] array) {
		//new出来的组数对象,如果没有赋值,那么默认为0
		if (array[n] == 0) {
			array[n] = fib2(n - 1, array) + fib2(n - 2, array);
		}
		return array[n];
	}

	//第三种方式,这是一个自底向上的过程
	private static int fib3(int n) {
		if (n <= 2) {
			return 1;
		}
		int[] array = new int[n + 1];
		array[1] = array[2] = 1;
		for (int i = 3; i <= n; i++) {
			array[i] = array[i - 1] + array[i - 2];
		}
		return array[n];
	}

	//第四种方式,每次计算只用到数组中的两个元素
	//解决方式,使用滚动数组
	private static int fib4(int n) {
		if (n <= 2) {
			return 1;
		}
		int[] array = new int[2];
		array[0] = array[1] = 1;
		for (int i = 3; i <= n; i++) {
			array[i % 2] = array[(i - 1) % 2] + array[(i - 2) % 2];
		}
		return array[n % 2];
	}

	/**
	 * 3%2=1  0b011 & 0b001 = 1
	 * 4%2=0  0b100 & 0b001 = 0
	 * 5%2=1  0b101 & 0b001 = 1
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年6月21日 上午11:45:58
	 * @param n
	 * @return
	 */
	private static int fib5(int n) {
		if (n <= 2) {
			return 1;
		}
		int[] array = new int[2];
		array[0] = array[1] = 1;
		for (int i = 3; i <= n; i++) {
			array[i & 1] = array[(i - 1) & 1] + array[(i - 2) & 1];
		}
		return array[n & 1];
	}

	/**
	 * 没有额外开闭堆空间
	 * 时间复杂度为O(n),空间复杂度为O(1)
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年6月21日 上午11:55:37
	 * @param n
	 * @return
	 */
	private static int fib6(int n) {
		if (n <= 2) {
			return 1;
		}
		int first = 1;
		int second = 1;
		for (int i = 3; i <= n; i++) {
			second = first + second;
			first = second - first;
		}
		return second;
	}

	//线性代数解法
	private static int fib7(int n) {
		double c = Math.sqrt(5);
		return (int) ((Math.pow((1 + c) / 2, n) - Math.pow((1 - c) / 2, n)) / c);
	}

	/**
	 * 0 1 2 3 4 5 6 7        第几项
	 * 0 1 1 2 3 5 8 13     第几项对应的数
	 * @Date 2021年1月23日 下午10:05:32
	 */
	public static int fib8(int n) {
		if (n <= 1) return n;
		int first = 0;
		int second = 1;
		for (int i = 0; i < n - 1; i++) {
			int sum = first + second;
			first = second;
			second = sum;
		}
		return second;
	}
	
}
