package com.mj.tools;

/**
 * 断言
 * @author ZengYanyu
 * @Description
 * @Date 2020年6月21日 下午5:56:39
 * @see com.mj.tools.Asserts.java
 */
public class Asserts {

	public static void test(boolean value) {
		try {
			if (!value)
				throw new Exception("测试未通过");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
