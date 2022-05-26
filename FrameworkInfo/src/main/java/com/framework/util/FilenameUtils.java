package com.framework.util;

/**
 * 文件名称工具类
 * @author ZengYanyu
 * @Description
 * @Date 2020年5月10日 下午2:35:19
 */
public class FilenameUtils extends org.apache.commons.io.FilenameUtils {

	/**
	 * getExtension: 返回文件后缀名
	 * 
	 * getBaseName：返回文件名，不包含后缀名
	 * 
	 * getName：返回文件全名
	 * 
	 * concat：按命令行风格组合文件路径(详见方法注释)
	 * 
	 * removeExtension：删除后缀名
	 * 
	 * normalize：使路径正常化
	 * 
	 * wildcardMatch：匹配通配符
	 * 
	 * seperatorToUnix：路径分隔符改成unix系统格式的，即/
	 * 
	 * getFullPath：获取文件路径，不包括文件名
	 * 
	 * isExtension：检查文件后缀名是不是传入参数(List<String>)中的一个
	 */

	public static void main(String[] args) {
		String fileName = "javaUtil.java";
		String extension = getExtension("javaUtil.java");
		System.out.println(extension);
		String baseName = getBaseName(fileName);
		System.out.println(baseName);
		String name = getName(fileName);
		System.out.println(name);
		String removeExtension = removeExtension(fileName);
		System.out.println(removeExtension);
		String normalize = normalize(fileName);
		System.out.println(normalize);
		boolean wildcardMatch = wildcardMatch(fileName, normalize);
		System.out.println(wildcardMatch);
	}

}
