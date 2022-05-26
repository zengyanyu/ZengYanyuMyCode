package com.springboot.service;

public interface ILoginInfoService {

	int countByUsername(String username);

	int save(String username, String password);

}
