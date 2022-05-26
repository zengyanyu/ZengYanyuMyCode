package com.mj.sort;

/**
 * 插入排序
 * @author ZengYanyu
 * @Description
 * @Date 2020年6月25日 下午1:06:02
 * @param <E>
 * @see com.mj.sort.InsertionSort2.java
 */
public class InsertionSort2<E extends Comparable<E>> extends Sort<E> {

	//插入排序的时间复杂度与逆序对的数量成正比关系
	//逆序对的数据越多,插入排序的时间复杂度越高
	@Override
	protected void sort() {
		//优化[思路:将(交换)转为(挪动)]
		for (int begin = 1; begin < array.length; begin++) {
			int current = begin;
			//1.先将待插入元素备份
			E v = array[current];
			while (current > 0 && cmp(v, array[current - 1]) < 0) {
				//2.头部有序数据中比待插入元素大的.都朝尾部方向挪动1个位置
				array[current] = array[current - 1];
				current--;
			}
			//3.将待插入元素放到最终的合适位置
			array[current] = v;
		}
	}

}
