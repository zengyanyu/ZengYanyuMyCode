package com.framework.service;

import com.framework.domain.Employee;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2020��4��18�� ����1:07:05
 */
public interface IEmployeeService {

	/**
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020��4��18�� ����1:09:50
	 * @param id
	 * @return
	 */
	Employee get(Long id);

}
