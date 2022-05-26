package com.framework.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * 上传工具
 *
 * @author Administrator
 */
public class FileUploadUtil {
	/**
	 * 需要引入的pom.xml依赖
	 *
	 <dependency>
	 <groupId>commons-fileupload</groupId>
	 <artifactId>commons-fileupload</artifactId>
	 <version>1.3.3</version>
	 </dependency>
	 */

	/**
	 * 处理文件上传
	 *
	 * @param file     上传的文件名称
	 * @param basePath 存放文件的目录的绝对路径 servletContext.getRealPath("/upload")
	 * @return
	 */
	public static String upload(MultipartFile file, String basePath) {
		//获取上传文件的原名   getOriginalFilename
		//获取表单中文件组件的名字  getName
		//获取文件的后缀 getExtension
		String orgFileName = file.getOriginalFilename();
		String fileName = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(orgFileName);
		try {
			File targetFile = new File(basePath, fileName);
			FileUtils.writeByteArrayToFile(targetFile, file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileName;
	}

}
