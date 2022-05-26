package com.framework.core.jdbc.dao;

import java.util.List;

import com.framework.core.jdbc.domain.Department;
import com.framework.core.jdbc.query.DepartmentQueryObject;
import com.framework.core.page.PageResult;

public interface IDepartmentDao {

	void save(Department department);

	void delete(Long id);

	void update(Department department);

	Department get(Long id);

	List<Department> getAll();

	PageResult query(DepartmentQueryObject qo);

	Long getTotalCount();

}
