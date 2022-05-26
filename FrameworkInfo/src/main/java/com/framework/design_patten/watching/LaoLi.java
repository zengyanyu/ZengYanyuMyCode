package com.framework.design_patten.watching;

/**
 * 小李
 * @author ZengYanyu
 * @Description
 * @Date 2020年4月25日 下午5:15:35
 */
public class LaoLi implements Person {

	private String name = "小李";

	public LaoLi() {

	}

	@Override
	public void getMessage(String msg) {
		System.out.println(name + "接收到小美打过来的电话,电话内容是:" + msg);
	}

}
