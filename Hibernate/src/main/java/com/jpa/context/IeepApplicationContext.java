package com.jpa.context;

import java.lang.annotation.Annotation;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

public class IeepApplicationContext {

	private static final Logger LOGGER = LoggerFactory.getLogger(IeepApplicationContext.class);
	private static ApplicationContext applicationContext;

	public static void setApplicationContext(ApplicationContext applicationContext) {
		if (applicationContext == null) {
			applicationContext = applicationContext;
			LOGGER.info("IeepApplicationContext ready to use: " + applicationContext);
		}

	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static Environment getEnvironment() {
		return (Environment) getBean(Environment.class);
	}

	public static String getApplicationName() {
		Environment env = getEnvironment();
		return env.getProperty("spring.application.name");
	}

	public static <T> T getBean(String name, Class<T> requiredType) {
		return applicationContext.getBean(name, requiredType);
	}

	public static <T> T getBean(Class<T> requiredType) {
		return applicationContext.getBean(requiredType);
	}

	public static <T> Map<String, T> getBeansOfType(Class<T> requiredType) {
		return BeanFactoryUtils.beansOfTypeIncludingAncestors(applicationContext, requiredType);
	}

	public static Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> annotationType) {
		return applicationContext.getBeansWithAnnotation(annotationType);
	}

	public static Object getBean(String name) {
		return applicationContext.getBean(name);
	}

	public static String getProperty(String key) {
		return applicationContext.getEnvironment().getProperty(key);
	}

	public static DataSource getDataSource() {
		return (DataSource) applicationContext.getBean(DataSource.class);
	}

	public static String getProjectName() {
		//String val = getPlatformConfig().getProject();
		return "";
	}

	public static String getProjectPrefix() {
		//		String val = getPlatformConfig().getProject();
		//		String sep = getPlatformConfig().getSeparator();
		//		return val == null ? "" : val.trim() + sep.trim();
		return "";
	}

	public static String getVersion() {
		//		String val = getPlatformConfig().getVersion();
		//		return val == null ? "" : val.trim();
		return "";
	}

	public static String getConsoleVersion() {
		//String val = getPlatformConfig().getConsole().getVersion();
		//return val == null ? "" : val.trim();
		return "";
	}

	public static String getSeparator() {
		//		String val = getPlatformConfig().getSeparator();
		//		return val == null ? "" : val.trim();
		return "";
	}

	public static String getConsoleServiceName() {
		//return getPlatformConfig().getConsole().getName() + getConsoleVersion();
		return "";
	}

}
