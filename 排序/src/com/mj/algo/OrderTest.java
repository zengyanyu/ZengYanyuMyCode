package com.mj.algo;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class OrderTest {

	private final static Comparator<Object> CHINA_COMPARE = //
	Collator.getInstance(Locale.CHINA);

	public static void main(String[] args) {
		List<String> cityList = new ArrayList<String>();
		cityList.add("啊");
		cityList.add("博");
		cityList.add("次");
		cityList.add("上海");
		cityList.add("北京");
		cityList.add("广州");
		cityList.add("深圳");
		Collections.sort(cityList, CHINA_COMPARE);
		System.out.println(cityList);
	}

}
