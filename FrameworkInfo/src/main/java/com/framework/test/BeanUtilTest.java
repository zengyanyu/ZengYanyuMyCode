package com.framework.test;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import com.framework.domain.Employee;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2020年5月16日 下午4:10:57
 */
public class BeanUtilTest {

	public static void main(String[] args) {
		//test1();
		//test2();
		//test3();
		test4();
	}

	private static void test4() {
		Employee employee = new Employee();
		try {
			com.framework.util.BeanUtils.setProperty(employee, "username", "USERNAME");
			Object property = com.framework.util.BeanUtils.getProperty(employee, "username");
			com.framework.util.BeanUtils.setProperty(employee, "password", "PASSWORD");
			com.framework.util.BeanUtils.setProperty(employee, "id", 1000L);
			System.out.println("property = " + property);
			System.out.println("=======================");
			com.framework.util.BeanUtils.setProperty(employee, "bool", true);
			Object property1 = com.framework.util.BeanUtils.getProperty(employee, "bool");
			System.out.println("property1 = " + property1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void test3() {
		Employee employee = new Employee();
		try {
			PropertyUtils.setProperty(employee, "username", "USERNAME");
			Object property = PropertyUtils.getProperty(employee, "username");
			System.out.println(property);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void test2() {
		try {
			Employee employee = new Employee();
			//employee.setUsername("USERNAME");
			//自己的工具设置普通boolean类型为true不生效(BUG已经处理)
			com.framework.util.BeanUtils.setProperty(employee, "username", "USERNAME");
			Object property4 = com.framework.util.BeanUtils.getProperty(employee, "username");
			System.out.println(property4);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void test1() {
		try {
			Employee employee = new Employee();
			employee.setUsername("USERNAME");
			//employee.setBool(false);
			String property = BeanUtils.getProperty(employee, "username");
			System.out.println("property = " + property);
			BeanUtils.setProperty(employee, "username", "NAME1");
			System.out.println("================");
			Object property2 = com.framework.util.BeanUtils.getProperty(employee, "username");
			System.out.println("property2 = " + property2);
			com.framework.util.BeanUtils.setProperty(employee, "bool", true);
			Object property3 = com.framework.util.BeanUtils.getProperty(employee, "bool");
			System.out.println("property3 = " + property3);
			//自己的工具设置普通boolean类型为true不生效
			com.framework.util.BeanUtils.setProperty(employee, "bool", false);
			Object property4 = com.framework.util.BeanUtils.getProperty(employee, "bool");
			System.out.println("property4 = " + property4);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
