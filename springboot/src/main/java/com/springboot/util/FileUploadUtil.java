package com.springboot.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.util.FileCopyUtils;
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
	 * @param basePath 上传文件的路径,必须是这样的格式,如: F:\\upload 或者 F:/upload 这样的
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

	/**
	 * 文件上传
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年6月14日 下午2:39:53
	 * @param file       上传的文件名称
	 * @param filePath   上传的文件路径 ,格式: F:/upload/   最后加斜杠的形式
	 * @return           返回上传的新的文件名称
	 */
	public static String fileUpload(MultipartFile file, String filePath) {
		//获取文件的拓展名
		String extName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		String fileName = UUID.randomUUID().toString() + extName;
		try {
			FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(new File(filePath + fileName)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileName;
	}

}
