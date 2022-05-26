package com.framework.design_patten.adapter;

/**
 * 手机类
 * @author ZengYanyu
 * @Description
 * @Date 2020年4月25日 下午5:37:06
 */
public class Phone {

	public static final int V = 220;//正常电压220V,是一个常量

	public VoltageAdapter adapter;

	//充电
	public void change() {
		adapter.changeVoltage();
	}

	public void setAdapter(VoltageAdapter adapter) {
		this.adapter = adapter;
	}

}
