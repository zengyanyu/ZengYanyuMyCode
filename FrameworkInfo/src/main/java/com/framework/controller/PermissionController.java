package com.framework.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.framework.domain.Permission;
import com.framework.service.IPermissionService;

@RestController
public class PermissionController {

	@Autowired
	private IPermissionService permissionService;

	@RequestMapping("/getPermission")
	public List<Permission> getAll() {
		return permissionService.getAll();
	}

}
