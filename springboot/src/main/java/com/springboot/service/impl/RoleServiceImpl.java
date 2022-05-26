package com.springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.domain.Role;
import com.springboot.mapper.RoleMapper;
import com.springboot.page.PageResult;
import com.springboot.query.RoleQueryObject;
import com.springboot.service.IRoleService;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2020年11月30日 下午12:06:36
 * @see com.springboot.service.impl.RoleServiceImpl.java
 */
@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private RoleMapper roleMapper;

	@Override
	public int delete(Long id) {
		int delete = roleMapper.delete(id);
		//模拟异常处理
		int i = 1 / 0;
		return delete;
	}

	/**
	 * 总结:调用方法的入口,如果贴了有注解 ,那么后面的方法都是属于支持事务的.如果在调用方法的入口没有
	 * 贴事务的注解,在这个方法中出现异常,是不做正常处理的.后面有贴这个事务的注解,才会处理正常的事务信息
	 */

	@Override
	public PageResult<Role> pageQuery(RoleQueryObject qo) {
		List<Role> roles = roleMapper.queryForListData(qo);
		if (roles.size() == 0) {
			return PageResult.EMPTY_LIST(qo.getPageSize());
		}
		Integer totalCount = roleMapper.queryForTotalCount(qo);
		return new PageResult(roles, totalCount, qo.getCurrentPage(), qo.getPageSize());
	}

}
