package com.framework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2020年4月19日 下午1:10:05
 */
@Controller
@SuppressWarnings("all")
@RequestMapping("/Employee")
public class EmployeeController extends BaseController {

	/**
	 * produces属性可以解决后端数据返回前端数据的中文乱码问题
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年4月19日 下午1:10:31
	 */
	@RequestMapping(value = "/toEmployeePage.do", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String toEmployeePage() {
		return "中文到员工";
	}

	/**
	 * @RequestBody 传递的是一个JSON对象
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年5月10日 下午4:28:00
	 * @param username
	 * @return
	 */
	@RequestMapping("/hello.do")
	@ResponseBody
	public String hello(@RequestBody String username) {
		return "helloWord!";
	}

}
