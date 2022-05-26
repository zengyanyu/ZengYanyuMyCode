package com.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.service.ILoginInfoService;
import com.springboot.util.AjaxResult;

/**
 * 登录信息控制器对象
 * @author ZengYanyu
 * @Description
 * @Date 2020年6月7日 下午9:17:33
 * @see com.springboot.controller.LoginInfoController.java
 */
@Controller
public class LoginInfoController extends BaseController {

	@Autowired
	private ILoginInfoService loginfoService;

	@RequestMapping("login")
	public String login() {
		return "login";
	}

	@RequestMapping("register")
	public String register() {
		return "register";
	}

	@RequestMapping("userRegister")
	@ResponseBody
	public AjaxResult register(String username, String password) {
		AjaxResult ajaxResult = new AjaxResult("成功");
		loginfoService.save(username, password);
		return ajaxResult;
	}

	/**
	 * 远程校验当前的用户名是否存在
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年6月7日 下午9:17:58
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "checkUsername", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult checkUsername(String username) {
		int count = loginfoService.countByUsername(username);
		if (count == 0) {
			AjaxResult ajaxResult = new AjaxResult("成功");
			return ajaxResult;
		} else {
			AjaxResult ajaxResult = new AjaxResult();
			ajaxResult.setMsg("用户名已存在");
			return ajaxResult;
		}
	}

}
