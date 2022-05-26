package com.springboot.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.springboot.domain.Department;

/**
 * 序列化工具
 * @author ZengYanyu
 * @Description
 * @Date 2020年6月19日 下午10:57:57
 * @see com.springboot.utils.WsdSerializationUtil.java
 */
public class WsdSerializationUtil {

	/**
	 * 序列化
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年6月19日 下午10:58:07
	 * @param object
	 * @return
	 */
	public static byte[] serialize(Object object) {
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			byte[] bytes = baos.toByteArray();
			return bytes;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 反序列化
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年6月19日 下午10:58:15
	 * @param bytes
	 * @return
	 */
	public static Object deserialize(byte[] bytes) {
		ByteArrayInputStream bais = null;
		try {
			bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			return ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		byte[] serialize = serialize(new Department());
		System.out.println(serialize);
		Object deserialize = deserialize(serialize);
		System.out.println(deserialize);
	}

}
