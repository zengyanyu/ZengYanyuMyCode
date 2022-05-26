package com.framework.mapper;

import java.util.List;

import com.framework.domain.SystemMenu;

public interface SystemMenuMapper {
	int deleteByPrimaryKey(Long id);

	int insert(SystemMenu record);

	SystemMenu selectByPrimaryKey(Long id);

	List<SystemMenu> selectAll();

	int updateByPrimaryKey(SystemMenu record);
}