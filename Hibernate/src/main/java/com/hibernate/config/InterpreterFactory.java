package com.hibernate.config;

public interface InterpreterFactory {

	static final String TYPE_SQL = "sql";

	/**
	 * 根据表达式获取表达式的解析器。
	 * @param obj 需要解析的对象。
	 * @return 表达式解析器。
	 */
	Interpreter getInterpreter(Object obj);
}
