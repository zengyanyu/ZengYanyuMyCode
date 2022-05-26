package com.hibernate.service;

import java.util.List;

import com.hibernate.domain.User;

public interface IUserService {

	void addUser(User user);

	List<User> get();

	User find(Integer id);

}
