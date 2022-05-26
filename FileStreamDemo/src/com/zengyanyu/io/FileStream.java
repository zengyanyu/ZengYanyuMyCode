package com.zengyanyu.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class FileStream {

	public static void main(String[] args) throws Exception {
		String filePath = "files/原材料采购订单.pdf";//文件地址
		FileOutputStream out = new FileOutputStream("myfile.txt");
		PrintStream p = new PrintStream(out);
		byte[] bytes = getBytes(filePath);
		String t = new String(bytes, "utf-8");
		p.println(t);
		p.close();
	}

	/**
	 * @description 获取文件字节流
	 * @param filePath {@link String} 文件地址
	 * @return
	 * @throws DefineException
	 * @date 2019年7月10日 上午9:17:00
	 * @author 宫清
	 */
	public static byte[] getBytes(String filePath) throws Exception {
		File file = new File(filePath);
		FileInputStream fis = null;
		byte[] buffer = null;
		try {
			fis = new FileInputStream(file);
			buffer = new byte[(int) file.length()];
			fis.read(buffer);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.initCause(e);
				}
			}
		}
		return buffer;
	}

}
