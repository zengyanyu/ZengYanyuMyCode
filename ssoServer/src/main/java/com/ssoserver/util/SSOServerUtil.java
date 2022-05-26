package com.ssoserver.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//单点登录服务端工具类
public class SSOServerUtil {

	//为单点注销做准备.验证token的时候也用到
	//Map<token, ArrayList<String[客户端注销的URL,客户端的sessionID]>>
	public static Map<String, List<String[]>> map = new HashMap<String, List<String[]>>();

}
