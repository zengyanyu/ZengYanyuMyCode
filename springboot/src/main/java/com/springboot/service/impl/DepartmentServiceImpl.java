package com.springboot.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.domain.Department;
import com.springboot.mapper.DepartmentMapper;
import com.springboot.service.IDepartmentService;

@Service
@Transactional
public class DepartmentServiceImpl implements IDepartmentService {

	@Autowired
	private DepartmentMapper mapper;

	@Override
	public void insert(Department dept) {
		mapper.insert(dept);
	}

	@Override
	public List<Department> getDept() {
		return mapper.selectAll();
	}
}
