package com.springboot;

import javax.servlet.MultipartConfigElement;
import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.springboot.web.filter.TestFilter;
import com.springboot.web.listener.TestListener;
import com.springboot.web.servlet.TestServlet;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2020年5月30日 下午9:24:02
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
//@MapperScan(basePackages = { "com.wisdom.springboot.mapper" })
//默认加载的是application.properties的文件,如果需要修改文件名称,使用@PropertySource注解重新引入
//@PropertySource("classpath:application-core.properties")
@ServletComponentScan
//@EnableTransactionManagement //相当于<tx:annotation-driver>
@ImportResource("classpath:application-tx.xml")
public class SpringBootApp extends WebMvcConfigurerAdapter {

	/**
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年5月30日 下午9:24:05
	 * @param args
	 */
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
	/*@Override
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
	}*/

	//第二种转换器(fastJson转换器)
	/*@Bean
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
	}*/

	//使用springboot方式
	/**
	 * @Bean注解表示往容器中注册这个Servlet
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年6月14日 下午1:51:21
	 * @return
	 */
	@Bean
	public ServletRegistrationBean testServlet() {
		ServletRegistrationBean bean = new ServletRegistrationBean();
		bean.setServlet(new TestServlet());
		bean.addUrlMappings("/testServlet");
		return bean;
	}

	@Bean
	public FilterRegistrationBean testFilter() {
		FilterRegistrationBean bean = new FilterRegistrationBean();
		bean.setFilter(new TestFilter());
		bean.addUrlPatterns("/*");
		return bean;
	}

	@Bean
	public ServletListenerRegistrationBean<TestListener> testListener() {
		ServletListenerRegistrationBean<TestListener> bean = //
		new ServletListenerRegistrationBean<>();
		bean.setListener(new TestListener());
		return bean;
	}

	/**
	 * 优先级:1:写了java配置的多文件配置元素的话,按照java配置的执行(优先于.properties的文件配置)
	 * 		2:如果没有配置java元素的配置,那么再找.properties的配置
	 * 可以使用配置,也可是使用Java设置
	 * 提供的类型是: MultipartConfigElement
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年6月14日 下午2:08:58
	 * @return
	 */
	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory configFactory = new MultipartConfigFactory();
		//最大文件大小
		configFactory.setMaxFileSize("100MB");
		//最大请求大小
		configFactory.setMaxRequestSize("100MB");
		return configFactory.createMultipartConfig();
	}

	//结合application-tx.xml的配置文件进行管理事务的方法,txManager的方法名称是application-tx.xml
	//的transaction-manager="txManager"对应
	@Bean
	public DataSourceTransactionManager txManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

}
