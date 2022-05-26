package com.framework.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.framework.domain.Userinfo;

@RestController
public class JsonController {

	/**
	 * 日期格式处理
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年10月31日 下午10:00:58
	 * @return
	 */
	@RequestMapping("/get")
	public List<Userinfo> getUserInfo() {
		List<Userinfo> list = new ArrayList<>();
		list.add(new Userinfo("jack", "password123456", new Date()));
		return list;
	}

	@RequestMapping("/save1")
	public void testDate(Userinfo userinfo) {
		System.out.println("inputTime = " + userinfo.getInputTime());
	}

	//处理日期类型的属性
	//前台往后台传递时间格式的,需要加方法
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		System.out.println("====---============");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

}
