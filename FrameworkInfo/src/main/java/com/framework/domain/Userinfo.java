package com.framework.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.framework.core.domain.BaseDomain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@SuppressWarnings("serial")
public class Userinfo extends BaseDomain {

	private String username;

	private String password;

	//后台返回JSON数据有时间的时候使用JsonFormat
	//设置时间为东八区
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	//前台往后台传日期参数需要使用
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date inputTime;

	public Userinfo(String username, String password, Date inputTime) {
		super();
		this.username = username;
		this.password = password;
		this.inputTime = inputTime;
	}

}
