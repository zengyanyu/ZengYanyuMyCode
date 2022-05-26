package com.mj.tools;

//工具类
public class Integers extends AbstractIntegers {

	/**
	 * 生成随机数
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年6月25日 下午2:49:22
	 * @param count  总数
	 * @param min    最小
	 * @param max    最大
	 * @return
	 */
	public static Integer[] random(int count, int min, int max) {
		if (count <= 0 || min > max)
			return null;
		Integer[] array = new Integer[count];
		int delta = max - min + 1;
		for (int i = 0; i < count; i++) {
			array[i] = min + (int) (Math.random() * delta);
		}
		return array;
	}

	/*
	 * 拷贝元素
	 */
	public static Integer[] copy(Integer[] array) {
		return array;
	}

	/*
	 * combine: 结合
	 */
	public static Integer[] combine(Integer[] array1, Integer[] array2) {
		if (array1 == null || array2 == null)
			return null;
		Integer[] array = new Integer[array1.length + array2.length];
		for (int i = 0; i < array1.length; i++) {
			array[i] = array1[i];
		}
		for (int i = 0; i < array2.length; i++) {
			array[i + array1.length] = array2[i];
		}
		return array;
	}

	/*
	 * count:设置Integer数组的个数
	 * unsameCount:设置不相同的个数,不相同的个数必须小于等于组数个数(unsameCount<=count)
	 */
	public static Integer[] same(int count, int unsameCount) {
		if (count <= 0 || unsameCount > count)
			return null;
		Integer[] array = new Integer[count];
		for (int i = 0; i < unsameCount; i++) {
			array[i] = unsameCount - i;
		}
		for (int i = unsameCount; i < count; i++) {
			array[i] = unsameCount + 1;
		}
		return array;
	}

	/*
	 * 头尾升序
	 * disorderCount:无序计数
	 */
	public static Integer[] headTailAscOrder(int min, int max, int disorderCount) {
		Integer[] array = ascOrder(min, max);
		if (disorderCount > array.length)
			return array;
		int begin = (array.length - disorderCount) >> 1;
		//reverse:颠倒
		reverse(array, begin, begin + disorderCount);
		return array;
	}

	/*
	 * 中间升序
	 */
	public static Integer[] conterAscOrder(int min, int max, int disorderCount) {
		Integer[] array = ascOrder(min, max);
		if (disorderCount > array.length)
			return array;
		int left = disorderCount >> 1;
		reverse(array, 0, left);
		int right = disorderCount - left;
		reverse(array, array.length - right, array.length);
		return array;
	}

	/*
	 * 颠倒
	 */
	private static void reverse(Integer[] array, int begin, int end) {
		int delta = end - begin;
		Integer[] copyArr = new Integer[delta];
		//旧的数组先进行备份
		int index = 0;
		for (int i = begin; i < end; i++) {
			copyArr[index] = array[i];
			index++;
		}
		for (int i = 0; i < delta; i++) {
			//从新的组数中进行取值,赋值给原来数组的位置 copyArr[delta - 1 - i]
			array[begin + i] = copyArr[delta - 1 - i];
		}
	}

	/*
	 * 升序
	 */
	public static Integer[] ascOrder(int min, int max) {
		if (min > max)
			return null;
		Integer[] array = new Integer[max - min + 1];
		for (int i = 0; i < array.length; i++) {
			array[i] = min + i;
		}
		return array;
	}

	/**
	 * 升序
	 * @author ZengYanyu
	 * @Description
	 * @Date 2021年1月20日 下午4:53:35
	 * @param count  总数
	 * @param min    最小值
	 * @param step   步长(必须大于0)
	 * @return
	 */
	public static Integer[] ascOrder(int count, int min, int step) {
		if (count <= 0 || min < 0 || step <= 0)
			return null;
		Integer[] array = new Integer[count];
		for (int i = 0; i < count; i++) {
			array[i] = min + i * step;
		}
		return array;
	}

	/**
	 * 如果没有步长的ascOrder算法,默认步长为1
	 * @author ZengYanyu
	 * @Description
	 * @Date 2021年1月20日 下午4:55:29
	 * @param count
	 * @param min
	 * @return
	 */
	public static Integer[] ascOrderDefaultStep1(int count, int min) {
		if (count <= 0 || min < 0)
			return null;
		Integer[] array = new Integer[count];
		for (int i = 0; i < count; i++) {
			array[i] = min + i;
		}
		return array;
	}

	public static void main(String[] args) {
		Integer[] ascOrder = ascOrder(10, 15);
		//Integers.println(ascOrder);
		Integer[] headTailAscOrder = headTailAscOrder(10, 20, 5);
		Integers.println(headTailAscOrder);
		System.out.println("");
		Integer[] conterAscOrder = conterAscOrder(10, 30, 10);
		Integers.println(conterAscOrder);
		//退出进程
		System.exit(0);
	}

}
