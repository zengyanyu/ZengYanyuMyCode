package com.springboot.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

//@WebListener
public class TestListener implements ServletContextListener {

	/**
	 * 初始化
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年6月14日 下午1:46:29
	 * @param arg0
	 */
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("listener启动了....");
	}

	/**
	 * 销毁
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年6月14日 下午1:46:35
	 * @param arg0
	 */
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("listener销毁了....");
	}

}
