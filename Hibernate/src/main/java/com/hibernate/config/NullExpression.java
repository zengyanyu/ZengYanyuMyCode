package com.hibernate.config;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2020年11月22日 下午8:05:39
 * @see com.hibernate.config.NullExpression.java
 */
@SuppressWarnings("serial")
public class NullExpression extends AbstractPropertyExpression {
	/**
	* 构造函数.
	*
	* @param entityName 实体名称
	* @param propName 属性名称。
	*/
	public NullExpression(String entityName, String propName) {
		super(entityName, propName);
	}

	/**
	 * 构造函数.
	 *
	 * @param propName 属性名称。
	 */
	public NullExpression(String propName) {
		super(propName);
	}
}
