package com.hibernate.config;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2020年11月22日 下午6:55:29
 * @see com.hibernate.config.LogicalExpression.java
 */
public class LogicalExpression implements WsdCriterion {

	private WsdCriterion lhs;
	private WsdCriterion rhs;
	private String operation;

	public LogicalExpression(WsdCriterion lhs, WsdCriterion rhs, String operation) {
		this.lhs = lhs;
		this.rhs = rhs;
		this.operation = operation;
	}

	public WsdCriterion getLhs() {
		return lhs;
	}

	public WsdCriterion getRhs() {
		return rhs;
	}

	public String getOperation() {
		return operation;
	}

}
