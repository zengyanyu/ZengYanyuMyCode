package com.springboot.util;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * 谷歌JSON工具类
 * com.google.gson.Gson
 * @author ZengYanyu
 * @Description
 * @Date 2020年7月18日 下午6:31:45
 * @see com.springboot.util.GsonUtils.java
 */
public class GsonUtils {

	/**
	 <dependency>
			<groupId>com.google.code.gson</groupId>
			<artifactId>gson</artifactId>
			<version>2.8.0</version>
		</dependency>
	 */

	//构造器私有化
	private GsonUtils() {
	}

	/**
	 * String result = "[{PrescriptionTime:\"2020-07-18 18:23:25\"}]";
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年7月19日 下午6:57:40
	 * @param jsonStr
	 * @param clazz
	 * @return
	 */
	public static <T> T decode(String jsonStr, Class<T> clazz) {
		Gson gson = new Gson();
		JsonParser jsonParser = new JsonParser();
		JsonArray jsonArray = jsonParser.parse(jsonStr).getAsJsonArray();
		for (JsonElement jsonElement : jsonArray) {
			T t = gson.fromJson(jsonElement, clazz);
			return t;
		}
		return null;
	}

	/**
	 * 用法:</p>
	 * 创建JsonObject jsonObject对象;</p>
	 * 		com.google.gson.JsonObject jsonObject = new JsonObject();</p>
	 * 添加属性:jsonObject.addProperty(String property, String value);</p>
	 * 		jsonObject.addProperty("PrescriptionTime", ConcurrentDateUtil.format(new Date()));</p>
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年7月18日 下午11:01:51
	 * @param jsonObject
	 * @param clazz
	 * @return
	 */
	public static <T> T jsonObject(JsonObject jsonObject, Class<T> clazz) {
		//JsonObject jsonObject = new JsonObject();
		//jsonObject.addProperty("PrescriptionTime", ConcurrentDateUtil.format(new Date()));
		Gson gson = new Gson();
		JsonParser jsonParser = new JsonParser();
		JsonArray jsonArray = jsonParser.parse("[" + jsonObject.toString() + "]").getAsJsonArray();
		for (JsonElement jsonElement : jsonArray) {
			T t = gson.fromJson(jsonElement, clazz);
			return t;
		}
		return null;
	}

	/**
	String result = "[{PrescriptionTime:\"2020-07-18 18:23:25\"},
	                  {PrescriptionTime:\"2020-07-18 18:23:25\"},
	                  {PrescriptionTime:\"2020-07-18 18:23:25\"}]";
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年7月19日 下午6:57:57
	 * @param jsonStr
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> decodeToArr(String jsonStr, Class<T> clazz) {
		List<T> ts = new ArrayList<>();
		Gson gson = new Gson();
		JsonParser jsonParser = new JsonParser();
		JsonArray jsonArray = jsonParser.parse(jsonStr).getAsJsonArray();
		for (JsonElement jsonElement : jsonArray) {
			T t = gson.fromJson(jsonElement, clazz);
			//
			ts.add(t);
		}
		return ts;
	}

}
