package com.springboot.controller;

import com.springboot.domain.Person;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class PersonController extends BaseController {

	/**
	 * FastJson测试
	 * @author ZengYanyu
	 * @Description
	 * @Date 2020年7月11日 下午1:34:20
	 * @return
	 */
	@RequestMapping("/person")
	public Person getPerson() {
		Person person = new Person();
		person.setId(1L);
		person.setUsername("用户名");
		person.setDate(new Date());
		return person;
	}

}
