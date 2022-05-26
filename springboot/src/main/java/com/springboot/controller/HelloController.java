package com.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.springboot.annotation.RequiredPermission;

@Controller
public class HelloController extends BaseController {

	private final static Log LOG = LogFactory.getLog(HelloController.class);

	@RequiredPermission("欢迎方法")
	@RequestMapping("hello")
	@ResponseBody
	public String hello() {
		LOG.info("execute HelloController class hello method ");
		return "hello World!";
	}

}
