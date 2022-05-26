package com.framework.design_patten.wrapper;

/**
 * 奶油
 * @author ZengYanyu
 * @Description
 * @Date 2020年4月25日 下午5:31:19
 */
public class Cream extends Food {

	private Food basicFood;

	public Cream(Food basicFood) {
		this.basicFood = basicFood;
	}

	@Override
	public String make() {
		return basicFood.make() + "+奶油";
	}

}
