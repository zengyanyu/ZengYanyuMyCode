package com.sso.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("all")
public class SSOClientUtil {

	//统一认证中心地址(域名)
	public static final String SERVER_DOMAIN = "http://www.sso.com";
	//统一认证中心检查是否已经登录的访问地址
	public static final String SERVER_CHECK_URL = "/checkLogin?redirectUrl=";
	//统一认证中心的token认证地址
	public static final String SERVER_VERIFY_URL = "/verify";
	//统一认证中心的登出地址
	public static final String SERVER_LOGOUT_URL = "/logOut";
	//客户端登出地址
	public static final String CLIENT_LOGOUT_URL = "/logOut";
	//统一认证中心的token认证方法的token参数名称
	public static final String TOKEN_NAME = "token";
	//统一认证中心的token认证方法的登出地址参数名
	public static final String CLIENTURL = "clientUrl";
	//统一认证中心的token认证方法的jsessionid参数名
	public static final String JSESSIONID = "jsessionid";

	/**
	 * 获取客户端完整的登出地址http://www.client1.com:8081/logOut
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年11月2日 下午6:35:41
	 * @param request
	 * @return
	 */
	public static String getClientLogOutUrl(HttpServletRequest request) {
		//获取请求使用的协议 http或者https
		String scheme = request.getScheme();
		//获取主机的名
		String serverName = request.getServerName();
		//获取请求的端口号
		int serverPort = request.getServerPort();
		//获取请求URL
		String servletPath = request.getServletPath();
		String url = scheme + "://" + serverName + ":" + serverPort + CLIENT_LOGOUT_URL;
		return url;
	}

	/**
	 * 获取认证中心登出地址 http://www.sso.com/logOut
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年11月2日 下午6:41:20
	 * @return
	 */
	public static String getServerLogOutUrl() {
		String serverURL = SERVER_DOMAIN;
		String logOutUrl = SERVER_LOGOUT_URL;
		return serverURL + logOutUrl;
	}

	/**
	 * 获取重定向URL
	 * 
	 * 当客户端请求被拦截,跳往统一认证中心,需要带redirectUrl的参数,统一认证中心登录后回调的地址
	 * 通过Request获取这次请求的地址 http://www.client1.com:8081/main
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年11月2日 下午6:44:20
	 * @param request
	 * @return
	 */
	public static String getRedirectUrl(HttpServletRequest request) {
		//获取请求使用的协议http或者https
		String scheme = request.getScheme();
		//获取主机的名 www.client1.com
		String serverName = request.getServerName();
		//获取请求的端口号 8081
		int serverPort = request.getServerPort();
		//获取请求URL
		String servletPath = request.getServletPath();
		String url = scheme + "://" + serverName + ":" + serverPort + servletPath;
		return url;
	}

	/**
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年11月2日 下午6:47:07
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	public static void redirectToSSOURL(HttpServletRequest request, HttpServletResponse response)//
			throws IOException {
		String serverURL = SERVER_DOMAIN;
		String checkUrl = SERVER_CHECK_URL;
		String redirectUrl = getRedirectUrl(request);
		StringBuilder url = new StringBuilder(50);
		url.append(serverURL).append(checkUrl).append(redirectUrl);
		response.sendRedirect(url.toString());
	}

	/**
	 * 验证token是否有效,如果有效,把客户端登录地址和jsessionid传递到统一认证中心,方便进行单点注销
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年11月2日 下午8:49:56
	 * @param token
	 * @param clientUrl
	 * @param jsessionid
	 * @return
	 */
	public static Boolean verify(String token, String clientUrl, String jsessionid) {
		String serverURL = SERVER_DOMAIN;
		String verifyURL = SERVER_VERIFY_URL;
		Map<String, String> params = new HashMap<String, String>();
		params.put(TOKEN_NAME, token);
		params.put(CLIENTURL, clientUrl);
		params.put(JSESSIONID, jsessionid);
		try {
			String responseContent = HttpUtil.sendHttpRequest(serverURL + verifyURL, params);
			if ("true".endsWith(responseContent)) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
}
