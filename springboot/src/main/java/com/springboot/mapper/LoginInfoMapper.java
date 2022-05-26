package com.springboot.mapper;

import java.util.List;

import com.springboot.domain.LoginInfo;

public interface LoginInfoMapper {
	int deleteByPrimaryKey(Long id);

	int insert(LoginInfo record);

	LoginInfo selectByPrimaryKey(Long id);

	List<LoginInfo> selectAll();

	int updateByPrimaryKey(LoginInfo record);

	int countByUsername(String username);
}