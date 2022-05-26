package com.framework.domain;

import com.framework.core.domain.BaseDomain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SuppressWarnings("serial")
public class SystemMenu extends BaseDomain {

	private String name;

	private String location;

	private String icon;

	private Integer status;

	private Integer sort;

	private String menucode;

	private String menuname;

}