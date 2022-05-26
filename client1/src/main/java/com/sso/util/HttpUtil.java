package com.sso.util;

import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.util.StreamUtils;

public class HttpUtil {

	/**
	 * ģ�������������
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020��11��2�� ����6:07:42
	 * @param httpURL ��������ĵ�ַ
	 * @param params  �������
	 * @return
	 * @throws Exception
	 */
	public static String sendHttpRequest(String httpURL, Map<String, String> params) throws Exception {
		//����URL���Ӷ���
		URL url = new URL(httpURL);
		//��������
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		//��������ʽ(��Ҫ���Ǵ�д)
		conn.setRequestMethod("POST");
		//������Ҫ��Ӧ���
		conn.setDoOutput(true);
		//�ж��Ƿ��в���
		if (params != null && params.size() > 0) {
			StringBuffer sb = new StringBuffer();
			for (Entry<String, String> entry : params.entrySet()) {
				sb.append("&").append(entry.getKey()).append("=").append(entry.getValue());
			}
			//sb.substring(1)ȥ����ǰ���&
			conn.getOutputStream().write(sb.substring(1).toString().getBytes("utf-8"));
		}
		//�������󵽷�����
		conn.connect();
		//��ȡԶ����Ӧ������
		String responseContent = StreamUtils.copyToString(conn.getInputStream(), Charset.forName("utf-8"));
		conn.disconnect();
		return responseContent;
	}

	/**
	 * ģ�������������
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020��11��2�� ����6:20:56
	 * @param httpURL ��������ĵ�ַ
	 * @param jsessionId  �ػ�ID
	 * @throws Exception
	 */
	public static void sendHttpRequest(String httpURL, String jsessionId) throws Exception {
		//����URL���Ӷ���
		URL url = new URL(httpURL);
		//��������
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		//��������ʽ(��Ҫ���Ǵ�д)
		conn.setRequestMethod("POST");
		//������Ҫ��Ӧ���
		conn.setDoOutput(true);
		conn.addRequestProperty("Cookie", "JSESSIONID" + jsessionId);
		//�������󵽷�����
		conn.connect();
		conn.getInputStream();
		conn.disconnect();
	}

}
