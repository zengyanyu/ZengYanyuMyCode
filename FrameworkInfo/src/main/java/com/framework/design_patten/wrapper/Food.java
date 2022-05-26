package com.framework.design_patten.wrapper;

/**
 * 食物
 * @author ZengYanyu
 * @Description
 * @Date 2020年4月25日 下午5:25:36
 */
public class Food {

	private String foodName;

	public Food() {
	}

	//食物名称
	public Food(String foodName) {
		this.foodName = foodName;
	}

	//make:制作
	public String make() {
		return foodName;
	}

}
