package com.hibernate.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

	@Value("${file.path}")
	private String filePath;

	//跳转到文件上传的页面
	@RequestMapping("/index")
	public String toUpload() {
		return "upload";
	}

	//跳转到批量文件上传的页面
	@RequestMapping("/index/batch")
	public String toUploadBatch() {
		return "batch";
	}

	//执行文件上传操作(上传到项目的绝对路径下)
	/***
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public String uplaod(MultipartFile file, HttpServletRequest request) {
		//获取文件后缀(从最后一个.开始截取到最后)
		String fileSuffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		//生成文件名称
		String fileName = UUID.randomUUID().toString() + fileSuffix;
		//创建文件在服务器端存放的路径
		String dir = request.getServletContext().getRealPath("/upload");
		File fileDir = new File(dir);
		if (!fileDir.exists()) {
			fileDir.mkdir();
		}
		File files = new File(fileDir + "/" + fileName);
		try {
			file.transferTo(files);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "上传成功";
	}
	*/

	//上传到文件的虚拟目录(磁盘文件)
	/**
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年11月17日 下午1:25:06
	 * @param file
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public String uplaod1(MultipartFile file) throws Exception {
		//获取文件的拓展名
		String extName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		String fileName = UUID.randomUUID().toString() + extName;
		File path = new File(filePath);
		if (!path.exists()) {
			path.mkdir();
		}
		FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(new File(filePath + fileName)));
		return "上传成功";
	}

	/**
	 * 实现多文件上传
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年11月17日 下午4:49:55
	 * @param file
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/upload/batch", method = RequestMethod.POST)
	@ResponseBody
	public String uplaodBatch(MultipartFile[] file, HttpServletRequest request) {
		//创建文件在服务器端存放的路径
		String dir = request.getServletContext().getRealPath("/upload");
		File fileDir = new File(dir);
		if (!fileDir.exists()) {
			fileDir.mkdir();
		}

		//这一点代码的性能和逻辑会更优一些
		//try放在for循环的外面，当遇到异常时，抛出异常，后面的循环就会终止，并不会执行。
		try {
			for (int i = 0; i < file.length; i++) {
				String extName = file[i].getOriginalFilename()
						.substring(file[i].getOriginalFilename().lastIndexOf("."));
				String fileName = UUID.randomUUID().toString() + extName;
				File files = new File(fileDir + "/" + fileName);
				//上传
				file[i].transferTo(files);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		//try放在for循环的里面所有的for循环都会执行，当遇到异常时，抛出异常继续执行；
		/**for (int i = 0; i < file.length; i++) {
			String extName = file[i].getOriginalFilename().substring(file[i].getOriginalFilename().lastIndexOf("."));
			String fileName = UUID.randomUUID().toString() + extName;
			File files = new File(fileDir + "/" + fileName);
			try {
				//上传
				file[i].transferTo(files);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}*/

		return "上传成功";
	}

}
