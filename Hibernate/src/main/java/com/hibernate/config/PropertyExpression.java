package com.hibernate.config;

public class PropertyExpression extends AbstractPropertyExpression {
	/**
	 */
	private static final long serialVersionUID = -3973687498827059082L;

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
	 * @param entityName    实体名称
	 * @param propName      属性名称。
	 * @param value         比较值
	 * @param operation     操作类型，包括==, <>, >, <, <=, >=等。
	 */
	public PropertyExpression(String entityName, String propName, Object value, String operation) {
		super(entityName, propName);
		this.value = value;
		this.operation = operation;
	}

	/**
	 * 构造函数.
	 *
	 * @param propName      属性名称。
	 * @param value         比较值
	 * @param operation     操作类型，包括==, <>, >, <, <=, >=等。
	 */
	public PropertyExpression(String propName, Object value, String operation) {
		super(propName);
		this.value = value;
		this.operation = operation;
	}

	/**
	 * 获取操作类型.
	 *
	 * @return 操作类型
	 */
	public String getOperation() {
		return operation;
	}

	/**
	 * 获取比较值.
	 *
	 * @return 比较值
	 */
	public Object getValue() {
		return value;
	}
}