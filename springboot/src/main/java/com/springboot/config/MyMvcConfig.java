package com.springboot.config;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.springboot.web.interceptor.MyInterceptor;

/**
 * 注册拦截器
 * @author ZengYanyu
 * @Description
 * @Date 2020年6月14日 下午3:16:35
 * @see com.springboot.config.MyMvcConfig.java
 */
@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {

	@Bean
	public MyInterceptor myInterceptor() {
		return new MyInterceptor();
	}

	/**
	 * 注册拦截器
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年6月6日 下午6:57:50
	 * @param registry
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//拦截所有的请求
		registry.addInterceptor(myInterceptor()).addPathPatterns("/*");
	}

	//==============配置消息转换器的方式======================================

	/**
	 * 配置消息转换器
	 * 需要继承于WebMvcConfigurerAdapter对象,覆写configureMessageConverters方法
	 * 消息转换器方法
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年12月5日 下午10:04:35
	 * @return
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		super.configureMessageConverters(converters);
		Iterator<HttpMessageConverter<?>> iterator = converters.iterator();
		while (iterator.hasNext()) {
			HttpMessageConverter<?> converter = iterator.next();
			if (converter instanceof AbstractJackson2HttpMessageConverter) {
				iterator.remove();
			}
		}
		//调用fastJson转换器(方法一)
		//converters.add(getFastJsonConverter());
		//调用fastJson转换器(方法二)
		//converters.add(getFastJsonConverter1());
	}

	//fastJson转换器
	private HttpMessageConverter<?> getFastJsonConverter() {
		FastJsonConfig config = new FastJsonConfig();
		//解决Long转json精度丢失的问题
		SerializeConfig serializeConfig = SerializeConfig.globalInstance;
		serializeConfig.put(BigInteger.class, ToStringSerializer.instance);
		serializeConfig.put(Long.class, ToStringSerializer.instance);
		serializeConfig.put(Long.TYPE, ToStringSerializer.instance);
		config.setSerializeConfig(serializeConfig);
		FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
		List<MediaType> supportedMediaTypes = new ArrayList<>();
		MediaType mediaTypeJson = MediaType.valueOf(MediaType.APPLICATION_JSON_UTF8_VALUE);
		supportedMediaTypes.add(mediaTypeJson);
		converter.setSupportedMediaTypes(supportedMediaTypes);
		config.setSerializerFeatures(SerializerFeature.WriteDateUseDateFormat,
				SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteMapNullValue);
		converter.setFastJsonConfig(config);
		return converter;
	}

	//配置FastJson消息转换器
	private HttpMessageConverter<?> getFastJsonConverter1() {
		//创建FastJson消息转换器
		FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
		//创建FastJson的配置对象
		FastJsonConfig config = new FastJsonConfig();
		//对json数据进行格式化
		config.setSerializerFeatures(SerializerFeature.PrettyFormat);
		//加入到容器中
		converter.setFastJsonConfig(config);
		return converter;
	}

	//第二种转换器(fastJson转换器)
	@Bean
	public HttpMessageConverters fastJsonConverter() {
		//创建FastJson消息转换器
		FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
		//创建FastJson的配置对象
		FastJsonConfig config = new FastJsonConfig();
		//对json数据进行格式化
		config.setSerializerFeatures(SerializerFeature.PrettyFormat);
		//加入到容器中
		converter.setFastJsonConfig(config);
		HttpMessageConverter<?> conver = converter;
		return new HttpMessageConverters(conver);
	}

	//===========================================================================

	/**
	 * 配置静态访问资源
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年6月6日 下午6:57:21
	 * @param registry
	 */
	/*@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/resources/static/")
				.addResourceLocations("classpath:/resources/templates/");
	}*/

}
