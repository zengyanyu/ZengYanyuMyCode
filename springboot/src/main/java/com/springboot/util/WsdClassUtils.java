package com.springboot.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.ClassUtils;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;

/**
 * 字节码对象工具类
 * @author ZengYanyu
 * @Description
 * @Date 2020年4月18日 下午3:50:16
 */
public class WsdClassUtils extends ClassUtils {

	private final static Log LOG = LogFactory.getLog(WsdClassUtils.class);

	/**
	 * 工具类的构造器私有化
	 */
	private WsdClassUtils() {
	}

	/**
	 * 获取所有的字段(是否要包含父类通过containSuper进行控制,如果包含父类设置为true,否则设置为false)
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年4月18日 下午4:14:47
	 * @param clazz  对应类的字节码对象
	 * @param containSuper  是否包含父类中的属性(字段)
	 * @return
	 */
	public static List<Field> getFields(Class<?> clazz, boolean containSuper) {
		List<Field> result = new ArrayList<>();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			result.add(field);
		}
		if (containSuper) {
			Class<?> superClass = clazz.getSuperclass();
			if (superClass != null && superClass != Object.class) {
				result.addAll(getFields(superClass));
			}
		}
		return result;
	}

	/**
	 * 获取类中的所有字段(默认包含父类型的所有字段)
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年4月18日 下午4:18:36
	 * @param clazz  对应类的字节码对象
	 * @return
	 */
	public static List<Field> getFields(Class<?> clazz) {
		return getFields(clazz, true);
	}

	/**
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年4月18日 下午4:36:56
	 * @param obj
	 * @param fieldName
	 * @return
	 */
	public static Field getFieldByFieldName(Object obj, String fieldName) {
		for (Class<?> superClass = obj.getClass(); superClass != Object.class; //
		superClass = superClass.getSuperclass()) {
			try {
				return superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
				LOG.error("ClassUtils getFieldByFieldName method execute error : ", e);
			}
		}
		return null;
	}

	/**
	 * 根据字段名称获取字段名称对应的值
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年4月18日 下午4:36:00
	 * @param obj  对应的对象
	 * @param fieldName  字段名称
	 * @return
	 * @throws Exception
	 */
	public static Object getValueByFieldName(Object obj, String fieldName) throws Exception {
		Field field = getFieldByFieldName(obj, fieldName);
		Object value = null;
		if (field != null) {
			if (field.isAccessible()) {
				value = field.get(obj);
			} else {
				field.setAccessible(true);
				value = field.get(obj);
				field.setAccessible(false);
			}
		}
		return value;
	}

	/**
	 * 根据当前指定类的字段名称设置字段的值
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年4月18日 下午4:57:43
	 * @param obj  对应的对象
	 * @param fieldName  字段名称
	 * @param value      字段名称对应的值
	 * @throws Exception
	 */
	public static void setValueByFielName(Object obj, String fieldName, Object value) throws Exception {
		Field field = obj.getClass().getDeclaredField(fieldName);
		if (field.isAccessible()) {
			field.set(obj, value);
		} else {
			field.setAccessible(true);
			field.set(obj, value);
			field.setAccessible(false);
		}
	}

	/**
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年6月20日 下午4:24:04
	 * @param clazz
	 * @return
	 */
	public static List<Class<?>> getSuperClasses(Class<?> clazz) {
		List<Class<?>> result = new ArrayList<Class<?>>();
		Class<?> superClass = clazz.getSuperclass();
		while (superClass != null) {
			result.add(superClass);
			superClass = superClass.getSuperclass();
		}
		return result;
	}

}
