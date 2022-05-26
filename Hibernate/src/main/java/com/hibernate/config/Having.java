package com.hibernate.config;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Having implements Serializable {
	/**
	* Having的条件语句，通常为聚合函数
	*/
	private final WsdCriterion criterion;

	public Having(WsdCriterion criterion) {
		this.criterion = criterion;
	}

	public WsdCriterion getCriterion() {
		return criterion;
	}
}
