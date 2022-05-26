package com.hibernate.config;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2020年11月22日 下午6:19:06
 * @see com.hibernate.config.BetweenExpression.java
 */
@SuppressWarnings("serial")
public class BetweenExpression extends AbstractPropertyExpression {

	private Object low;
	private Object high;

	/**
	 * 构造函数.
	 * @param entityName 实体名称
	 * @param propName 属性名称
	 * @param low 区间中的较小值
	 * @param high 区间中的较大值
	 */
	public BetweenExpression(String entityName, String propName, Object low, Object high) {
		super(entityName, propName);
		this.low = low;
		this.high = high;
	}

	/**
	 * 构造函数.
	 * @param propName 属性名称
	 * @param low 区间中的较小值
	 * @param high 区间中的较大值
	 */
	public BetweenExpression(String propName, Object low, Object high) {
		super(propName);
		this.low = low;
		this.high = high;
	}

	public Object getLow() {
		return low;
	}

	public Object getHigh() {
		return high;
	}

}
