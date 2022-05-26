package com.hibernate.service;

import java.io.File;

public interface IEmailService {

	/**
	 * 发送简单的邮件
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年11月17日 上午10:18:33
	 * @param sendTo  发送的邮箱地址
	 * @param title   发送的标题
	 * @param content 发送的内容
	 */
	void sendSimpleMail(String sendTo, String title, String content);

	/**
	 * 发送带附件的邮件
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年11月17日 上午10:26:52
	 * @param sendTo  发送的邮箱地址
	 * @param title   发送的标题
	 * @param content 发送的内容
	 * @param file    发送的附件
	 */
	void sendAttachmentMail(String sendTo, String title, String content, File file);

	/**
	 * 发送模板邮件
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年11月17日 上午10:46:19
	 * @param sendTo  发送的邮箱地址
	 * @param title   发送的标题
	 * @param info    模板参数名称
	 */
	void sendTemplateMail(String sendTo, String title, String info);

}
