package com.mj.sort;

/**
 * 插入排序
 * @author ZengYanyu
 * @Description
 * @Date 2020年6月25日 下午1:06:02
 * @param <E>
 * @see com.mj.sort.InsertionSort2.java
 */
public class InsertionSort3<E extends Comparable<E>> extends Sort<E> {

	//使用二分搜索进行优化
	@Override
	protected void sort() {
		for (int begin = 1; begin < array.length; begin++) {
			/*
			//备份元素(待插入元素)
			E v = array[begin];
			//插入位置
			int insertIndex = search(begin);
			//将[indertIndex,begin)范围内的元素往右边挪动一个单位
			//for (int i = begin - 1; i >= insertIndex; i--) {
			//}
			for (int i = begin; i > insertIndex; i--) {
				array[i] = array[i - 1];
			}
			array[insertIndex] = v;
			*/

			//insert:将begin位置的元素插入到 search(begin)的位置
			insert(begin, search(begin));
		}
	}

	/**
	 * 将source位置的元素插入到dest位置
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年6月25日 下午2:10:40
	 * @param source  来源
	 * @param dest    目标
	 */
	private void insert(int source, int dest) {
		//备份
		E v = array[source];
		for (int i = source; i > dest; i--) {
			array[i] = array[i - 1];
		}
		array[dest] = v;
	}

	/**
	 * 利用二分搜索.找到index位置元素的待插入位置
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年6月25日 下午1:34:10
	 * @param index
	 * @return
	 */
	private int search(int index) {
		int begin = 0;
		int end = index;
		while (begin < end) {
			int mid = (begin + end) >> 1;
			if (cmp(array[index], array[mid]) < 0) {
				end = mid;
			} else {
				begin = mid + 1;
			}
		}
		return begin;
	}

}
