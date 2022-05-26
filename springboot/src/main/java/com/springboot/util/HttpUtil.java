package com.springboot.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

/**
 * Http工具类
 * @author ZengYanyu
 * @Description
 * @Date 2020年5月10日 上午11:53:16
 */
public class HttpUtil {

	//构造器私有化
	private HttpUtil() {
	}

	/**
	 * 发送get请求
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年5月10日 上午11:53:28
	 * @param url
	 * @return
	 */
	public static String get(String url) {
		String result = "";
		InputStream in = null;
		try {
			// 打开和URL之间的连接
			HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestMethod("GET");
			// 建立实际的连接
			conn.connect();
			// 定义输入流来读取URL的响应
			in = conn.getInputStream();
			result = StreamUtils.copyToString(in, Charset.forName("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	/**
	 * 发送post请求
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年5月10日 上午11:53:48
	 * @param url
	 * @param paramStr
	 * @return
	 */
	public static String post(String url, String paramStr) {
		InputStream in = null;
		OutputStream os = null;
		String result = "";
		try {
			// 打开和URL之间的连接
			HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			// 发送POST请求须设置
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			os = conn.getOutputStream();
			// 注意编码格式，防止中文乱码
			if (StringUtils.hasText(paramStr)) {
				os.write(paramStr.getBytes("UTF-8"));
				os.close();
			}
			in = conn.getInputStream();
			result = StreamUtils.copyToString(in, Charset.forName("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (os != null) {
					os.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

}