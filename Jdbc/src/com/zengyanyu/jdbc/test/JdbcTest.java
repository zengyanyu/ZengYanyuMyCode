package com.zengyanyu.jdbc.test;

import org.junit.Test;

import com.zengyanyu.jdbc.dao.IDepartmentDao;
import com.zengyanyu.jdbc.dao.impl.DepartmentDaoImpl;
import com.zengyanyu.jdbc.domain.Department;

public class JdbcTest {

	private IDepartmentDao dao = new DepartmentDaoImpl();

	@Test
	public void testMethod() {
		Department department = dao.get(1L);
		System.out.println(department);
	}
}
