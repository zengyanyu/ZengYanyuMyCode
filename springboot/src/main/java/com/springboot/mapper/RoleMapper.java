package com.springboot.mapper;

import java.util.List;

import com.springboot.domain.Role;
import com.springboot.query.RoleQueryObject;

public interface RoleMapper {

	int delete(Long id);

	List<Role> queryForListData(RoleQueryObject qo);

	Integer queryForTotalCount(RoleQueryObject qo);

}
