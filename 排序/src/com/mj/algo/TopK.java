package com.mj.algo;

import java.util.PriorityQueue;

import com.mj.tools.Integers;

public class TopK {

	public static void main(String[] args) {
		Integer[] data = Integers.random(10, 1, 10);
		topK(data, 3);
		topK2(data, 3);
	}

	private static void topK(Integer[] data, int k) {
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		for (int i = 0; i < data.length; i++) {//[3]nlogk  :n表示数组元素规模
			if (queue.size() < k) {
				//offer:表示入堆
				queue.offer(data[i]);//logk
			} else if (data[i] > queue.peek()) {//peek():获取堆顶元素,O(1)级别
				//将堆顶删掉
				queue.poll();//logk
				//表示入堆
				queue.offer(data[i]);//logk
			}
		}
		System.out.println(queue);
	}

	//优化,当堆中没有元素的时候,直接添加(不需要进行判断),当添加到一定量的时候 ,再进行判断操作
	private static void topK2(Integer[] data, int k) {
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		for (int i = 0; i < k; i++) {
			//入堆
			queue.offer(data[i]);
		}
		for (int i = k; i < data.length; i++) {
			if (data[i] <= queue.peek())
				continue;
			//删除
			queue.poll();
			//入堆
			queue.offer(data[i]);
		}
		System.out.println(queue);
	}

}
