package com.framework.domain;

import com.framework.core.domain.BaseDomain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 属性描述信息
 * @author ZengYanyu
 * @Description
 * @Date 2020年5月10日 下午12:45:58
 */
@Getter
@Setter
@ToString
@SuppressWarnings("serial")
public class PropertyDescInfo extends BaseDomain {

	private Class<?> clazz;

	private String fieldName;//字段名称

	private String desc;//描述信息

	private String length;//长度

}
