package com.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @Configuration标签,把一个类标记为spring的配置对象
 * @author ZengYanyu
 * @Description
 * @Date 2020年6月13日 下午11:06:50
 */
@Configuration
public class Config {

	@Bean
	//配置作用域的模式(默认值为单例,设置为多实例模式)
	@Scope("prototype")
	public SomeBean someBean() {
		return new SomeBean();
	}

}
