package com.freemarker;

public class ClassInfo<T> {

	private Class<T> clazz;

	public ClassInfo(Class<T> clazz) {
		super();
		this.clazz = clazz;
	}

	public Class<T> getClazz() {
		return clazz;
	}

	public void setClazz(Class<T> clazz) {
		this.clazz = clazz;
	}

}
