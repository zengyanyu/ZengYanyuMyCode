package com.wisdom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.wisdom.domain.Employee;
import com.wisdom.service.IEmployeeService;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2020年5月30日 上午9:50:56
 */
@RestController
public class HelloController {

	private final static Log LOG = LogFactory.getLog(HelloController.class);

	@Autowired
	private IEmployeeService employeeService;

	//http://localhost:8080/test
	@RequestMapping("test")
	public String test() {
		return "Hello World!";
	}

	@RequestMapping("test1")
	public List<Employee> test1() {
		return employeeService.getEmp();
	}

}
