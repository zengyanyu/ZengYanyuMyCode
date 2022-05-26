package com.sso.util;

import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.util.StreamUtils;

public class HttpUtil {

	/**
	 * 模拟浏览器的请求
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年11月2日 下午6:07:42
	 * @param httpURL 发送请求的地址
	 * @param params  请求参数
	 * @return
	 * @throws Exception
	 */
	public static String sendHttpRequest(String httpURL, Map<String, String> params) throws Exception {
		//建立URL连接对象
		URL url = new URL(httpURL);
		//创建连接
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		//设置请求方式(需要的是大写)
		conn.setRequestMethod("POST");
		//设置需要响应结果
		conn.setDoOutput(true);
		//判断是否有参数
		if (params != null && params.size() > 0) {
			StringBuffer sb = new StringBuffer();
			for (Entry<String, String> entry : params.entrySet()) {
				sb.append("&").append(entry.getKey()).append("=").append(entry.getValue());
			}
			//sb.substring(1)去掉最前面的&
			conn.getOutputStream().write(sb.substring(1).toString().getBytes("utf-8"));
		}
		//发送请求到服务器
		conn.connect();
		//获取远程响应的内容
		String responseContent = StreamUtils.copyToString(conn.getInputStream(), Charset.forName("utf-8"));
		conn.disconnect();
		return responseContent;
	}

	/**
	 * 模拟浏览器的请求
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年11月2日 下午6:20:56
	 * @param httpURL 发送请求的地址
	 * @param jsessionId  回话ID
	 * @throws Exception
	 */
	public static void sendHttpRequest(String httpURL, String jsessionId) throws Exception {
		//建立URL连接对象
		URL url = new URL(httpURL);
		//创建连接
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		//设置请求方式(需要的是大写)
		conn.setRequestMethod("POST");
		//设置需要响应结果
		conn.setDoOutput(true);
		conn.addRequestProperty("Cookie", "JSESSIONID" + jsessionId);
		//发送请求到服务器
		conn.connect();
		conn.getInputStream();
		conn.disconnect();
	}

}
