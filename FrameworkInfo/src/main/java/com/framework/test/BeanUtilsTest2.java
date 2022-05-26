package com.framework.test;

import com.framework.domain.Department;
import com.framework.util.BeanUtils;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2020年5月23日 上午11:52:46
 */
public class BeanUtilsTest2 {

	public static void main(String[] args) {
		Department dept = new Department();
		try {
			BeanUtils.setProperty(dept, "bool", true);
			Object property = BeanUtils.getProperty(dept, "bool");
			System.out.println("property = " + property);
			BeanUtils.setProperty(dept, "username", "USERNAME");
			Object property1 = BeanUtils.getProperty(dept, "username");
			System.out.println("property1 = " + property1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
