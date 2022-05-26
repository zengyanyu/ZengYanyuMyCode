package com.framework.core.jdbc.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Department {

	private Long id;

	private String username;

	private String password;

	public Department(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

}
