package com.springboot.utils;

import java.io.File;
import java.net.URL;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public class WsdWebPathUtils {

	/**
	 * @return 濡�:F:\TongJianpeng\J2EEUtil
	 * */
	public static String getAbsolutePathWithProject() {
		return System.getProperty("user.dir");
	}

	public static String getDriverPathWithProject() {
		return new File("/").getAbsolutePath();
	}

	/**
	 * @return 椤圭洰鏍圭洰.渚嬪<br/> F:\tomcat\webapps\J2EEUtil\
	 * */
	public static String getAbsolutePathWithWebProject(HttpServletRequest request) {
		return request.getSession().getServletContext().getRealPath("/");
	}

	/**
	 * @param 椤圭洰鏍圭洰涓嬬殑鎸囧畾鐩綍
	 *            .渚嬪:/login/
	 * @return 椤圭洰鏍圭洰涓嬬殑鎸囧畾鐩綍.渚嬪:<br/> F:\tomcat\webapps\J2EEUtil\login\
	 * */
	public static String getAbsolutePathWithWebProject(HttpServletRequest request, String path) {
		return request.getSession().getServletContext().getRealPath(path);
	}

	/**
	 * 鑾峰彇椤圭洰鏍圭洰褰曠殑缁濆璺緞
	 * 
	 * @return 椤圭洰鏍圭洰.渚嬪<br/> F:\tomcat\webapps\J2EEUtil\
	 * */
	public static String getAbsolutePathWithWebProject(ServletContext context) {
		return context.getRealPath("/");
	}

	/**
	 * 鑾峰彇椤圭洰鏍圭洰褰曚笅鐨勬寚瀹氱洰褰曠殑缁濆璺緞
	 * 
	 * @param 椤圭洰鏍圭洰涓嬬殑鎸囧畾鐩綍
	 *            .渚嬪:/login/
	 * @return 椤圭洰鏍圭洰涓嬬殑鎸囧畾鐩綍.渚嬪:<br/> F:\tomcat\webapps\J2EEUtil\login\
	 * */
	public static String getAbsolutePathWithWebProject(ServletContext context, String path) {
		return context.getRealPath(path);
	}

	/**
	 * 鑾峰彇椤圭洰classpath鐩綍鐨勭粷瀵硅矾寰�
	 * 
	 * @return classes鐩綍鐨勭粷瀵硅矾寰�<br/>
	 *         file:/F:/tomcat/webapps/J2EEUtil/WEB-INF/classes/
	 * */
	public static URL getAbsolutePathWithClass() {
		return WsdWebPathUtils.class.getResource("/");
	}

	/**
	 * 鑾峰彇椤圭洰classPath鐩綍涓嬬殑鎸囧畾鐩綍鐨勭粷瀵硅矾寰�
	 * 
	 * @param path
	 *            classes鐩綍涓嬬殑鎸囧畾鐩綍.姣斿:/com/
	 * @return file:/F:/tomcat/webapps/J2EEUtil/WEB-INF/classes/com/
	 * */
	public static URL getAbsolutePathWithClass(String path) {
		return WsdWebPathUtils.class.getResource(path);
	}

	/**
	 * 鑾峰彇鎸囧畾绫绘枃浠剁殑鎵�鍦ㄧ洰褰曠殑缁濆璺緞
	 * 
	 * @param clazz
	 *            绫�
	 * @return 绫绘枃浠剁殑缁濆璺緞.渚嬪:<br/> 鍖卌om.Aries.Util.Web涓嬬殑Main.java绫�.<br/>
	 *         璺緞涓�:file:/
	 *         F:/tomcat/webapps/J2EEUtil/WEB-INF/classes/com/Aries/Util/Web/
	 * */
	public static URL getAbsolutePathWithClass(Class<?> clazz) {
		return clazz.getResource("");
	}

	/**
	 * 鏍规嵁鐩稿璺緞鑾峰彇鏂囦欢锛堟敮鎸丩inux涓嬬殑WebLogic锛岀敤瀹屽悗锛岃鑷鍒犻櫎鏂囦欢锛�
	 * @param relativePath 鐩稿璺緞濡傦細/config/xx.xml
	 * @param suffix 鐢熸垚鐨勪复鏃舵枃浠剁殑鍚庣紑鍚�
	 * @return 鐢熸垚鐨勪复鏃舵枃浠�
	 */
	public static File getFileByRelativePath(String prefix, String relativePath) throws Exception {
		String suffix = null;
		if (prefix == null) {
			prefix = "";
		}
		int i = relativePath.lastIndexOf('.');
		if (i > 0) {
			suffix = relativePath.substring(i);
		}
		File f = File.createTempFile(prefix + UUID.randomUUID().toString(), suffix == null ? ".tmp" : suffix);
		return f;
	}

}
