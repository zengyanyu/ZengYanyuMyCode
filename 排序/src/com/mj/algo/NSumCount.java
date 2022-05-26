package com.mj.algo;

//求n个数之和
//计算1+2+3+4+..+n的和
public class NSumCount {

	public static int sum1(int n) {
		int result = 0;
		for (int i = 1; i <= n; i++) {
			result += i;
		}
		return result;
	}

	public static int sum2(int n) {
		return (1 + n) * n / 2;
	}
	
	public static void main(String[] args) {
		System.out.println(sum1(10000));
		System.out.println(sum2(10000));
	}

}
