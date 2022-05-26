package com.hibernate.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hibernate.service.IEmailService;

/**
 * 邮件发送
 * @author ZengYanyu
 * @Description
 * @Date 2020年11月17日 上午10:18:08
 * @see com.hibernate.controller.EmailController.java
 */
@Controller
public class EmailController {

	@Autowired
	private IEmailService emailService;

	//简单邮件
	@RequestMapping("/simple")
	@ResponseBody
	public String sendSimpleEmail() {
		emailService.sendSimpleMail("1194314874@qq.com", "曾~~",
				"本诗从严格好处上说不能算律诗，但它却被后人尊为唐人七律之首。《黄鹤楼》能成为千古绝唱，说明律诗气势、意境更为重要。诗人登临古迹黄鹤楼，泛览眼前景物，即景而生情，诗兴大作，脱口而出，一泻千里。既自然宏丽，又饶有风骨。诗虽不协律，但音节浏亮而不拗口。真是信手而就，一气呵成，成为历代所推崇的珍品。即使有一代诗仙之称的李白，也不由得佩服得连连赞叹，觉得自己还是暂时止笔为好。为此，李白还遗憾得叹气说：眼前好景道不得，崔颢题诗在上头!(也称七律第一!李白所赞叹的珍品)");
		return "success";
	}

	//附件邮件
	@RequestMapping("/attach")
	@ResponseBody
	public String sendAttachmentMail() {
		File file = new File("src/main/resources/static/面试总结.txt");
		emailService.sendAttachmentMail("1194314874@qq.com", "Hello", "你好", file);
		return "success";
	}

	//发送模板邮件
	@RequestMapping("/template")
	@ResponseBody
	public String templateMail() {
		emailService.sendTemplateMail("1194314874@qq.com", "哈哈", "info.html");
		return "success";
	}

}
