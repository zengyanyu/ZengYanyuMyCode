package com.framework.design_patten.factory;

public class Factory {

	public static Car getCarInstance(String type) {
		Car car = null;
		if ("Ford".equals(type)) {
			car = new Ford();
		}
		if ("Benz".equals(type)) {
			car = new Benz();
		}
		return car;
	}

}
