package com.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.domain.LoginInfo;
import com.springboot.mapper.LoginInfoMapper;
import com.springboot.service.ILoginInfoService;

@Service
//@Transactional
public class LoginInfoServiceImpl implements ILoginInfoService {

	@Autowired
	private LoginInfoMapper loginInfoMapper;

	@Override
	public int countByUsername(String username) {
		int count = loginInfoMapper.countByUsername(username);
		if (count == 0) {
			//用户名不存在
			//创建对象,设置属性
			//			LoginInfo loginInfo = new LoginInfo();
			//			loginInfo.setUsername("admin");
			//			loginInfo.setPassword("admin");
			//			loginInfoMapper.insert(loginInfo);
			save("admin", "admin");
		}
		return 0;
	}

	@Override
	public int save(String username, String password) {
		try {
			//创建对象,设置对象
			LoginInfo loginInfo = new LoginInfo();
			loginInfo.setUsername(username);
			loginInfo.setPassword(password);
			loginInfoMapper.insert(loginInfo);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			//TODO
			return 0;
		}
	}

}
