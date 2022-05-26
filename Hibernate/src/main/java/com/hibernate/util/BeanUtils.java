package com.hibernate.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * javaBean工具类
 * @author ZengYanyu
 * @Description
 * @Date 2020年4月18日 下午1:44:21
 */
public class BeanUtils {

	/**
	 * 工具类的构造器私有化
	 */
	private BeanUtils() {
	}

	/**
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年4月18日 下午1:45:31
	 * @param object    设置的对象
	 * @param propName  属性名称
	 * @param propVal   属性值
	 * @throws Exception 
	 */
	public static void setProperty(Object object, String propName, Object propVal) throws Exception {
		try {
			Method method = object.getClass().getMethod(getSetName(propName), propVal.getClass());
			method.invoke(object, propVal);
		} catch (Exception e) {
			try {
				Method method = object.getClass().getMethod(setIsBoolName(propName), propVal.getClass());
				method.invoke(object);
			} catch (Exception e1) {
				//commons-beanutils.jar文件中的方法
				PropertyUtils.setProperty(object, propName, propVal);
			}
		}
	}

	/**
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年4月18日 下午3:21:21
	 * @param propName
	 * @return
	 */
	private static String setIsBoolName(String propName) {
		return getIsBoolName(propName);
	}

	/**
	 * 根据属性名称获取对应的属性值
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年4月18日 下午2:43:35
	 * @param obj    对象
	 * @param propName   属性名称
	 * @return  返回属性(字段)对应的值
	 * @throws Exception
	 */
	public static Object getProperty(Object obj, String propName) throws Exception {
		try {
			Method method = obj.getClass().getMethod(getGetName(propName));
			return method.invoke(obj);
		} catch (Exception e) {
			Method method = obj.getClass().getMethod(getIsBoolName(propName));
			return method.invoke(obj);
		}
	}

	/**
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年4月18日 下午3:33:02
	 * @param src   源对象
	 * @param srcName  源对象名称
	 * @param dest  目标对象
	 * @param destName 目标对象名称
	 * @throws Exception
	 */
	public static void copyProperty(Object src, String srcName, Object dest, String destName) throws Exception {
		Object value = getProperty(src, srcName);
		setProperty(dest, destName, value);
	}

	/**
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年4月18日 下午3:38:39
	 * @param src   源对象
	 * @param dest  目标对象
	 * @param name  拷贝属性的名称
	 * @throws Exception
	 */
	public static void copyProperty(Object src, Object dest, String name) throws Exception {
		Object value = getProperty(src, name);
		setProperty(dest, name, value);
	}

	/**
	 * 拷贝属性集合(是否要包含父类通过containSuper进行控制,如果包含父类设置为true,否则设置为false)
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年4月18日 下午4:05:16
	 * @param src  源对象
	 * @param dest 目标对象
	 * @param containSuper 是否包含父类的状态
	 */
	public static void copyProperties(Object src, Object dest, boolean containSuper) {
		List<Field> fields = ClassUtils.getFields(src.getClass(), containSuper);
		for (Field field : fields) {
			try {
				copyProperty(src, field.getName(), dest, field.getName());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 拷贝属性集合(默认包含父类中的所有属性)
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年4月18日 下午4:04:11
	 * @param src  源对象
	 * @param dest 目标对象
	 */
	public static void copyProperties(Object src, Object dest) {
		copyProperties(src, dest, true);
	}

	/**
	 * 属性名称
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年4月18日 下午2:26:25
	 * @param name  属性名称
	 * @return
	 */
	public static final String getSetName(String name) {
		return "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
	}

	/**
	 * 属性名称
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年4月18日 下午2:39:27
	 * @param name  属性名称
	 * @return
	 */
	public static final String getGetName(String name) {
		return "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
	}

	/**
	 * 获取 "isXxxx"的bool类型的值
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年4月18日 下午2:41:22
	 * @param boolName  bool类型的名称
	 * @return
	 */
	public static final String getIsBoolName(String boolName) {
		return "is" + boolName.substring(0, 1).toUpperCase() + boolName.substring(1);
	}

}
