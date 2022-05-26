package com.framework.design_patten.single;

/**
 * 单例
 * @author ZengYanyu
 * @Description
 * @Date 2020年4月25日 下午4:59:11
 */
public class Singleton {

	private static Singleton singleton;

	private Singleton() {
	}

	/**
	 * 线程安全的懒汉式写法
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年4月25日 下午5:01:30
	 * @return
	 */
	public static synchronized Singleton getInstance() {
		if (singleton == null) {
			singleton = new Singleton();
		}
		return singleton;
	}

}
