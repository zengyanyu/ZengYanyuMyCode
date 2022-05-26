package com.framework.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 天气提供商
 * @author ZengYanyu
 * @Description
 * @Date 2020年11月4日 下午3:48:24
 * @see com.wisdom.springboot.domain.Weather.java
 */
@Getter
@Setter
@AllArgsConstructor
public class Weather {

	private Long id;

	private String city;//城市

	private String temperatrue;//温度

	private String condition;//天气情况

}