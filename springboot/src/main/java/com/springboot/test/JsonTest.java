package com.springboot.test;

import java.util.List;

import com.springboot.util.WsdJsonUtils;

public class JsonTest {

	public static void main(String[] args) {
		String json = "[{id:111,name:222},{id:222,name:333}]";
		List<Student> decodeToArr = WsdJsonUtils.decodeToArr(json, Student.class);
		System.out.println(decodeToArr);
		System.out.println("--------------------------");
		String json1 = "{id:111,name:222}";
		Student decode = WsdJsonUtils.decode(json1, Student.class);
		System.out.println(decode);
	}

}
