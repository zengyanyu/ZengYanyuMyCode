package com.framework.design_patten.proxy;

public class Test {

	public static void main(String[] args) {
		ProxyInterface proxyInterface = new WeddingCompany(new NormalHome());
		proxyInterface.marry();
	}

}
