package com.framework.design_patten.watching;

/**
 * 老王
 * @author ZengYanyu
 * @Description
 * @Date 2020年4月25日 下午5:13:35
 */
public class LaoWang implements Person {

	private String name = "小王";

	public LaoWang() {
	}

	@Override
	public void getMessage(String msg) {
		System.out.println(name + "接收到小美打过来的电话,电话内容是:" + msg);
	}

}
