package com.framework.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@SuppressWarnings("serial")
public class Employee extends BaseDomain {

	private String name;

	private Boolean rue;

	private Integer username;

	@Override
	public String toString() {
		return "Employee [name=" + name + ", id=" + this.getId() + ", rue=" + rue + ", username=" + username + "]";
	}

}
