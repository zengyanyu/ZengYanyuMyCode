package com.framework.core.jdbc.test;

import java.util.List;

import org.junit.Test;

import com.framework.core.jdbc.dao.IEmployeeDao;
import com.framework.core.jdbc.dao.impl.EmployeeDaoImpl;
import com.framework.core.jdbc.domain.Employee;
import com.framework.core.jdbc.query.EmployeeQueryObject;
import com.framework.core.page.PageResult;

public class EmployeeTest {

	private IEmployeeDao dao = new EmployeeDaoImpl();

	@Test
	public void testTotalCount() {
		Long totalCount = dao.getTotalCount();
		System.out.println(totalCount);
	}

	@Test
	public void testGet() {
		Employee employee = dao.get(100L);
		System.out.println(employee);
	}

	@SuppressWarnings("unused")
	@Test
	public void testGetAll() {
		long start = System.currentTimeMillis();
		List<Employee> all = dao.getAll();
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}

	@Test
	public void testQueryPage() {
		EmployeeQueryObject qo = new EmployeeQueryObject();
		qo.setUsername("1111");
		qo.setPassword("1111");
		PageResult page = dao.query(qo);
		System.out.println(page);
	}

	@Test
	public void testSave() {
		Employee employee = new Employee("888", "888");
		dao.save(employee);
	}

}
