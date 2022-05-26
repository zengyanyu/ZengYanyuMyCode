package com.mj.sort;

/**
 * 插入排序
 * @author ZengYanyu
 * @Description
 * @Date 2020年6月24日 下午10:40:52
 * @param <E>
 * @see com.mj.sort.InsertionSort.java
 */
public class InsertionSort<E extends Comparable<E>> extends Sort<E> {

	@Override
	protected void sort() {
		for (int begin = 1; begin < array.length; begin++) {
			int current = begin;
			while (current > 0 && cmp(current, current - 1) < 0) {
				swap(current, current - 1);
				current--;
			}
		}
	}

}
