package com.springboot.service;

public interface IPermissionService {

	/**
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年6月7日 下午12:45:26
	 */
	void loadPermission();

	/**
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年11月30日 下午12:05:00
	 * @param id
	 * @return
	 */
	int delete(Long id);

}
