package com.framework.design_patten.single;

/**
 * 双重校验锁
 * @author ZengYanyu
 * @Description
 * @Date 2020年4月25日 下午5:08:44
 */
public class Singleton5 {

	private volatile static Singleton5 singleton5;

	private Singleton5() {
	}

	public static Singleton5 getSingleton5() {
		if (singleton5 == null) {
			synchronized (Singleton5.class) {
				singleton5 = new Singleton5();
			}
		}
		return singleton5;
	}

}
