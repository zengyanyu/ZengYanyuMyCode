package com.framework.design_patten.single;

/**
 * 静态内部类
 * @author ZengYanyu
 * @Description
 * @Date 2020年4月25日 下午5:04:14
 */
public class Singleton2 {

	private static class SingletonHoiler {
		private static final Singleton2 INSTANCE = new Singleton2();
	}

	private Singleton2() {
	}

	public static final Singleton2 getInstance() {
		return SingletonHoiler.INSTANCE;
	}
}
