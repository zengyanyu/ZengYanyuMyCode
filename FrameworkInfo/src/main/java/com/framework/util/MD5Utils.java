package com.framework.util;

import java.security.MessageDigest;

/**
 * MD5工具类
 * @author ZengYanyu
 * @Description
 * @Date 2020年4月25日 下午6:33:50
 */
public abstract class MD5Utils {

	//构造器私有化
	private MD5Utils() {
	}

	/**
	 * 加密
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年4月25日 下午6:36:19
	 * @param pwd   用户传递的密码
	 * @return      返回加密之后的结果
	 */
	public final static String md5enc(String pwd) {
		//用于加密的字符  
		char md5String[] = { //
				'0', '1', '2', '3', '4', '5', '6', '7', //
				'8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			//使用平台的默认字符集将此 String 编码为 byte序列，并将结果存储到一个新的 byte数组中  
			byte[] btInput = pwd.getBytes();
			//信息摘要是安全的单向哈希函数，它接收任意大小的数据，并输出固定长度的哈希值。  
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			//MessageDigest对象通过使用 update方法处理数据， 使用指定的byte数组更新摘要  
			mdInst.update(btInput);
			// 摘要更新之后，通过调用digest（）执行哈希计算，获得密文  
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式  
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) { //i = 0  
				byte byte0 = md[i]; //95  
				str[k++] = md5String[byte0 >>> 4 & 0xf]; //5   
				str[k++] = md5String[byte0 & 0xf]; //F  
			}
			//返回经过加密后的字符串  
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

	public static void main(String[] args) {
		String md5 = MD5Utils.md5enc("1");
		System.out.println(md5);
	}

}
