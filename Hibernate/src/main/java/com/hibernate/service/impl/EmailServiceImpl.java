package com.hibernate.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.hibernate.config.EmailConfig;
import com.hibernate.service.IEmailService;

import freemarker.template.Template;

@Service("emailService")
@Transactional
public class EmailServiceImpl implements IEmailService {

	@Autowired
	private EmailConfig emailConfig;
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;

	/**
	 * 发送邮件
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年11月17日 上午10:19:10
	 * @param sendTo   发送的邮箱地址
	 * @param title    发送的标题
	 * @param content  发送的内容
	 */
	@Override
	public void sendSimpleMail(String sendTo, String title, String content) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(emailConfig.getEmailForm());
		message.setTo(sendTo);
		message.setSubject(title);
		message.setText(content);
		mailSender.send(message);
	}

	//发送带附件的邮件
	@Override
	public void sendAttachmentMail(String sendTo, String title, String content, File file) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(emailConfig.getEmailForm());
			helper.setTo(sendTo);
			helper.setSubject(title);
			helper.setText(content);

			FileSystemResource r = new FileSystemResource(file);
			helper.addAttachment("附件.txt", r);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//邮件发送
		mailSender.send(message);
	}

	//发送模板邮件
	@Override
	public void sendTemplateMail(String sendTo, String title, String info) {//模板参数名称---info
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(emailConfig.getEmailForm());
			helper.setTo(sendTo);
			helper.setSubject(title);
			//
			//封装模板使用的数据
			Map<String, Object> model = new HashMap<>();
			model.put("username", "小红");
			Template template = freeMarkerConfigurer.getConfiguration().getTemplate(info);
			//FreeMarker模板工具类
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
			//设置邮件内容
			helper.setText(html, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//邮件发送
		mailSender.send(message);
	}

}
