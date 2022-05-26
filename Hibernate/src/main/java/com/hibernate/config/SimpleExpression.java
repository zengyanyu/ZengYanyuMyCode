package com.hibernate.config;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2020年11月22日 下午8:11:01
 * @see com.hibernate.config.SimpleExpression.java
 */
@SuppressWarnings("serial")
public class SimpleExpression extends AbstractPropertyExpression {
	/**
	 * 操作类型
	 */
	private final String operation;

	/**
	 * 比较值
	 */
	private Object value;

	/**
	 * 构造函数.
	 *
	 * @param entityName 实体名称
	 * @param propName 属性名称。
	 * @param value 比较值
	 * @param operation 操作类型，包括==, <>, >, <, <=, >=等。
	 */
	public SimpleExpression(String entityName, String propName, Object value, String operation) {
		super(entityName, propName);
		this.value = value;
		this.operation = operation;
	}

	/**
	 * 构造函数.
	 *
	 * @param propName 属性名称。
	 * @param value 比较值
	 * @param operation 操作类型，包括==, <>, >, <, <=, >=等。
	 */
	public SimpleExpression(String propName, Object value, String operation) {
		super(propName);
		this.value = value;
		this.operation = operation;
	}

	/**
	 * 获取比较值.
	 *
	 * @return 比较值
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * 获取操作类型.
	 *
	 * @return 操作类型
	 */
	public String getOperation() {
		return operation;
	}

}
