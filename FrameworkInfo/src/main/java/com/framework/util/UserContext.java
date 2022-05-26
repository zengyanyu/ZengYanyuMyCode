package com.framework.util;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.framework.domain.Userinfo;

/**
 * 用户上下文信息
 * @author ZengYanyu
 * @Description
 * @Date 2020年5月1日 上午11:57:38
 */
@SuppressWarnings("all")
public class UserContext {

	/**
	 * 用户信息
	 */
	public static final String USER_IN_SESSION = "user_in_session";
	/**
	 * 用户所拥有的权限信息
	 */
	public static final String PERMISSIONS_IN_SESSION = "permissions_in_session";

	/**
	 * 抽取获取Session的一个公共方法;
	 * 只能在本类中使用,所有修饰符使用private修饰;
	 * 当做一个工具类,所以定义为一个static的方法,方便调用     
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年5月1日 下午12:01:40
	 * @param request
	 * @return
	 */
	private static Map<String, Object> getSession(HttpServletRequest request) {
		return (Map<String, Object>) request.getSession();
	}

	/**
	 * 往session中存放用户信息
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年5月1日 下午12:03:49
	 * @param currentUser  当前用户的对象信息
	 * @param request
	 */
	public static void setCurrentUser(Userinfo currentUser, HttpServletRequest request) {
		getSession(request).put(USER_IN_SESSION, currentUser);
	}

	/**
	 * 从session中去获取当前用户信息
	 */
	public static Userinfo getCurrentUser(HttpServletRequest request) {
		return (Userinfo) getSession(request).get(USER_IN_SESSION);
	}

	/**
	 * 往session中存放权限信息
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年5月1日 上午11:59:19
	 * @param permissions  存入的是当前用户所拥有的权限信息,存储的是一个Set集合对象
	 * @param request
	 */
	public static void setCurrentPermission(Set<String> permissions, HttpServletRequest request) {
		getSession(request).put(PERMISSIONS_IN_SESSION, permissions);
	}

	/**
	 * 从session中取出权限信息
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年5月1日 上午11:58:50
	 * @param request
	 * @return  返回的是String类型的Set集合对象信息
	 */
	public static Set<String> getCurrentPermission(HttpServletRequest request) {
		return (Set<String>) getSession(request).get(PERMISSIONS_IN_SESSION);
	}

}
