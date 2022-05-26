package com.framework.test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.framework.annotation.PropertyDesc;
import com.framework.domain.PropertyDescInfo;
import com.framework.domain.Role;
import com.framework.util.ClassUtils;

public class ClassTest {

	public static void main(String[] args) {
		List<PropertyDescInfo> propertyDescInfos = new ArrayList<>();
		List<Field> fields = ClassUtils.getFields(Role.class);
		for (Field field : fields) {
			//是否使用@PropertyDesc注解
			if (field.isAnnotationPresent(PropertyDesc.class)) {
				//获取所有的注解
				for (Annotation anno : field.getDeclaredAnnotations()) {
					//找到自己的注解
					if (anno.annotationType().equals(PropertyDesc.class)) {
						//System.out.println(anno.annotationType());
						PropertyDescInfo propertyDescInfo = new PropertyDescInfo();
						//对应字段的值上的注解desc描述不能空字符串
						PropertyDesc propertyDesc = ((PropertyDesc) anno);
						if (!propertyDesc.desc().equals("")) {
							String desc = propertyDesc.desc();
							//System.out.println(desc);
							//获取当前字段的名称
							String name = field.getName();
							//System.out.println(name);
							Class<Role> clazz = Role.class;
							//String clazzStr = clazz + "".substring(clazz.toString().lastIndexOf(" "));
							//System.out.println(clazz);
							propertyDescInfo.setClazz(clazz);
							propertyDescInfo.setFieldName(name);
							propertyDescInfo.setDesc(desc);
							propertyDescInfos.add(propertyDescInfo);
						} else {
						}
						//获取长度
						/*if (!propertyDesc.length().equals("")) {
							String length = propertyDesc.length();
							propertyDescInfo.setLength(length);
						}*/
					}
				}
			}
		}
		for (PropertyDescInfo propertyDescInfo : propertyDescInfos) {
			System.out.println(propertyDescInfo);
		}
	}

}
