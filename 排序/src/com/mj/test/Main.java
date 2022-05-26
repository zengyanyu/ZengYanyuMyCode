package com.mj.test;

import java.util.Arrays;

import com.mj.sort.BubbleSort3;
import com.mj.sort.HeapSort;
import com.mj.sort.SelectionSort;
import com.mj.sort.Sort;
import com.mj.tools.Integers;

@SuppressWarnings("all")
public class Main {

	/*Times.test("bubbleSort1", () -> {
	bubbleSort1(array);
	});
	Times.test("bubbleSort2", () -> {
	bubbleSort2(array);
	});*/
	//new HeapSort().sort(array);

	public static void main(String[] args) {
		Integer[] array = Integers.random(10000, 1, 20000);

		//new SelectionSort().sort(array);
		testSort(array, //
				new HeapSort(), //
				new SelectionSort(), //
				new BubbleSort3() //
		);
	}

	public static void testSort(Integer[] array, Sort... sorts) {
		for (Sort sort : sorts) {
			sort.sort(Integers.copy(array));
		}
		Arrays.sort(array);
		for (Sort sort : sorts) {
			System.out.println(sort);
		}
	}

	//冒泡排序
	static void bubbleSort1(Integer[] array) {
		for (int end = array.length; end > 0; end--) {
			for (int begin = 1; begin < end; begin++) {
				if (array[begin] < array[begin - 1]) {
					//交换
					int tmp = array[begin];
					array[begin] = array[begin - 1];
					array[begin - 1] = tmp;
				}
			}
		}
	}

	//冒泡排序
	static void bubbleSort2(Integer[] array) {
		for (int end = array.length; end > 0; end--) {
			//当交换到某种程度的时候,数组是属于完全有序的,那么就直接结束循环
			boolean sorted = true;
			for (int begin = 1; begin < end; begin++) {
				if (array[begin] < array[begin - 1]) {
					//交换
					int tmp = array[begin];
					array[begin] = array[begin - 1];
					array[begin - 1] = tmp;

					sorted = false;
				}
			}
			if (sorted)
				break;
		}
	}

	/**
	 * 最坏,平均时间复杂度:O(n2)
	 * 最好时间复杂度:O(n)
	 * 空间复杂度:O(1)
	 */
	//冒泡排序
	static void bubbleSort3(Integer[] array) {
		for (int end = array.length; end > 0; end--) {
			//sortedIndex初始值在数组完全有序的时候有用
			int sortedIndex = 1;
			for (int begin = 1; begin < end; begin++) {
				if (array[begin] < array[begin - 1]) {
					//交换
					int tmp = array[begin];
					array[begin] = array[begin - 1];
					array[begin - 1] = tmp;

					//记录一下最后交换的位置(作用:减少交换的次数)
					sortedIndex = begin;
				}
			}
			end = sortedIndex;
		}
	}

	//选择排序
	static void selectionSort(Integer[] array) {
		for (int end = array.length; end > 0; end--) {
			int maxIndex = 0;
			for (int begin = 1; begin < end; begin++) {
				if (array[maxIndex] <= array[begin]) {
					maxIndex = begin;
				}
			}
			//交换
			int tmp = array[maxIndex];
			array[maxIndex] = array[end];
			array[end] = tmp;
		}
	}

}
