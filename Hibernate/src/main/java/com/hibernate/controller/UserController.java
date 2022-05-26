package com.hibernate.controller;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hibernate.domain.User;
import com.hibernate.service.IUserService;

@Controller
@SuppressWarnings("all")
public class UserController {

	@Autowired
	private IUserService userService;

	@Autowired
	private JdbcTemplate JdbcTemplate;

	@RequestMapping("/save")
	@ResponseBody
	public String saveUser() {
		User user = new User();
		user.setName("Neld");
		user.setPassword("1234");
		user.setEmail("1194@qq.com");
		user.setBirthday(new Date());

		userService.addUser(user);

		return "success";
	}

	@RequestMapping("/listAll")
	@ResponseBody
	public List<User> listAll() throws Exception {
		BeanInfo beanInfo = Introspector.getBeanInfo(User.class, Object.class);
		PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor pd : pds) {
			String name = pd.getName();
		}
		return userService.get();
	}

	@RequestMapping("/find/{id}")
	@ResponseBody
	public User findOne(@PathVariable Integer id) {
		return userService.find(id);
	}

}
