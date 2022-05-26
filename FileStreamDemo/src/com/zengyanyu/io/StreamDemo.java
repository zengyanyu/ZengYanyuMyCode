package com.zengyanyu.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.util.StringUtils;

@SuppressWarnings("all")
public class StreamDemo {

	public static final String url = "https://files.alicdn.com/tpsservice/7da854e121a5dc6eff4ed2cc4740a3b5.pdf";

	public static void main(String[] args) {
		//test1(url);
		test3();
	}

	private static void test3() {
		String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(1615346050373l));
		System.out.println(format);
		
	}

	private static void test1(String url) {
		long begin = System.currentTimeMillis();
		InputStream in = null;
		OutputStream out = null;
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
			//conn.connect();
			// 定义输入流来读取URL的响应
			in = new BufferedInputStream(conn.getInputStream());
			out = new BufferedOutputStream(new FileOutputStream("files/test3.pdf"));

			byte[] buffer = new byte[1024];
			int len = -1;
			while ((len = in.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
			//int data;
			//while ((data = in.read()) != -1) {
			//out.write(data);
			//}
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
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println(System.currentTimeMillis() - begin);
	}

	public static void test2(String url) {
		long begin = System.currentTimeMillis();
		InputStream in = null;
		OutputStream out = null;
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
			out = conn.getOutputStream();
			in = new BufferedInputStream(conn.getInputStream());
			//out = new BufferedOutputStream(new FileOutputStream("files/test3.pdf"));

			byte[] buffer = new byte[1024];
			int len = -1;
			while ((len = in.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
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
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println(System.currentTimeMillis() - begin);
	}

}
