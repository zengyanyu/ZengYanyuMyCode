package com.springboot.domain;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person extends BaseDomain {

	private Long id;

	private String username;

	//com.alibaba.fastjson.annotation.JSONField
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date date;

	@Override
	public String toString() {
		return "Person [id=" + id + ", username=" + username + ", date=" + date + "]";
	}

}
