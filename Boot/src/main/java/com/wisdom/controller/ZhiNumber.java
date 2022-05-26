package com.wisdom.controller;

public class ZhiNumber {

	//判断是否是质数
	public static void main(String[] args) {
		for (int i = 2; i < 10000; i++) {
			if (i == 2 || i == 3 || i == 5) {
				System.out.println(i);
			}
			if (i % 2 != 0 && i % 3 != 0 && i % 5 != 0) {
				System.out.println(i);
			}
		}
	}

}
