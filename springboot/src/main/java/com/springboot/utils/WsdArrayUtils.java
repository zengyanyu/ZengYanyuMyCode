package com.springboot.utils;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

@SuppressWarnings("all")
public class WsdArrayUtils extends ArrayUtils {

	public static void addRange(List al, List list) {
		if (al == null || list == null) {
			return;
		}
		for (int i = 0; i < list.size(); i++) {
			al.add(list.get(i));
		}
	}

}
