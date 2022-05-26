package com.wisdom.util;

public class ListInit {

	private ListInit() {
	}

	public static void main(String[] args) {
		System.out.println(initValue16(10));
		System.out.println(initValue18(10));
	}

	//jdk1.6扩容算法: 长度增长计算公式： ((旧容量 * 3) / 2) + 1
	public static int initValue16(int index) {
		if (index > 0) {
			return ((index * 3) / 2) + 1;
		}
		return index;
	}

	//jdk1.8的扩容算法：newCapacity = oldCapacity + ( oldCapacity >> 1 )
	public static int initValue18(int index) {
		if (index > 0) {
			return index = index + (index >> 1);
		}
		return index;

	}
}
