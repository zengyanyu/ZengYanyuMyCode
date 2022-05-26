package com.framework.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

	/**
	 * 使用注解的方式,需要在applicationContext.xml文件中配置对应的类的Bean对象信息
	 * 或者使用第二种方式,扫描controller包的方式
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年10月30日 下午8:12:41
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/value1")
	public ModelAndView value(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("msg", "hello1_annotation");
		request.getRequestDispatcher("/index.jsp").forward(request, response);
		return null;
	}

	@RequestMapping("/delete")
	public ModelAndView delete() throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg", "ModelAndView");
		mv.setViewName("/index");
		return mv;
	}

	/**
	 * 返回的试图名称如果配置了试图解析器org.springframework.web.servlet.view.InternalResourceViewResolver
	 * 类的话,试图名就不需要重新配置过多的后缀
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年10月30日 下午10:07:16
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/index2")
	public ModelAndView index() throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg", "ModelAndView");
		Map<String, Object> map = new HashMap<>();
		map.put("name", "jack");
		map.put("age", 17);
		mv.addAllObjects(map);
		//mv.setViewName("index.jsp");
		mv.setViewName("index");
		return mv;
	}

}
