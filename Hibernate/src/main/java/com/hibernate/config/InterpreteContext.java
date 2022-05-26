package com.hibernate.config;

import java.util.List;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2020年11月22日 下午8:00:03
 * @see com.hibernate.config.InterpreteContext.java
 */
public interface InterpreteContext {

	void append(Object obj);

	String getContextString();

	void addPrepraredValue(Object obj);

	List getPreparedValues();

}
