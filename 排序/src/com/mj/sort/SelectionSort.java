package com.mj.sort;

/**
 * 选择排序
 * @author ZengYanyu
 * @Description
 * @Date 2020年10月20日 下午9:35:32
 * @param <E>
 * @see com.mj.sort.SelectionSort.java
 */
public class SelectionSort<E extends Comparable<E>> extends Sort<E> {

	@Override
	protected void sort() {
		for (int end = array.length - 1; end > 0; end--) {
			int maxIndex = 0;
			for (int begin = 1; begin <= end; begin++) {
				//if (array[maxIndex] <= array[begin]) {
				if (cmp(maxIndex, begin) <= 0) {
					maxIndex = begin;
				}
			}
			swap(maxIndex, end);
		}
	}

}
