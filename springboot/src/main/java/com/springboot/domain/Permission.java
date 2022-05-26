package com.springboot.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Permission extends BaseDomain{

	private String name;

	private String expression;

}
