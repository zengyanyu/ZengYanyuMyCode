package com.mj.algo;

//归并数组
public class MegerArray {

	public static void meger(int[] nums1, int m, int[] nums2, int n) {
		int i1 = m - 1;
		int i2 = n - 1;
		int cur = nums1.length - 1;

		while (i2 > 0) {
			if (i1 >= 0 && nums2[i2] < nums1[i1]) {
				nums1[cur--] = nums1[i1--];
			} else {
				nums1[cur--] = nums2[i2--];
			}
		}
	}

	public static void main(String[] args) {
		int[] arr1 = new int[] { 1, 3, 5, 0, 0, 0 };//3
		int[] arr2 = new int[] { 2, 4, 6 };//3
		meger(arr1, 3, arr2, 3);
	}

}
