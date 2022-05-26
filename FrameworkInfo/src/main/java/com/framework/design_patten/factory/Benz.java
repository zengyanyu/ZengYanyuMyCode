package com.framework.design_patten.factory;

/**
 * 具体实现类
 * @author ZengYanyu
 * @Description
 * @Date 2020年4月25日 下午5:48:49
 */
public class Benz implements Car {

	@Override
	public void run() {
		System.out.println("Benz开始启动了......");
	}

	@Override
	public void stop() {
		System.out.println("Benz停车了......");
	}

}
