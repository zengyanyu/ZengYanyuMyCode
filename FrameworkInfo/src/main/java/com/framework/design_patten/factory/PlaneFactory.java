package com.framework.design_patten.factory;

//具体工厂
public class PlaneFactory implements VehicleFactory {

	@Override
	public Moveable create() {
		return new Plane();
	}

}
