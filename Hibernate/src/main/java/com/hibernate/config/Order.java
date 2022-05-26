package com.hibernate.config;

import java.io.Serializable;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2020年11月22日 下午6:09:07
 * @see com.hibernate.config.Order.java
 */
public class Order implements Serializable {

	protected final String entityName;
	protected final String propName;
	protected final boolean ascending;

	private Order(String entityName, String propName, boolean ascending) {
		this.entityName = entityName;
		this.propName = propName;
		this.ascending = ascending;
	}

	public String getPropName() {
		return propName;
	}

	public String getEntityName() {
		return propName;
	}

	public String getFullName() {
		if (entityName == null) {
			return propName;
		} else {
			return entityName + "." + propName;
		}
	}

	public boolean isAscending() {
		return ascending;
	}

	public static Order asc(String name) {
		return new Order(null, name, true);
	}

	public static Order asc(String entityName, String name) {
		return new Order(entityName, name, true);
	}

	public static Order desc(String name) {
		return new Order(null, name, false);
	}

	public static Order desc(String entityName, String name) {
		return new Order(entityName, name, false);
	}

}
