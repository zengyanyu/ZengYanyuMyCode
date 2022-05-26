package com.hibernate.config;

/**
 * Interpreter对表达式进行解析，例如解析成SQL语句的片断，解析成Hibernate中的Expression。
 * 解析的结果存放到Context中。
 * @author ZengYanyu
 * @Description
 * @Date 2020年11月22日 下午5:24:45
 * @param <Context>
 * @param <Object>
 * @see com.hibernate.config.Interpreter.java
 */
public interface Interpreter<Context, Object> {

	/**
	 * 对表达式进行解析，形成可以用于实际查询的上下文。
	 * @param context 解析上下文
	 * @param obj     需要解析的对象，包括表达式、Order等等。
	 */
	void interprete(Context context, Object obj);
}
