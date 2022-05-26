package com.framework.core.jdbc.handler.impl;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.ResultSet;

import com.framework.core.jdbc.handler.IResultSetHandler;

/**
 * 对单个javaBean对象进行处理的结果集
 * @author ZengYanyu
 * @Description
 * @Date 2020年4月25日 上午11:17:03
 * @param <T>
 */
public class BeanHandler<T> implements IResultSetHandler<T> {

	//字节码对象
	private Class<T> classType;

	//使用构造器传参的方式,传入指定的字节码类型
	public BeanHandler(Class<T> classType) {
		this.classType = classType;
	}

	/**
	 * 处理单个结果集对象信息
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年4月25日 上午11:23:01
	 * @param rs   结果集对象
	 * @return     返回处理之后的对象
	 * @throws Exception
	 */
	@Override
	public T handle(ResultSet rs) throws Exception {
		T obj = classType.newInstance();
		//通过内省机制创建对象
		BeanInfo beanInfo = Introspector.getBeanInfo(classType, Object.class);
		PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
		if (rs.next()) {
			for (PropertyDescriptor pd : pds) {
				String columnName = pd.getName();
				Object val = rs.getObject(columnName);
				pd.getWriteMethod().invoke(obj, val);
			}
		}
		return obj;
	}

}
