package com.mj.sort;

/**
 * 二分搜索
 * @author ZengYanyu
 * @Description
 * @Date 2020年6月25日 下午1:18:04
 * @see com.mj.sort.BinarySearch.java
 */
public class BinarySearch {

	/**
	 * 查找v在有序数组array中的位置
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年6月25日 下午1:21:13
	 * @param array
	 * @param v
	 * @return
	 */
	public static int indexOf(int[] array, int v) {
		if (array == null || array.length == 0) {
			return -1;
		}
		int begin = 0;
		int end = array.length;
		while (begin < end) {
			int mid = (begin + end) >> 1;
			if (v < array[mid]) {
				end = mid;
			} else if (v > array[mid]) {
				begin = mid;
			} else {
				return mid;
			}
		}
		return -1;
	}

	/**
	 * 查找v在有序数组array中的待插入位置
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年6月25日 下午1:22:07
	 * @param array
	 * @param v
	 * @return
	 */
	public static int search(int[] array, int v) {
		if (array == null || array.length == 0) {
			return -1;
		}

		int begin = 0;
		int end = array.length;
		while (begin < end) {
			int mid = (begin + end) >> 1;
			if (v < array[mid]) {
				end = mid;
			} else {
				begin = mid + 1;
			}
		}
		return begin;
	}

}
