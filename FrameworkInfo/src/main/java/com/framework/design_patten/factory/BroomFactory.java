package com.framework.design_patten.factory;

//具体工厂
public class BroomFactory implements VehicleFactory {

	@Override
	public Moveable create() {
		return new Broom();
	}

}
