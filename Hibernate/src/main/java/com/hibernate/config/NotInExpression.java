package com.hibernate.config;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2020年11月22日 下午6:27:44
 * @see com.hibernate.config.NotInExpression.java
 */
@SuppressWarnings("all")
public class NotInExpression extends AbstractPropertyExpression {

	private final Object[] values;

	/**
	* 构造函数.
	*
	* @param entityName 实体名称
	* @param propName 属性名称
	* @param values 取值范围
	*/
	public <T> NotInExpression(String entityName, String propName, T... values) {
		super(entityName, propName);
		this.values = values;
	}

	/**
	* 构造函数.
	*
	* @param propName 属性名
	* @param values 取值范围.
	*/
	public <T> NotInExpression(String propName, T... values) {
		super(propName);
		this.values = values;
	}

	public Object[] getValues() {
		return values;
	}
}
