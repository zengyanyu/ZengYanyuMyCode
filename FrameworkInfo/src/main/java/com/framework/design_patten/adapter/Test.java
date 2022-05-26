package com.framework.design_patten.adapter;

public class Test {

	public static void main(String[] args) {
		Phone phone = new Phone();
		VoltageAdapter adapter = new VoltageAdapter();
		phone.setAdapter(adapter);
		phone.change();
	}

}
