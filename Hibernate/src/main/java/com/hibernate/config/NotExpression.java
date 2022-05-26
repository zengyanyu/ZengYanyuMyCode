package com.hibernate.config;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2020年11月22日 下午6:58:50
 * @see com.hibernate.config.NotExpression.java
 */
@SuppressWarnings("serial")
public class NotExpression implements WsdCriterion {

	/**
	* Criterion条件
	*/
	protected WsdCriterion criterion;

	/**
	 * Constructor
	 *
	 * @param criterion Criterion条件
	 */
	public NotExpression(WsdCriterion criterion) {
		this.criterion = criterion;
	}

	/**
	 * 获取Criterion条件.
	 *
	 * @return Criterion条件
	 */
	public WsdCriterion getCriterion() {
		return criterion;
	}

}
