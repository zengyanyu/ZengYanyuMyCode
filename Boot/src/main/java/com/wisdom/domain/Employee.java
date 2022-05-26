package com.wisdom.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@SuppressWarnings("serial")
public class Employee extends BaseDomain {

	private String username;

	private String password;

}
