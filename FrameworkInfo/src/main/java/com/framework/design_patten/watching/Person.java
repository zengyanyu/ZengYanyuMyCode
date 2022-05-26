package com.framework.design_patten.watching;

/**
 * 观察者模式
 * @author ZengYanyu
 * @Description
 * @Date 2020年4月25日 下午5:12:15
 */
public interface Person {

	//小王和小李通过这个接口可以接收到小美发过来的消息
	void getMessage(String msg);

}
