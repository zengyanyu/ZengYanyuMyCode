package com.springboot.mapper;

import java.util.List;

import com.springboot.domain.Department;

public interface DepartmentMapper {
	int deleteByPrimaryKey(Long id);

	int insert(Department record);

	Department selectByPrimaryKey(Long id);

	List<Department> selectAll();

	int updateByPrimaryKey(Department record);
}