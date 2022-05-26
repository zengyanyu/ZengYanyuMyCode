package com.mj.sort;

//堆排序
public class HeapSort<E extends Comparable<E>> extends Sort<E> {

	private int heapSize;

	@Override
	protected void sort() {
		//原地建堆
		heapSize = array.length;
		for (int i = (heapSize >> 1) - 1; i >= 0; i--) {
			//下滤操作
			siftDown(i);
		}

		while (heapSize > 1) {
			//交换堆顶元素和尾部元素
			swap(0, --heapSize);

			//对0位置进行siftDown
			siftDown(0);
		}
	}

	//下滤操作
	private void siftDown(int index) {
		E element = array[index];

		int halp = heapSize >> 1;
		while (index < halp) {//index必须是非叶子节点
			//默认是左边跟父节点比
			int childIndex = (index << 1) + 1;
			E child = array[childIndex];

			int rightIndex = childIndex + 1;

			//右子节点比左子节点大
			if (rightIndex < heapSize && cmp(array[rightIndex], child) > 0) {
				child = array[childIndex = rightIndex];
			}

			//大于等于子节点
			if (cmp(element, child) >= 0)
				break;

			array[index] = child;
			index = childIndex;
		}
		array[index] = element;
	}

}
