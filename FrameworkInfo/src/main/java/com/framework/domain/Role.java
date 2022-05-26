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
public class Role extends BaseDomain {

	@PropertyDesc(desc = "角色名称")
	private String name;

	@PropertyDesc(desc = "角色编号")
	private String sn;

	@PropertyDesc(desc = "排序")
	private Integer srl;

}
