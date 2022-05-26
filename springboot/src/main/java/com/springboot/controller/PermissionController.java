package com.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.service.IPermissionService;

@RestController
public class PermissionController extends BaseController {

	@Autowired
	private IPermissionService permissionServiceImpl;

	/**
	 * @author ZengYanyu
	 * @Description
	 * @Date 2021年8月8日 下午3:56:51
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		permissionServiceImpl.delete(id);
		return "删除成功";
	}
	
	/**
	 * @author ZengYanyu
	 * @Description
	 * @Date 2021年8月8日 下午3:56:12
	 */
	@RequestMapping("/loadPermission")
	public void loadPermission(){
		permissionServiceImpl.loadPermission();
	}

}
