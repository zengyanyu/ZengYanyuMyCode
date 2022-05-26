package com.framework.util;

import java.net.URL;

import javax.servlet.http.HttpServletRequest;

/**
 * 文件路径工具类
 * @author ZengYanyu
 * @Description
 * @Date 2020年5月10日 下午4:53:40
 */
public class FilePathUtils {

	public static void main(String[] args) {
		String property = System.getProperty("user.dir");
		System.out.println(property);

		//得到的是当前类FileTest.class文件的URI目录。不包括自己！
		URL resource = FilePathUtils.class.getResource("");
		System.out.println(resource);
	}

	/**
	 * 得到项目的根目录的绝对路径
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年5月10日 下午4:59:29
	 * @param request
	 * @return
	 */
	public static String getPath(HttpServletRequest request) {
		//表示到项目的根目录下，要是想到目录下的子文件夹，修改"/"即可
		String path = request.getSession().getServletContext().getRealPath("/");
		path = path.replaceAll("\\\\", "/");
		return path;
	}

}
