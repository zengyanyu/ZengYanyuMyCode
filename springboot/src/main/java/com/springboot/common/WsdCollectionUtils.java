package com.springboot.common;

import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2020年6月11日 下午9:49:21
 * @see com.springboot.common.WsdCollectionUtils.java
 */
public class WsdCollectionUtils extends CollectionUtils {

	public static Object getFromUniqueCollection(Collection<?> c) {
		if (c == null || c.size() == 0) {
			return null;
		}
		if (isUniqueCollection(c)) {
			return get(c, 0);
		}
		throw new RuntimeException(
				"found more than one object in this collection, this collection size is: " + size(c));
	}

	/**
	 * 判断是否是单集合(一条对象的集合)
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年6月19日 下午10:46:15
	 * @param c
	 * @return
	 */
	public static boolean isUniqueCollection(Collection<?> c) {
		return (size(c) > 1) ? false : true;
	}

}
