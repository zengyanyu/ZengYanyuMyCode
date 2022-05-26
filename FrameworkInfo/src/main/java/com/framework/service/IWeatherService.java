package com.framework.service;

import javax.jws.WebService;

import com.framework.domain.Weather;

@WebService
public interface IWeatherService {

	Weather getWeatherByCity(String city);

}
