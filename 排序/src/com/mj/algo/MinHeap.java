package com.mj.algo;

import java.util.Comparator;

@SuppressWarnings("all")
//小顶堆算法
public class MinHeap<E> {

	private E[] elements;
	
	private static final int DEFAULT_E_CAPACITY = 10;

	private int size;

	private Comparator<E> comparator;

	public MinHeap() {
		this(null, null);
	}

	public MinHeap(Comparator<E> comparator) {
		this(null, comparator);
	}

	public MinHeap(E[] elements) {
		this(elements, null);
	}

	public MinHeap(E[] elements, Comparator<E> comparator) {
		this.comparator = comparator;
		if (comparator == null || elements.length == 0) {
			this.elements = (E[]) new Object[DEFAULT_E_CAPACITY];
		} else {
			int capacity = Math.max(DEFAULT_E_CAPACITY, elements.length);
			this.elements = (E[]) new Object[capacity];
			this.size = elements.length;
			for (int i = 0; i < elements.length; i++) {
				this.elements[i] = elements[i];
			}
			heapify();
		}
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void clear() {
		for (int i = 0; i < size; i++) {
			elements[i] = null;
		}
		size = 0;
	}

	public void add(E element) {
		elementNotNullCheck(element);
		elementCapacity(size + 1);
		elements[size++] = element;
		siftUp(size - 1);
	}

	private void elementCapacity(int i) {

	}

	private void elementNotNullCheck(E element) {

	}

	public E get() {
		emptyCheck();
		return elements[0];
	}

	private void emptyCheck() {

	}

	private void heapify() {
		for (int i = (size >> 1) - 1; i >= 0; i--) {
			siftDown(i);
		}
	}

	private void siftDown(int index) {
		E element = elements[index];

		int halp = size >> 1;
		while (index < halp) {

		}
	}

	private void siftUp(int index) {
		E element = elements[index];
		while (index > 0) {
			int parentIndex = (index - 1) >> 1;
			E parent = elements[parentIndex];
			if (compare(parent, element) >= 0)
				break;
			elements[index] = parent;
			index = parentIndex;
		}
		elements[index] = element;
	}

	private int compare(E parent, E element) {
		return 0;
	}

}
