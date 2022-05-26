package com.wisdom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisdom.domain.Employee;
import com.wisdom.mapper.EmployeeMapper;
import com.wisdom.service.IEmployeeService;

@Service(value = "employeeService")
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired(required = true)
	private EmployeeMapper employeeMapper;

	@Override
	public List<Employee> getEmp() {
		return employeeMapper.getEmp();
	}

}
