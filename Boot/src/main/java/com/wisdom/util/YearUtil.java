package com.wisdom.util;

/**
 * 年份工具类
 * @author ZengYanyu
 * @Description
 * @Date 2020年10月26日 下午10:31:31
 * @see com.wisdom.util.YearUtil.java
 */
public class YearUtil {

	private YearUtil() {
	}

	/**
	 * 判断一个数是否是闰年
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年10月26日 下午10:25:45
	 * @param year   年份(int)
	 * @return       返回是否为闰年的一个boolea值,{闰年:true,不是闰年:false}
	 */
	public static boolean init(int year) {
		//1、普通闰年:公历年份是4的倍数的，一般是闰年。（如2004年就是闰年）；
		//2、世纪闰年:公历年份是整百数的，必须是400的倍数才是闰年（如1900年不是世纪闰年，2000年是世纪闰年）
		if (year % 400 == 0 || year % 4 != 0) {//不是闰年
			return false;
		}
		return true;
	}

	//test code
	public static void main(String[] args) {
		System.out.println("闰年状态  = " + init(2004));
	}

}
