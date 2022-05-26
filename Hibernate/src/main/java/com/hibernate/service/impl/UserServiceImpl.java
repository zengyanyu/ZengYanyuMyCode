package com.hibernate.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hibernate.dao.UserDao;
import com.hibernate.domain.User;
import com.hibernate.service.IUserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserDao userDao;

	@Override
	public void addUser(User user) {
		//userDao.save(user);
	}

	//redis缓存注解
	//@Cacheable(value = "myUsers")//这个value是键值对的key,查询出来的列表信息是键值对的value
	@Override
	public List<User> get() {
		User user =new User();
		user.setId(2);
		Example<User> users = Example.of(user);
		return userDao.findAll(users);
	}

	@Override
	public User find(Integer id) {
		return userDao.findOne(id);
	}

}
