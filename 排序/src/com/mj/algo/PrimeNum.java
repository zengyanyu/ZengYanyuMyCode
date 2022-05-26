package com.mj.algo;

//素数
public class PrimeNum {

	//素数
	public static void test2() {
		int count = 0;
		for (int i = 101; i < 200; i += 2) {
			boolean flag = true;
			for (int j = 2; j <= Math.sqrt(i); j++) {
				if (i % j == 0) {
					flag = false;
					break;
				}
			}
			if (flag == true) {
				count++;
				System.out.println(i);
			}
		}
		System.out.println(count);
	}

}
