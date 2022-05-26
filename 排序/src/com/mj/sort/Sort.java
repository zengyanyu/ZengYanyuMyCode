package com.mj.sort;

import java.text.DecimalFormat;

//所有排序的父类(抽象类)
@SuppressWarnings("all")
public abstract class Sort<E extends Comparable<E>> implements Comparable<Sort<E>> {

	protected E[] array;

	private int cmpCount;//比较次数
	private int swapCount;//交换次数
	private long time;

	/**
	 * 格式化
	 */
	private DecimalFormat fmt = new DecimalFormat("#.00");

	public void sort(E[] array) {
		//如果你的组数只有一个元素,或者没有元素,那就不进行排序了
		if (array == null || array.length < 2)
			return;

		this.array = array;

		long begin = System.currentTimeMillis();
		sort();
		time = System.currentTimeMillis() - begin;
	}

	/**
	 * 排序
	 */
	protected abstract void sort();

	/**
	 */
	protected int cmp(int i1, int i2) {
		cmpCount++;
		return array[i1].compareTo(array[i2]);
	}

	/**
	 */
	protected int cmp/*Elements*/(E v1, E v2) {
		cmpCount++;
		return v1.compareTo(v2);
	}

	protected void swap(int i1, int i2) {
		swapCount++;
		E tmp = array[i1];
		array[i1] = array[i2];
		array[i2] = tmp;
	}

	@Override
	public int compareTo(Sort<E> o) {
		return (int) (time - o.time);
	}

	@Override
	public String toString() {
		String timeStr = "时间: " + (time / 1000.0) + "s(" + time + "ms)";
		String comparaCountStr = "比较次数: " + numberString(cmpCount);
		String swapCountStr = "交换次数: " + numberString(swapCount);
		String stableStr = "稳定性: " + isStable();
		return getClass().getSimpleName() + "\n"//
				+ stableStr + "\t"//
				+ timeStr + "\t"//
				+ comparaCountStr + "\t"//
				+ swapCountStr + "\n" + "---------------------------";
	}

	//稳定性
	public boolean isStable() {
		Student[] students = new Student[20];
		for (int i = 0; i < students.length; i++) {
			students[i] = new Student(i * 10, 20);
		}
		sort((E[]) students);
		for (int i = 1; i < students.length; i++) {
			int score = students[i].score;
			int prevScore = students[i - 1].score;
			if (score != prevScore + 10)
				return false;
		}
		return true;
	}

	private String numberString(int number) {
		if (number < 1000)
			return "" + number;
		if (number < 100000000)
			return fmt.format(number / 10000.0) + "万";
		return fmt.format(number / 100000000.0) + "亿";
	}

}
