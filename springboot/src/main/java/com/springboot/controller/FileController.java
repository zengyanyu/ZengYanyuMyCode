package com.springboot.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.annotation.RequiredPermission;
import com.springboot.util.FileUploadUtil;

@Controller
public class FileController extends BaseController {

	@Value("${file.path}")
	private String filePath;

	/**
	 * 文件上传
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年7月4日 上午11:50:53
	 * @param file
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@RequiredPermission("文件上传")
	@RequestMapping("/upload")
	@ResponseBody
	public String upload(MultipartFile file) throws Exception {
		//获取文件的拓展名
		/*String extName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		String fileName = UUID.randomUUID().toString() + extName;
		FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(new File(filePath + fileName)));*/

		//调用工具类
		//String fileName = FileUploadUtil.fileUpload(file, filePath);
		String fileName = FileUploadUtil.upload(file, "F:/upload");
		return fileName;
	}

}
