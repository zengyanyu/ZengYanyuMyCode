package com.springboot.mapper;

import java.util.List;

import com.springboot.domain.Permission;

public interface PermissionMapper {

	void save(Permission permission);

	List<Permission> listAll();

	int delete(Long id);

}
