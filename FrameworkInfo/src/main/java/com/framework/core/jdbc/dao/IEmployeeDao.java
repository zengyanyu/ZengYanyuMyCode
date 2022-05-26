package com.framework.core.jdbc.dao;

import java.util.List;

import com.framework.core.jdbc.domain.Employee;
import com.framework.core.jdbc.query.EmployeeQueryObject;
import com.framework.core.page.PageResult;

public interface IEmployeeDao {

	void save(Employee employee);

	void delete(Long id);

	void update(Employee employee);

	Employee get(Long id);

	List<Employee> getAll();

	PageResult query(EmployeeQueryObject qo);

	Long getTotalCount();

}
