package com.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.springboot.domain.Role;
import com.springboot.page.PageResult;
import com.springboot.query.RoleQueryObject;
import com.springboot.service.IRoleService;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2021年10月5日 上午10:07:21
 */
@RestController
public class RoleController extends StdRoleController {

	private final static Log LOG = LogFactory.getLog(RoleController.class);

	@Autowired
	private IRoleService roleService;

	/**
	 * @author ZengYanyu
	 * @Description
	 * @Date 2021年10月5日 上午10:07:16
	 * @param id
	 */
	@RequestMapping("/role/{id}")
	public void delete(@PathVariable Long id) {
		roleService.delete(id);
	}

	@RequestMapping("/getRole")
	public PageResult<Role> pageResult() {
		RoleQueryObject qo = new RoleQueryObject();
		PageResult<Role> pageQuery = roleService.pageQuery(qo);
		LOG.info(pageQuery.getListData() + "");
		return pageQuery;
	}

}
