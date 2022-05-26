package com.framework.core.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.framework.annotation.PropertyDesc;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SuppressWarnings("serial")
public class BaseDomain implements Serializable {

	@PropertyDesc(desc = "通用主键ID")
	private Long id;

	@PropertyDesc(desc = "创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date createDate;

	@PropertyDesc(desc = "修改时间")
	private Date updateDate;

	@PropertyDesc(desc = "创建人")
	private Integer createUser;

	@PropertyDesc(desc = "修改人")
	private Integer updateUser;

	/**
	 * toString方法,子类继承本父类的类,子类默认包括所有的toString方法字段(包括父类中的字段)
	 * 子类永远不要覆写toString()
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年5月24日 下午4:57:46
	 * @return
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
