package com.framework.domain;

import com.framework.annotation.PropertyDesc;
import com.framework.core.domain.BaseDomain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@SuppressWarnings("serial")
public class Employee extends BaseDomain {

	@PropertyDesc(desc = "用户名")
	private String username;

	@PropertyDesc(desc = "密码")
	private String password;

	private boolean bool;

}