package com.framework.design_patten.single;

/**
 * 饿汉式单例
 * @author ZengYanyu
 * @Description
 * @Date 2020年4月25日 下午5:03:22
 */
public class Singleton1 {

	private static Singleton1 singleton1 = new Singleton1();

	private Singleton1() {
	}

	/**
	 * 线程安全的懒汉式写法
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年4月25日 下午5:01:30
	 * @return
	 */
	public static Singleton1 getInstance() {
		return singleton1;
	}
}
