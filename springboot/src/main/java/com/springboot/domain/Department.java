package com.springboot.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SuppressWarnings("all")
public class Department extends BaseDomain {

	private String username;

	private String password;

	@Override
	public String toString() {
		return "Department [username=" + username + ", password=" + password + "]";
	}

}
