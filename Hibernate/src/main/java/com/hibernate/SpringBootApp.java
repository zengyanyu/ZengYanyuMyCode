package com.hibernate;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

@SpringBootApplication
@EnableAutoConfiguration
@EnableTransactionManagement
//@EnableCaching//启动缓存
public class SpringBootApp extends /*SpringBootServletInitializer*/ WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApp.class, args);
	}

	/**
	 * 需要继承于WebMvcConfigurerAdapter对象,覆写configureMessageConverters方法
	 * 消息转换器方法
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年7月11日 下午12:55:23
	 * @param converters
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		super.configureMessageConverters(converters);
		//创建FastJson消息转换器
		FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
		//创建FastJson的配置对象
		FastJsonConfig config = new FastJsonConfig();
		//对json数据进行格式化
		config.setSerializerFeatures(SerializerFeature.PrettyFormat);
		//加入到容器中
		converter.setFastJsonConfig(config);
		converters.add(converter);
	}

	/**
	 * 打包需要的依赖
	 * 启动类继承 SpringBootServletInitializer类,覆写configure方法
	 * <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-tomcat</artifactId>
			<scope>provided</scope>
		</dependency>
	 */
	//打包命令: clean package
	/*@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SpringBootApp.class);
	}*/
}
