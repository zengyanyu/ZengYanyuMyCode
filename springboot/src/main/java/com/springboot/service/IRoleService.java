package com.springboot.service;

import com.springboot.domain.Role;
import com.springboot.page.PageResult;
import com.springboot.query.RoleQueryObject;

public interface IRoleService {

	/**
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年11月30日 下午12:05:35
	 * @param id
	 * @return
	 */
	int delete(Long id);

	/**
	 * @author ZengYanyu
	 * @Description
	 * @Date 2021年10月5日 下午6:01:52
	 * @param qo
	 * @return
	 */
	PageResult<Role> pageQuery(RoleQueryObject qo);

}
