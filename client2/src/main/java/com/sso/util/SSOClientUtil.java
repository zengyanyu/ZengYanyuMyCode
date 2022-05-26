package com.sso.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("all")
public class SSOClientUtil {

	//ͳһ��֤���ĵ�ַ(����)
	public static final String SERVER_DOMAIN = "http://www.sso.com";
	//ͳһ��֤���ļ���Ƿ��Ѿ���¼�ķ��ʵ�ַ
	public static final String SERVER_CHECK_URL = "/checkLogin?redirectUrl=";
	//ͳһ��֤���ĵ�token��֤��ַ
	public static final String SERVER_VERIFY_URL = "/verify";
	//ͳһ��֤���ĵĵǳ���ַ
	public static final String SERVER_LOGOUT_URL = "/logOut";
	//�ͻ��˵ǳ���ַ
	public static final String CLIENT_LOGOUT_URL = "/logOut";
	//ͳһ��֤���ĵ�token��֤������token��������
	public static final String TOKEN_NAME = "token";
	//ͳһ��֤���ĵ�token��֤�����ĵǳ���ַ������
	public static final String CLIENTURL = "clientUrl";
	//ͳһ��֤���ĵ�token��֤������jsessionid������
	public static final String JSESSIONID = "jsessionid";

	/**
	 * ��ȡ�ͻ��������ĵǳ���ַhttp://www.client1.com:8081/logOut
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020��11��2�� ����6:35:41
	 * @param request
	 * @return
	 */
	public static String getClientLogOutUrl(HttpServletRequest request) {
		//��ȡ����ʹ�õ�Э�� http����https
		String scheme = request.getScheme();
		//��ȡ��������
		String serverName = request.getServerName();
		//��ȡ����Ķ˿ں�
		int serverPort = request.getServerPort();
		//��ȡ����URL
		String servletPath = request.getServletPath();
		String url = scheme + "://" + serverName + ":" + serverPort + CLIENT_LOGOUT_URL;
		return url;
	}

	/**
	 * ��ȡ��֤���ĵǳ���ַ http://www.sso.com/logOut
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020��11��2�� ����6:41:20
	 * @return
	 */
	public static String getServerLogOutUrl() {
		String serverURL = SERVER_DOMAIN;
		String logOutUrl = SERVER_LOGOUT_URL;
		return serverURL + logOutUrl;
	}

	/**
	 * ��ȡ�ض���URL
	 * 
	 * ���ͻ�����������,����ͳһ��֤����,��Ҫ��redirectUrl�Ĳ���,ͳһ��֤���ĵ�¼��ص��ĵ�ַ
	 * ͨ��Request��ȡ�������ĵ�ַ http://www.client1.com:8081/main
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020��11��2�� ����6:44:20
	 * @param request
	 * @return
	 */
	public static String getRedirectUrl(HttpServletRequest request) {
		//��ȡ����ʹ�õ�Э��http����https
		String scheme = request.getScheme();
		//��ȡ�������� www.client1.com
		String serverName = request.getServerName();
		//��ȡ����Ķ˿ں� 8081
		int serverPort = request.getServerPort();
		//��ȡ����URL
		String servletPath = request.getServletPath();
		String url = scheme + "://" + serverName + ":" + serverPort + servletPath;
		return url;
	}

	/**
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020��11��2�� ����6:47:07
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
	 * ��֤token�Ƿ���Ч,�����Ч,�ѿͻ��˵�¼��ַ��jsessionid���ݵ�ͳһ��֤����,������е���ע��
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020��11��2�� ����8:49:56
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
