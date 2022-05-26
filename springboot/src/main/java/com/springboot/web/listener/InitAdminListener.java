package com.springboot.web.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.springboot.service.ILoginInfoService;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2020年5月31日 下午10:07:05
 */
@Component
public class InitAdminListener implements ApplicationListener<ContextRefreshedEvent> {

	private final Log LOG = LogFactory.getLog(InitAdminListener.class);

	@Autowired
	private ILoginInfoService loginInfoServiceImpl;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		LOG.info("spingboot 程序启动中--loggingInfo");
		//判断系统管理员是否存在
		loginInfoServiceImpl.countByUsername("admin");
	}

}
