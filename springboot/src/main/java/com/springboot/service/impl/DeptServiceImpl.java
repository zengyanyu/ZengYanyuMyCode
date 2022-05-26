package com.springboot.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.domain.Dept;
import com.springboot.mapper1.DeptMapper;
import com.springboot.service.IDeptService;

@Service
@Transactional
public class DeptServiceImpl implements IDeptService {

	@Autowired
	private DeptMapper deptMapper;

	@Override
	public List<Dept> get() {
		return deptMapper.get();
	}

}
