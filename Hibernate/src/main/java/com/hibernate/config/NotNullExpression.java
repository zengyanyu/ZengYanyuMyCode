package com.hibernate.config;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2020年11月22日 下午6:49:04
 * @see com.hibernate.config.NotNullExpression.java
 */
@SuppressWarnings("serial")
public class NotNullExpression extends AbstractPropertyExpression {

	/**
	 * 构造函数.
	 * @param entityName 实体名称
	 * @param propName 属性名称。
	 */
	public NotNullExpression(String entityName, String propName) {
		super(entityName, propName);
	}

	/**
	 * 构造函数.
	 * @param propName 属性名称。 
	 */
	public NotNullExpression(String propName) {
		super(propName);
	}

}
