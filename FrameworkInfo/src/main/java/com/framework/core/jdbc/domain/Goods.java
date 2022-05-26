package com.framework.core.jdbc.domain;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Goods {

	private Long id;
	private String name;//名字
	private String brand;//商品名称
	private BigDecimal sal;//商品价格

}
