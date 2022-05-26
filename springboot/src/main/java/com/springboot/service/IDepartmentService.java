package com.springboot.service;

import java.util.List;

import com.springboot.domain.Department;

public interface IDepartmentService {

	void insert(Department dept);

	List<Department> getDept();
}
