package com.springboot.test;

/**
 * 找出元素在数组中的位置(数组元素可能是null)
 * @author ZengYanyu
 * @Description
 * @Date 2020年6月21日 下午12:18:48
 * @see com.wisdom.springboot.test.ElementTest.java
 */
@SuppressWarnings("unchecked")
public class ArrayList<E> {

	private int size;
	private E[] elements;
	//初始化容量
	private static final int DEFAULT_E_CAPACITY = 10;
	//
	private static final int EMELENT_NOT_FOUND = -1;

	public ArrayList() {
		this(DEFAULT_E_CAPACITY);
	}

	public ArrayList(int capacity) {
		capacity = (capacity < DEFAULT_E_CAPACITY) ? DEFAULT_E_CAPACITY : capacity;
		elements = (E[]) new Object[capacity];
	}

	/**
	 * 清空
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年6月21日 下午12:34:27
	 */
	public void clear() {
		for (int i = 0; i < size; i++) {
			elements[i] = null;
		}
		size = 0;
	}

	public int indexOf(E element) {
		//JDK 源码(官方的写法)
		if (element == null) {
			for (int i = 0; i < size; i++) {
				if (elements[i] == null)
					return i;
			} //n + 1
		} else {
			for (int i = 0; i < size; i++) {
				if (element.equals(elements[i]))
					return i;
			} //n + 1
		}

		//方式二
		for (int i = 0; i < size; i++) {
			if (elements[i] == element) //n
				return i;
			if (element != null && element.equals(elements[i])) //2n
				return i;
		} //3n
		return EMELENT_NOT_FOUND;
	}

}
