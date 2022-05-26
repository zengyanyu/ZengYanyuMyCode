package com.framework.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.framework.annotation.PropertyDesc;
import com.framework.domain.Employee;
import com.framework.domain.PropertyDescInfo;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2020年5月10日 下午1:52:32
 */
public class PropertyInfoUtils {

	/**
	 * 获取当前类所有字段上的属性描述信息
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年5月10日 下午1:47:46
	 * @param clazz
	 * @return
	 */
	public static List<PropertyDescInfo> getPropertyDescInfo(Class<?> clazz) {
		List<PropertyDescInfo> propertyDescInfos = new ArrayList<>();
		List<Field> fields = ClassUtils.getFields(clazz);
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
		return propertyDescInfos;
	}

	public static void main(String[] args) {
		List<PropertyDescInfo> propertyDescInfos = getPropertyDescInfo(Employee.class);
		for (PropertyDescInfo propertyDescInfo : propertyDescInfos) {
			System.out.println(propertyDescInfo);
		}
	}

}
