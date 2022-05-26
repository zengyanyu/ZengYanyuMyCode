package com.hibernate.config;

import java.util.ArrayList;
import java.util.List;

/**
 * 一般的上下文解释器.
 * @author ZengYanyu
 * @Description
 * @Date 2020年11月22日 下午8:22:31
 * @see com.hibernate.config.CommonInterpreteContext.java
 */
public class CommonInterpreteContext implements InterpreteContext {
	/**
	* 上下文语句
	*/
	protected StringBuilder sb = new StringBuilder();
	/**
	 * 上下文参数
	 */
	protected List<Object> preparedValues = new ArrayList<Object>();

	/**
	 * 添加上下文语句.
	 *
	 * @param obj   上下文语句
	 */
	@Override
	public void append(Object obj) {
		sb.append(obj);
	}

	/**
	 * 获取上下文语句.
	 *
	 * @return 上下文语句
	 */
	@Override
	public String getContextString() {
		return sb.toString();
	}

	/**
	 * 添加上下文参数.
	 *
	 * @param obj   上下文参数
	 */
	@Override
	public void addPrepraredValue(Object obj) {
		preparedValues.add(obj);
	}

	/**
	 * 获取上下文参数.
	 *
	 * @return 上下文参数
	 */
	@Override
	public List getPreparedValues() {
		return preparedValues;
	}

}
