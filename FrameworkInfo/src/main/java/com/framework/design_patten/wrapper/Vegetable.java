package com.framework.design_patten.wrapper;

/**
 * 蔬菜
 * @author ZengYanyu
 * @Description
 * @Date 2020年4月25日 下午5:32:42
 */
public class Vegetable extends Food {

	private Food basicFood;

	public Vegetable(Food basicFood) {
		this.basicFood = basicFood;
	}

	@Override
	public String make() {
		return basicFood.make() + "+蔬菜";
	}

}
