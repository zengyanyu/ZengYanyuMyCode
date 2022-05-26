package com.springboot.util;

/**
 * 字符串工具类
 * @author ZengYanyu
 * @Description
 * @Date 2020年4月19日 下午1:46:33
 */
public class StringUtils extends org.springframework.util.StringUtils {

	/**
	 * 工具类的构造器私有化
	 */
	private StringUtils() {
	}

	/**
	 * 判断字符串是否为空,不为空,返回false;否则,返回true
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年4月19日 下午1:48:27
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || "".equals(str.trim());
	}

	/**
	 * 判断字符串是否不为空,不为空返回true;否则,返回false
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年4月19日 下午1:56:26
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		return hasLength(str);
	}

	/**
	 * 判断字符串是否不为空,不为空返回true;否则,返回false
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年4月20日 下午9:20:44
	 * @param str
	 * @return
	 */
	public static boolean isNotBlank(String str) {
		return isNotEmpty(str);
	}

	/**
	 * 判断字符串是否为空,不为空,返回false;否则,返回true
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年4月19日 下午1:51:03
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		return isEmpty(str);
	}

	/**
	 * 判断字符串是否有长度;有长度返回true;否则,返回false
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年4月19日 下午1:50:13
	 * @param str
	 * @return
	 */
	public static boolean hasLength(String str) {
		return str != null && !"".equals(str.trim());
	}

	/**
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年4月19日 下午1:53:52
	 * @param args
	 */
	public static void main(String[] args) {
		boolean hasLength = hasLength(" 1");
		System.out.println(hasLength);
		boolean empty = isEmpty(" ");
		System.out.println(empty);
		boolean blank = isBlank("1");
		System.out.println(blank);
		boolean notEmpty = isNotEmpty(" ");
		System.out.println(notEmpty);
		boolean hasText = hasText("test");
		System.out.println(hasText);
	}

}
