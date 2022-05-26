package com.framework.core.jdbc.test;

import org.junit.Test;

import com.framework.core.jdbc.dao.IDepartmentDao;
import com.framework.core.jdbc.dao.impl.DepartmentDaoImpl;

public class DepartmentTest {

	private IDepartmentDao dao = new DepartmentDaoImpl();

	@Test
	public void testToalCount() {
		Long totalCount = dao.getTotalCount();
		System.out.println(totalCount);
	}

}
