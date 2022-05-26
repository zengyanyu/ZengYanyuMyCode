package com.wisdom.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Qualifier;

import com.wisdom.domain.Employee;

@Qualifier("db1SqlSessionFactory")
public interface EmployeeMapper {

	@Select("SELECT * FROM employee")
	List<Employee> getEmp();

}
