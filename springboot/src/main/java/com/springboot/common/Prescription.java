package com.springboot.common;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 实体对象信息
 * @author ZengYanyu
 * @Description
 * @Date 2020年7月18日 下午5:52:59
 * @see com.springboot.common.Prescription.java
 */
@SuppressWarnings("serial")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Prescription implements Serializable {

	/**
	 * 开处方时间
	 */
	//对象中的字段名称必须和JSON字符串指定的名称一致;
	private String PrescriptionTime;

}
