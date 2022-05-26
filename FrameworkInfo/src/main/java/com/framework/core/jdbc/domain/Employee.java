package com.framework.core.jdbc.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Employee {

	private Long id;

	private String username;

	private String password;

	public Employee(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

}
