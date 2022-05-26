package com.framework.domain;

import com.framework.core.domain.BaseDomain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 权限的实体对象信息
 * @author ZengYanyu
 * @Description
 * @Date 2020年5月1日 上午11:36:31
 */
@Getter
@Setter
@ToString
public class Permission extends BaseDomain {

	private static final long serialVersionUID = 3817993364440884625L;

	private String name;//权限名称

	private String expression;//权限表达式
}
