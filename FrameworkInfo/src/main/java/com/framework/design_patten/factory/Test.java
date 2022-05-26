package com.framework.design_patten.factory;

public class Test {

	public static void main(String[] args) {
		Car car = Factory.getCarInstance("Benz");
		if (car != null) {
			car.run();
			car.stop();
		} else {
			System.out.println("造不了这种汽车");
		}
		System.out.println("=================");
		BroomFactory broomFactory = new BroomFactory();
		Moveable create = broomFactory.create();
		create.run();
	}

}
