package com.hibernate.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.hibernate.domain.User;

/**
 * 内省机制工具类
 * @author ZengYanyu
 * @Description
 * @Date 2020年11月24日 下午9:55:12
 * @see com.hibernate.util.IntrospectorUtil.java
 */
public class IntrospectorUtil {

	//constructor private
	private IntrospectorUtil() {
	}

	/**
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年11月24日 下午10:22:51
	 * @param clazz
	 * @return
	 */
	public static Set<String> getFieldNames(Class<?> clazz) {
		if (clazz == null) {
			return null;
		}
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(clazz, Object.class);
			PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
			Set<String> columnNames = new HashSet<>();
			for (PropertyDescriptor pd : pds) {
				String columnName = pd.getName();
				columnNames.add(columnName);
			}
			return columnNames;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年11月24日 下午10:22:47
	 * @param clazz
	 * @return
	 */
	public static PropertyDescriptor[] getPropertyDescriptors(Class<?> clazz) {
		if (clazz == null) {
			return null;
		}
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(clazz, Object.class);
			PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
			return pds;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		Set<String> fieldNames = getFieldNames(User.class);
		//==================================
		for (Iterator<String> iter = fieldNames.iterator(); iter.hasNext();) {
			String columnName = iter.next();
			System.out.println("column = " + columnName);
		}
		//==================================
		for (int i = 0; i < fieldNames.size(); i++) {
			String columnName = (String) fieldNames.toArray()[i];
			System.out.println("columnName = " + columnName);
		}
		//==================================
		for (String fieldName : fieldNames) {
			System.out.println(fieldName);
		}
		//==================================
		System.out.println("============================");
		PropertyDescriptor[] pds = getPropertyDescriptors(User.class);
		for (PropertyDescriptor pd : pds) {
			String columnName = pd.getName();
			System.out.println(columnName);
		}
		System.out.println("==================================");
		Iterator<String> iter = fieldNames.iterator();
		while (iter.hasNext()) {
			String columnName = iter.next();
			System.out.println("column_while = " + columnName);
		}
	}

}
