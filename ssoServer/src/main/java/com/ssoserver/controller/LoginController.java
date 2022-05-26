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

	//这个地址要跟检查认证中心的地址相同
	@RequestMapping("/checkLogin")
	public String checkLogin(String redirectUrl, Model model, HttpSession session) {
		//如果已经登录了(有其他客户端登录过),就返回令牌给客户端
		Object token = session.getAttribute("token");
		if (token != null) {
			//已经登录过
			//就返回令牌给客户端
			model.addAttribute("token", token);
			return "redirect:" + redirectUrl;
		}
		model.addAttribute("redirectUrl", redirectUrl);
		//返回到登录页面
		return "login";
	}

	@RequestMapping("/login")
	public String login(String username, String password, //
			String redirectUrl, Model model, HttpSession session) {
		//做账号密码校验,如果校验成功需要重定向到客户端
		if ("zhangsan".equals(username) && "666".equals(password)) {
			//创建令牌
			String token = UUID.randomUUID().toString();
			//创建全局回话
			session.setAttribute("token", token);
			//放入Map中(只要存在map中的token都是合法的)
			SSOServerUtil.map.put(token, new ArrayList<String[]>());

			//把令牌传给客户端
			model.addAttribute("token", token);
			return "redirect:" + redirectUrl;
		}

		model.addAttribute("errorMessage", "账号或者密码错误");
		model.addAttribute("redirectUrl", redirectUrl);

		return "login";
	}

	//验证(这里使用boolean类型是会报错的,所以使用String类型)
	@RequestMapping("/verify")
	@ResponseBody
	public String verify(String token) {
		//验证token是否合法
		List<String[]> list = SSOServerUtil.map.get(token);
		if (list != null) {
			//合法的
			return "true";
		}
		//不合法
		return "false";
	}
}
