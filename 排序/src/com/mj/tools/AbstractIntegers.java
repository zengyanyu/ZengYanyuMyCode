package com.mj.tools;

public abstract class AbstractIntegers {

	//打印
	public static void println(Integer[] array) {
		for (int i = 0; i < array.length; i++) {
			if (i != (array.length - 1)) {
				System.out.print(array[i] + "_");
			} else {
				System.out.print(array[i]);
			}
		}
	}

}
