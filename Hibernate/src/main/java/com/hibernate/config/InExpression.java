package com.hibernate.config;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2020年11月22日 下午6:22:03
 * @see com.hibernate.config.InExpression.java
 */
@SuppressWarnings("serial")
public class InExpression extends AbstractPropertyExpression {

	private final Object[] values;

	/**
	 * 构造函数.
	 *
	 * @param entityName 实体名称
	 * @param propName 属性名称
	 * @param values 取值范围
	 */
	public <T> InExpression(String entityName, String propName, T... values) {
		super(entityName, propName);
		this.values = values;
	}

	/**
	 * 构造函数.
	 *
	 * @param propName 属性名
	 * @param values 取值范围.
	 */
	public <T> InExpression(String propName, T... values) {
		super(propName);
		this.values = values;
	}

	public Object[] getValues() {
		return values;
	}
}
