package com.springboot.utils;

import java.util.Collection;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.util.ObjectUtils;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2020年6月19日 下午10:55:36
 * @see com.springboot.utils.WsdObjectUtils.java
 */
public class WsdObjectUtils extends ObjectUtils {

	public static String quote(Object obj) {
		return (obj != null ? "'" + obj.toString() + "'" : null);
	}

	public static boolean hasEmptyElement(Object[] array) {
		if (org.apache.commons.lang.ArrayUtils.isEmpty(array))
			return true;
		for (Object obj : array) {
			if (obj == null)
				return true;
		}
		return false;
	}

	public static boolean hasEmptyElement(Collection<?> coll) {
		if (CollectionUtils.isEmpty(coll))
			return true;
		for (Object obj : coll) {
			if (obj == null)
				return true;
		}
		return false;
	}

	public static boolean hasEmptyValue(Map<?, ?> map) {
		if (MapUtils.isEmpty(map))
			return true;
		for (Object value : map.values()) {
			if (value == null)
				return true;
		}
		return false;
	}

}
