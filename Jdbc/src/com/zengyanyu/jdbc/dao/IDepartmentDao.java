package com.zengyanyu.jdbc.dao;

import java.util.List;

import com.zengyanyu.jdbc.domain.Department;
import com.zengyanyu.jdbc.page.PageResult;
import com.zengyanyu.jdbc.query.DepartmentQueryObject;

public interface IDepartmentDao {

	void save(Department department);

	void delete(Long id);

	void update(Department department);

	Department get(Long id);

	List<Department> getAll();

	PageResult query(DepartmentQueryObject qo);

	Long getTotalCount();

}
