package com.springboot.service.impl;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.annotation.RequiredPermission;
import com.springboot.controller.BaseController;
import com.springboot.domain.Permission;
import com.springboot.mapper.PermissionMapper;
import com.springboot.service.IPermissionService;
import com.springboot.service.IRoleService;

@Service
@Transactional
public class PermissionServiceImpl implements IPermissionService, ApplicationContextAware {

	@Autowired
	private PermissionMapper permissionMapper;

	private ApplicationContext applicationContext;

	@Autowired
	private IRoleService roleServiceImpl;

	@Override
	public int delete(Long id) {
		int del = permissionMapper.delete(id);
		roleServiceImpl.delete(1L);
		return del;
	}

	/**
	 * @author ZengYanyu
	 * @Description
	 * @Date 2021年8月8日 下午3:57:55
	 * @param applicationContext
	 * @throws BeansException
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	/**
	 * @author ZengYanyu
	 * @Description
	 * @Date 2021年8月8日 下午3:57:47
	 */
	@Override
	public void loadPermission() {
		List<Permission> permissions = permissionMapper.listAll();
		Set<String> expressions = new HashSet<>();
		for (Permission permission : permissions) {
			expressions.add(permission.getExpression());
		}
		Map<String, BaseController> beansOfMaps = applicationContext.getBeansOfType(BaseController.class);
		Collection<BaseController> values = beansOfMaps.values();
		for (BaseController controller : values) {
			//获取controller的全限定表达式
			String controllerName = controller.getClass().getName();
			//获取到贴了注解的方法
			Method[] methods = controller.getClass().getMethods();
			//获取所有的方法
			for (Method method : methods) {
				//判断方法上是否贴了有@RequiredPermission注解
				if (method.isAnnotationPresent(RequiredPermission.class)) {
					//获取方法名称
					String methodName = method.getName();
					//拼接表达式
					String expression = controllerName + ":" + methodName;
					//判断这个表达式在数据库中是否存在,如果存在,不保存,否则,保存
					if (!expressions.contains(expression)) {
						RequiredPermission requiredPermission = method.getAnnotation(RequiredPermission.class);
						//获取到注解的值
						String name = requiredPermission.value();
						//创建对象,设置属性
						Permission permission = new Permission();
						permission.setName(name);
						permission.setExpression(expression);
						//保存
						permissionMapper.save(permission);
					}
				}
			}
		}
	}

}
