package com.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.springboot.annotation.RequiredPermission;
import com.springboot.domain.Department;
import com.springboot.domain.Dept;
import com.springboot.service.IDepartmentService;
import com.springboot.service.IDeptService;
import com.springboot.service.IPermissionService;

@Controller
@SuppressWarnings("all")
public class IndexController extends BaseController {

	private final static Log LOG = LogFactory.getLog(IndexController.class);

	@Autowired
	private IDepartmentService departmentService;
	@Autowired
	private IDeptService deptService;
	@Autowired
	private IPermissionService permissionService;

	@RequiredPermission("department")
	@RequestMapping("department")
	@ResponseBody
	public List<Department> department(Model model) {
		//双数据源测试 
		LOG.info("execute IndexController class index method ");
		//第一个数据源信息
		List<Department> depts = departmentService.getDept();
		LOG.info("========================");
		//第二个数据源信息
		List<Dept> list = deptService.get();
		for (Dept dept : list) {
			LOG.info(dept + "");
		}
		return depts;
	}

	@RequiredPermission("dept")
	@RequestMapping("dept")
	@ResponseBody
	public List<Dept> dept(Model model) {
		//双数据源测试 
		List<Dept> list = deptService.get();
		return list;
	}

	@RequiredPermission("bootstrap方法,返回index页面")
	@RequestMapping("bootstrap")
	public String bootstrap() {
		permissionService.loadPermission();
		return "index";
	}

}
