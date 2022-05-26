package com.zengyanyu.jdbc.handler.impl;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.zengyanyu.jdbc.handler.IResultSetHandler;

/**
 * 对多个javaBean对象进行处理的结果集
 * @author ZengYanyu
 * @Description
 * @Date 2020年4月25日 上午11:17:48
 * @param <T>
 */
public class BeanListHandler<T> implements IResultSetHandler<List<T>> {

	//字节码对象
	private Class<T> classType;

	//使用构造器传参的方式,传入指定的字节码类型
	public BeanListHandler(Class<T> classType) {
		this.classType = classType;
	}

	/**
	 * 处理多个结果集对象信息
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年4月25日 上午11:23:33
	 * @param rs   结果集对象
	 * @return     返回处理之后的对象
	 * @throws Exception
	 */
	@Override
	public List<T> handle(ResultSet rs) throws Exception {
		List<T> list = new ArrayList<>();
		//通过内省机制创建对象
		BeanInfo beanInfo = Introspector.getBeanInfo(classType, Object.class);
		PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
		while (rs.next()) {
			T obj = classType.newInstance();
			list.add(obj);
			for (PropertyDescriptor pd : pds) {
				String columnName = pd.getName();
				Object val = rs.getObject(columnName);
				pd.getWriteMethod().invoke(obj, val);
			}
		}
		return list;
	}

}