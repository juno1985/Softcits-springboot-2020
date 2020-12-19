package org.softcits.cn.controller;

import org.softcits.cn.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HelloController {
	
	@Autowired
	private Person person;
	
	@GetMapping("/hello")
	public ModelAndView hello() {
	
		return new ModelAndView("/page/Hello.html");
	}
	
	@GetMapping("/person/get")
	public Person getPerson() {
		//Person p1 = new Person();
		System.out.println(person.toString());
		return person;
	}

}
