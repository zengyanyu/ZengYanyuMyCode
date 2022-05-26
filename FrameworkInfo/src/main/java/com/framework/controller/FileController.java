package com.framework.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传
 * @author ZengYanyu
 * @Description
 * @Date 2020年10月30日 下午11:06:22
 * @see com.framework.controller.FileController.java
 */
@Controller
public class FileController {

	/**
	 * 文件上传
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年10月30日 下午10:27:32
	 * @param file
	 * @throws Exception 
	 */
	@RequestMapping("/upload")
	public void fileUpload(MultipartFile file) throws Exception {
		System.out.println("=======================");
		System.out.println(file.getName());
		System.out.println(file.getContentType());
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getSize());
		System.out.println("=======================");
		FileOutputStream outputStream = new FileOutputStream("F:/upload/1.jpg");
		IOUtils.copy(file.getInputStream(), outputStream);
		outputStream.close();
	}

	//文件下载
	@RequestMapping("/download")
	public void download(HttpServletResponse response) throws Exception {
		//对中文进行处理
		String fileName = new String("中文".getBytes("UTF-8"), "ISO8859-1");
		//设置文件下载的响应头
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".jpg");
		//读取本地某个文件
		FileInputStream inputStream = new FileInputStream("F:/upload/1.jpg");
		//输出到浏览器
		IOUtils.copy(inputStream, response.getOutputStream());
		//关闭资源
		inputStream.close();
	}

}
