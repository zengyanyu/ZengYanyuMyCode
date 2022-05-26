package com.framework.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;

/**
 * javaBean工具类
 * @author ZengYanyu
 * @Description
 * @Date 2020年4月18日 下午1:44:21
 */
public class BeanUtils {

	private final static Log LOG = LogFactory.getLog(BeanUtils.class);

	/**
	 * 工具类的构造器私有化
	 */
	private BeanUtils() {
	}

	private static final String GET_PREFIX = "get";
	private static final String SET_PREFIX = "set";
	private static final String IS_PREFIX = "is";

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
		if (object == null) {
			throw new IllegalArgumentException("BenaUtils method setProperty method No bean specified");
		}
		if (propName == null) {
			throw new IllegalArgumentException(
					"BenaUtils method setProperty method No name specified for bean class '" + object.getClass() + "'");
		}
		//用来存放列名
		List<String> columnNames = new ArrayList<String>();
		//使用内省机制获取属性描述器信息,获取列名
		BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass(), Object.class);
		PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor pd : pds) {
			String columnName = pd.getName();
			columnNames.add(columnName);
		}
		//判断列名是否存在(判断不存在的情况)
		if (!columnNames.contains(propName)) {
			throw new NoSuchMethodException("Unknown property '" + propName + "' on class '" + object.getClass() + "'");
		}

		//第一种方式(自己编写的方法)
		try {
			Method method = object.getClass().getMethod(getSetName(propName), propVal.getClass());
			method.invoke(object, propVal);
		} catch (Exception e) {
			//普通boolean类型执行这段代码处理设置值
			/*
			 * 这个方法对boolean类型存在问题
			 * Method method = object.getClass().getMethod(setIsBoolName(propName), propVal.getClass());
			method.invoke(object);*/

			//对属性描述器中的方法进行操作
			for (PropertyDescriptor pd : pds) {
				//获取列名
				String columnName = pd.getName();
				if (columnName.equals(propName)) {
					//得到属性的写方法(setter方法)
					Method write = pd.getWriteMethod();
					//为属性赋值
					write.invoke(object, propVal);
					/*//获取属性的值(getter方法)
					Method readMethod = pd.getReadMethod();
					//获取属性的值
					Object invoke = readMethod.invoke(object);
					System.out.println("invoke = " + invoke);*/
				}
			}
			try {
			} catch (Exception ex) {
				//commons-beanutils.jar文件中的方法
				//PropertyUtils.setProperty(object, propName, propVal);
			}
		}
		//第二种方式(org.apache.commons.beanutils.BeanUtilsBean框架中的方法)
		//BeanUtilsBean.getInstance().setProperty(object, propName, propVal);
	}

	/**
	 * 根据属性名称获取对应的属性值
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年4月18日 下午2:43:35
	 * @param object    对象
	 * @param propName   属性名称
	 * @return  返回属性(字段)对应的值
	 * @throws Exception
	 */
	public static Object getProperty(Object object, String propName) throws Exception {
		if (object == null) {
			throw new IllegalArgumentException("BenaUtils method getProperty method No bean specified");
		}
		if (propName == null) {
			throw new IllegalArgumentException(
					"BenaUtils method getProperty method No name specified for bean class '" + object.getClass() + "'");
		}
		//使用内省机制获取属性描述器信息,获取列名
		BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass(), Object.class);
		PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
		List<String> columnNames = new ArrayList<String>();
		for (PropertyDescriptor pd : pds) {
			String columnName = pd.getName();
			columnNames.add(columnName);
		}
		//判断 列名是否存在(不存在)
		if (!columnNames.contains(propName)) {
			throw new NoSuchMethodException("Unknown property '" + propName + "' on class '" + object.getClass() + "'");
		}
		//第一种方式(自己编写的方法)
		try {
			Method method = object.getClass().getMethod(getGetName(propName));
			return method.invoke(object);
		} catch (Exception e) {
			for (PropertyDescriptor pd : pds) {
				String columnName = pd.getName();
				if (columnName.equals(propName)) {
					Method readMethod = pd.getReadMethod();
					//return readMethod.invoke(object);
				}
			}
			//这个方法也可以调用
			Method method = object.getClass().getMethod(getIsBoolName(propName));
			return method.invoke(object);
		}
		//return BeanUtilsBean.getInstance().getProperty(object, propName);
		//return PropertyUtils.getProperty(object, propName);
		//第二种方式(org.apache.commons.beanutils.BeanUtilsBean框架中的方法)
		//return BeanUtilsBean.getInstance().getProperty(obj, propName);
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
				LOG.error("BeanUtils copyProperties method execute method error : ", e);
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
	private static final String getSetName(String name) {
		return SET_PREFIX + capitalize(name);
	}

	/**
	 * 获取"getXxxx"格式的值
	 * 属性名称
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年4月18日 下午2:39:27
	 * @param name  属性名称
	 * @return
	 */
	private static final String getGetName(String name) {
		return GET_PREFIX + capitalize(name);
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
	 * 获取 "isXxxx"的bool类型的值
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年4月18日 下午2:41:22
	 * @param boolName  bool类型的名称
	 * @return
	 */
	private static final String getIsBoolName(String boolName) {
		return IS_PREFIX + capitalize(boolName);
	}

	/**
	 * 获取设置首字母大写的字符串值
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年5月16日 下午4:20:28
	 * @param str   xxxx
	 * @return      首字母大写(Xxxx)
	 */
	private static String capitalize(String str) {
		if (str == null || str.length() == 0) {
			LOG.info("BeanUtils class capitalize method param name str is null or str length = 0");
			return str;
		}
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

}
