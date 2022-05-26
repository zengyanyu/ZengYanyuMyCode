package com.framework.design_patten.adapter;

/**
 * 变压器
 * @author ZengYanyu
 * @Description
 * @Date 2020年4月25日 下午5:38:32
 */
public class VoltageAdapter {

	public void changeVoltage() {
		System.out.println("正常充电...");
		System.out.println("原始电压:" + Phone.V + "V");
		System.out.println("经过变压器转换之后的电压:" + (Phone.V - 200) + "V");
	}

}
