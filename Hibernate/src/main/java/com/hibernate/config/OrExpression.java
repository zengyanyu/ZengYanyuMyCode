package com.hibernate.config;

/**
 * Constrains the property to a specified list of values.
 * 参数长度至少为2.
 * @author ZengYanyu
 * @Description
 * @Date 2020年11月22日 下午8:44:35
 * @param <T>
 * @see com.hibernate.config.OrExpression.java
 */
public class OrExpression<T extends WsdCriterion> implements WsdCriterion {
	private final T[] values;

	/**
	* 构造函数.
	*
	* @param values 取值范围
	*/
	public OrExpression(T... values) {
		if (values.length < 2) {
			throw new IllegalArgumentException("err.invalid_argument_length:" + values.length);
		}
		this.values = values;
	}

	public T[] getValues() {
		return values;
	}
}
