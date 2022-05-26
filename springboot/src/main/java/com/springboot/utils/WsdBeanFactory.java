package com.springboot.utils;

import java.lang.annotation.Annotation;
import java.util.Map;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.ApplicationContext;

public class WsdBeanFactory {

	private WsdBeanFactory(ListableBeanFactory beanFactory) {
	}

	private static ApplicationContext applicationContext;

	public static void setApplicationContext(ApplicationContext applicationContext) {
		WsdBeanFactory.applicationContext = applicationContext;
	}

	public static Class<?> getBeanType(String beanId) {
		return applicationContext.getType(beanId);
	}

	public static Object lookup(String beanId) {
		return applicationContext.getBean(beanId);
	}

	public static <T> T getBeanById(String beanId, Class<T> expectedType) {
		return applicationContext.getBean(beanId, expectedType);
	}

	public static Map<String, ?> getBeansByType(Class<?> expectedType) {
		return applicationContext.getBeansOfType(expectedType, false, false);
	}

	public Map<String, Object> getBeansByAnnotation(Class<? extends Annotation> annotationType) {
		return applicationContext.getBeansWithAnnotation(annotationType);
	}

}
