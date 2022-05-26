package com.mj.sort;

//冒泡排序
public class BubbleSort3<E extends Comparable<E>> extends Sort<E> {

	@Override
	protected void sort() {
		for (int end = array.length - 1; end > 0; end--) {
			//sortedIndex初始值在数组完全有序的时候有用
			int sortedIndex = 1;
			for (int begin = 1; begin <= end; begin++) {
				//if (array[begin] < array[begin - 1]) {
				if (cmp(begin, begin - 1) < 0) {
					swap(begin, begin - 1);

					sortedIndex = begin;
				}
			}
			end = sortedIndex;
		}
	}

}
