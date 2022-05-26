package com.mj.sort;

//归并排序
public class MergeSort<E extends Comparable<E>> extends Sort<E> {

	@Override
	protected void sort() {
		sort(0, array.length);
	}

	/**
	 * 对 [begin,end] 范围内的数据进行归并排序
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年6月25日 下午2:24:28
	 * @param begin
	 * @param end
	 */
	private void sort(int begin, int end) {
		if (end - begin < 2)
			return;
		int mid = (begin + end) >> 1;
		sort(begin, mid);
		sort(mid, end);
		merge(begin, mid, end);
	}

	/**
	 * 将 [begin,mid) 和[mid,end) 范围的序列合并成一个有序序列
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年6月25日 下午2:25:12
	 * @param begin
	 * @param mid
	 * @param end
	 */
	private void merge(int begin, int mid, int end) {
		//TODO
	}

}
