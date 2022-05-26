package com.springboot.util;

import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * JSON工具类
 * @author ZengYanyu
 * @Description
 * @Date 2020年7月9日 下午8:50:56
 * @see com.springboot.util.WsdJsonUtils.java
 */
public class WsdJsonUtils /*extends JSON*/ {

	//构造器私有化
	private WsdJsonUtils() {
	}

	/**
	 <!-- fastjson -->
		<dependency>
			<groupId>com.alibaba</groupId>
			<artifactId>fastjson</artifactId>
			<version>1.2.31</version>
		</dependency>
	 */

	/**
	 * 将JSON字符串转换成对象
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年7月9日 下午9:07:18
	 * @param jsonStr  json字符串
	 * @param clazz    反射的类
	 * @return     返回反射类的对象
	 */
	public static <T> T decode(String jsonStr, Class<T> clazz) {
		return JSON.parseObject(jsonStr, clazz);
	}

	/**
	 * 将JSON字符串转换成对象集合数组
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年7月9日 下午9:08:50
	 * @param jsonStr   json字符串
	 * @param clazz     反射的类
	 * @return       返回反射类的对象集合
	 */
	public static <T> List<T> decodeToArr(String jsonStr, Class<T> clazz) {
		return JSON.parseArray(jsonStr, clazz);
	}

}
