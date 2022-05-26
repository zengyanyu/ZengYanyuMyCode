package com.ssoserver.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssoserver.util.SSOServerUtil;

@Controller
public class LoginController {

	//�����ַҪ�������֤���ĵĵ�ַ��ͬ
	@RequestMapping("/checkLogin")
	public String checkLogin(String redirectUrl, Model model, HttpSession session) {
		//����Ѿ���¼��(�������ͻ��˵�¼��),�ͷ������Ƹ��ͻ���
		Object token = session.getAttribute("token");
		if (token != null) {
			//�Ѿ���¼��
			//�ͷ������Ƹ��ͻ���
			model.addAttribute("token", token);
			return "redirect:" + redirectUrl;
		}
		model.addAttribute("redirectUrl", redirectUrl);
		//���ص���¼ҳ��
		return "login";
	}

	@RequestMapping("/login")
	public String login(String username, String password, //
			String redirectUrl, Model model, HttpSession session) {
		//���˺�����У��,���У��ɹ���Ҫ�ض��򵽿ͻ���
		if ("zhangsan".equals(username) && "666".equals(password)) {
			//��������
			String token = UUID.randomUUID().toString();
			//����ȫ�ֻػ�
			session.setAttribute("token", token);
			//����Map��(ֻҪ����map�е�token���ǺϷ���)
			SSOServerUtil.map.put(token, new ArrayList<String[]>());

			//�����ƴ����ͻ���
			model.addAttribute("token", token);
			return "redirect:" + redirectUrl;
		}

		model.addAttribute("errorMessage", "�˺Ż����������");
		model.addAttribute("redirectUrl", redirectUrl);

		return "login";
	}

	//��֤(����ʹ��boolean�����ǻᱨ���,����ʹ��String����)
	@RequestMapping("/verify")
	@ResponseBody
	public String verify(String token) {
		//��֤token�Ƿ�Ϸ�
		List<String[]> list = SSOServerUtil.map.get(token);
		if (list != null) {
			//�Ϸ���
			return "true";
		}
		//���Ϸ�
		return "false";
	}
}
