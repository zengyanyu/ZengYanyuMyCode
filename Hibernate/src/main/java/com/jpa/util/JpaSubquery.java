package com.jpa.util;

import java.io.Serializable;
import java.util.Map;

public class JpaSubquery implements Serializable {

	private static final long serialVersionUID = 6614734858037181223L;
	private Class<?> entityClass;
	private Class<?> resultClass;
	private String selectionField;
	private Map<String, Object> params;

	public Class<?> getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class<?> entityClass) {
		this.entityClass = entityClass;
	}

	public Class<?> getResultClass() {
		return resultClass;
	}

	public void setResultClass(Class<?> resultClass) {
		this.resultClass = resultClass;
	}

	public String getSelectionField() {
		return selectionField;
	}

	public void setSelectionField(String selectionField) {
		this.selectionField = selectionField;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

}
