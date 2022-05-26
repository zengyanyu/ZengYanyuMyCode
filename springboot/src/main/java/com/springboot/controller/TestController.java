package com.springboot.controller;

import com.springboot.domain.Department;
import com.springboot.domain.Person;
import com.springboot.vo.DemoVo;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class TestController extends BaseController {

	@RequestMapping("/index")
	public ModelAndView index(@ModelAttribute("dept") Department dept, //
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/index.jsp");
		return mv;
	}

	/**
	 * 前台传递的参数和后台不匹配的情况,使用@RequestParam的注解进行指定
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年10月30日 上午9:53:03
	 * @param username
	 * @param password
	 */
	@RequestMapping("/index2")
	public void index(@RequestParam("name") String username, String password) {
		System.out.println(username);
		System.out.println(password);
	}

	/**
	 * 传递的参数是JSON的格式,使用@RequestBody的注解
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年10月30日 上午9:55:39
	 */
	@RequestMapping("/index3")
	public void index(@RequestBody List<Department> depts) {
	}

	/**
	 * 地址栏传参
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年10月30日 上午10:05:03
	 * @param id
	 */
	@RequestMapping("/delete/${id}")
	//把地址中那个叫参数的名称封装到方法形参上,使用@PathVariable注解
	public void delete(@PathVariable("id") Long id, HttpServletResponse response) {
	}

	//第一种方式:多个对象的传递.  如果是同名的,每个对象都可以封装进去
	@RequestMapping("/test")
	public void test(DemoVo vo) {
		System.out.println(vo);
	}

	//=========================
	//第二种方式:多个对象的传递  需要创建每个对象对应的绑定方法
	//注意点:@ModelAttribute("名称")和@InitBinder("名称")的名称需要对应上
	@RequestMapping("/test1")
	public void test(@ModelAttribute("person") Person person, //
			@ModelAttribute("department") Department department) {

	}

	@InitBinder("person")
	public void initPerson(WebDataBinder binder) {
		//字段设置默认的前缀
		binder.setFieldDefaultPrefix("person.");
	}

	@InitBinder("department")
	public void initDepartment(WebDataBinder binder) {
		//字段设置默认的前缀
		binder.setFieldDefaultPrefix("department.");
	}

}
