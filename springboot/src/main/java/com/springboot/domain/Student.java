package com.springboot.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student extends BaseDomain {

	private String username;

	private Integer age;

}