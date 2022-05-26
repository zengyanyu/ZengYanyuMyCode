package com.framework.service;

import com.framework.domain.Employee;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2020年4月18日 下午1:07:05
 */
public interface IEmployeeService {

	/**
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年4月18日 下午1:09:50
	 * @param id
	 * @return
	 */
	Employee get(Long id);

}
