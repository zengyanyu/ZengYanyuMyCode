package com.mj.sort;

import com.mj.tools.Integers;

/**
 * 选择排序
 * @author ZengYanyu
 * @Description
 * @Date 2020年10月19日 下午10:44:45
 * @see com.mj.sort.SelectionSort1.java
 */
public class SelectionSort1 {

	//选择排序
	public static Integer[] sls(Integer[] a) {
		for (int i = 0; i < a.length; i++) {
			for (int j = i + 1; j < a.length; j++) {
				if (a[j] > a[i]) {
					int temMax = a[j];
					a[j] = a[i];
					a[i] = temMax;
				}
			}
		}
		return a;
	}

	public static void main(String[] args) {
		Integer[] random = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		Integer[] sls = sls(random);
		Integers.println(sls);
	}

}
