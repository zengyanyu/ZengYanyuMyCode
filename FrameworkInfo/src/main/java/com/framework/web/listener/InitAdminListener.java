package com.framework.web.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2020年10月30日 下午6:47:47
 * @see com.framework.web.listener.InitAdminListener.java
 */
public class InitAdminListener implements ApplicationListener<ContextRefreshedEvent> {

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		System.out.println("SSM 程序启动中");
	}
}
