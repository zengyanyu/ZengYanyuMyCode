package com.springboot.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.springboot.domain.Role;
import com.springboot.page.PageResult;
import com.springboot.query.RoleQueryObject;
import com.springboot.service.IRoleService;

public class RoleTest {

	@Autowired
	private IRoleService roleService;

	@Test
	public void roleTest() {
		RoleQueryObject qo = new RoleQueryObject();
		PageResult<Role> pageQuery = roleService.pageQuery(qo);
		System.out.println(pageQuery);
	}

}
