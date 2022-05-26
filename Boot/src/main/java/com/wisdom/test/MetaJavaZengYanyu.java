package com.wisdom.test;

import java.util.Map;
import java.util.Set;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2020年11月23日 下午6:58:05
 * @see com.wisdom.test.MetaJavaZengYanyu.java
 */
public class MetaJavaZengYanyu {

	public static String qn0 = "AD";

	public static String qn1 = "B";

	public static String qn2 = "E";

	public static String qn3 = "BC";

	public static String qn4 = "C";

	public static String qn5 = "C";

	public static String qn6 = "B";

	public static String qn7 = "C";

	public static String qn8 = "C";

	public static String qn9 = "D";

	public static String qn10 = "D";

	public static String qn11 = "C";

	public static String qn12 = "D";

	public static String qn13 = "D";

	public static String qn14 = "C";

	public static String qn15 = "A";

	/*
	 * 以下是实现题
	 */
	/**
	 * <b>注意! 本题不要遍历二维数组. 要求时间复杂度严格低于n^2, 否则视为不得分 </b>
	 *
	 * 现有一个n*n的二维正整数数组nums，每行元素保证递增，每列元素保证递增，求某正整数x是否存在于该二维数组中，需要尽量优化时间和空间复杂度；
	 * @param int[][] nums
	 * @param int x 目标数
	 * @return boolean
	 */
	public static boolean searchMatrix(int[][] nums, int x) {
		// Todo your code goes here...
		return false;
	}

	/**
	* 对任意一个Map<String, Object>, 其 key 为 String,
	* 其 value 为 Map<String, Object> Object[] Number String 中的任意一种,
	* 显然叶子节点是 value 类型为 Number 或 String的节点,
	* 将 Map 转为多条字符串, 每条字符串表达其中一个叶子节点,
	* 比如:
	* {"a":{"b":["v",2,{"c":0}]},"d":[1,null,3]}
	* 将转化为以下这些字符串
	* a.b[0] = v
	* a.b[1] = 2
	* a.b[2].c = 0
	* d[0] = 1
	* d[1] = null
	* d[2] = 3
	*
	* @param map 上述的 map
	* @return 所有的字符串
	*/
	public static Set<String> showMap(Map<String, Object> map) {
		//TODO your code goes here...
		return null;
	}

	/**
	 * 给定一个二叉树, 检查它是否是镜像对称的
	 * 例如以下是镜像对称的
	 *      1
	 *     / \
	 *    2   2
	 *   / \ / \
	 *  3  4 4  3
	 *
	 * 下面这个则不是镜像对称的
	 *      1
	 *     / \
	 *    2   2
	 *     \   \
	 *      3   3
	 *
	 * TreeNode类的定义:
	 *
	 * @param TreeNode 一颗二叉树
	 * @return boolean 是否是对称的
	 */

	// 以下给出TreeNode类, 请勿修改
	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public static boolean isTreeSymmetric(TreeNode root) {
		return false;
	}
}
