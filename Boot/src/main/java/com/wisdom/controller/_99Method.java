package com.wisdom.controller;

public class _99Method {

	public static void main(String[] args) {
		for (int i = 1; i < 10; i++) {
			for (int j = 1; j <= i; j++) {
				String k = "";
				int n = (j * i);
				if (n <= 9) {
					k = " " + n;
				} else {
					//k = Integer.toString(n);
					k = String.valueOf(n);
				}
				System.out.print(j + " * " + i + "=" + k + "   ");
			}
			System.out.println("");
		}
	}

}
