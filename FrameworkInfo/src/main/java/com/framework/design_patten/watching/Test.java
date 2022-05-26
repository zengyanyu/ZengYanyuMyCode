package com.framework.design_patten.watching;

public class Test {

	//完美的测试方法
	public static void main(String[] args) {
		XiaoMei xiaoMei = new XiaoMei();

		LaoLi laoLi = new LaoLi();

		LaoWang laoWang = new LaoWang();

		//小王和小李都是小美那里注册了一下
		xiaoMei.addPerson(laoWang);
		xiaoMei.addPerson(laoLi);

		xiaoMei.notifyPerson();
		/**
		 * 小王接收到小美打过来的电话,电话内容是:你们过来吧,谁先过来谁就能陪我一起玩儿游戏!
		 * 小李接收到小美打过来的电话,电话内容是:你们过来吧,谁先过来谁就能陪我一起玩儿游戏!
		 */
	}

}
