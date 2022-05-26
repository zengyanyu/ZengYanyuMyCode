package com.framework.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.jws.WebService;

import com.framework.domain.Weather;
import com.framework.service.IWeatherService;

//添加@WebService对应的注解
@WebService
public class WeatherServiceImpl implements IWeatherService {

	public static Map<String, Weather> map = new HashMap<>();

	//初始化数据
	static {
		map.put("bj", new Weather(1L, "北京", "20~25", "晴转多云"));
		map.put("gz", new Weather(2L, "广州", "30~35", "台风多雨"));
		map.put("sz", new Weather(3L, "深圳", "35~40", "局部有雨"));
	}

	@Override
	public Weather getWeatherByCity(String city) {
		return map.get(city);
	}

}