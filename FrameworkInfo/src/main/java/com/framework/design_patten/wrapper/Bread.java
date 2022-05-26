package com.framework.design_patten.wrapper;

/**
 * 面包
 * @author ZengYanyu
 * @Description
 * @Date 2020年4月25日 下午5:28:31
 */
public class Bread extends Food {

	private Food basicFood;

	public Bread(Food basicFood) {
		this.basicFood = basicFood;
	}

	@Override
	public String make() {
		return basicFood.make() + "+面包";
	}

}
