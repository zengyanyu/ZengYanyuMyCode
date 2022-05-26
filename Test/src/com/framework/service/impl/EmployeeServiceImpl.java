package com.framework.service.impl;

import java.lang.reflect.Field;

import com.framework.domain.Employee;
import com.framework.service.IEmployeeService;
import com.framework.util.BeanUtils;
import com.framework.util.ClassUtils;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2020年4月18日 下午1:10:48
 */
public class EmployeeServiceImpl implements IEmployeeService {

	/**
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年4月18日 下午1:10:57
	 * @param id
	 * @return
	 */
	@Override
	public Employee get(Long id) {
		return null;
	}

	/**
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年4月18日 下午5:06:20
	 * @param args
	 */
	public static void main(String[] args) {
		//test1();
		//testClassUtils();
		testCopyProperties();
	}

	public static void testClassUtils() {
		//创建对象,设置属性
		Employee employee = new Employee();
		try {
			ClassUtils.setValueByFielName(employee, "username", 1);
			System.out.println(employee.getUsername());
			Object property = BeanUtils.getProperty(employee, "username");
			System.out.println(property);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testCopyProperties() {
		Employee srcEmp = new Employee();
		Employee destEmp = new Employee();
		srcEmp.setId(1L);
		srcEmp.setName("name");
		srcEmp.setRue(true);
		srcEmp.setUsername(2);
		System.out.println(srcEmp);
		System.out.println("------------------");
		BeanUtils.copyProperties(srcEmp, destEmp, true);
		System.out.println(destEmp.getRue());
		System.out.println(destEmp);
	}

	public static void test1() {
		//创建对象,设置属性
		Employee employee = new Employee();
		try {
			BeanUtils.setProperty(employee, "name", "Neld");
			BeanUtils.setProperty(employee, "id", 1L);
			BeanUtils.setProperty(employee, "rue", true);
			BeanUtils.setProperty(employee, "username", 1);
			System.out.println(employee);
			System.out.println("-----------------");
			Object propValue = BeanUtils.getProperty(employee, "name");
			Object propValue1 = BeanUtils.getProperty(employee, "rue");
			Object username = BeanUtils.getProperty(employee, "username");
			Object id = BeanUtils.getProperty(employee, "id");
			System.out.println(propValue);
			System.out.println(propValue1);
			System.out.println(username);
			System.out.println(id);
			System.out.println("-----------------");
			//创建对象,设置属性
			Employee employee2 = new Employee();
			BeanUtils.copyProperty(employee, "username", employee2, "username");
			Object user = BeanUtils.getProperty(employee2, "username");
			System.out.println(user + "------------");
			System.out.println("-----------------");
			BeanUtils.copyProperty(employee, employee2, "rue");
			Object rue = BeanUtils.getProperty(employee2, "rue");
			System.out.println("==========拷贝属性集合开始=========");
			//穿件对象,设置属性
			Employee employee3 = new Employee();
			BeanUtils.copyProperties(employee, employee3, true);
			Object name = BeanUtils.getProperty(employee3, "name");
			Object rue2 = BeanUtils.getProperty(employee3, "rue");
			Object username2 = BeanUtils.getProperty(employee3, "username");
			Object id2 = BeanUtils.getProperty(employee3, "id");
			System.out.println(name + " " + rue2 + " " + username2 + " " + id2);
			System.out.println("==========拷贝属性集合结束=========");
			System.out.println(rue);
			System.out.println("========================================");
			Object value = ClassUtils.getValueByFieldName(employee, "username");
			System.out.println(value);
			Field fieldName = ClassUtils.getFieldByFieldName(employee, "username");
			System.out.println(fieldName.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
