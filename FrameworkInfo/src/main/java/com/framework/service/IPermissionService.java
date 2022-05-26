package com.framework.service;

import java.util.List;

import com.framework.domain.Permission;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2020年5月1日 上午11:39:01
 */
public interface IPermissionService {

	/**
	 * 加载权限
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年5月1日 上午11:40:32
	 */
	void reloadPermission();

	List<Permission> getAll();

}
