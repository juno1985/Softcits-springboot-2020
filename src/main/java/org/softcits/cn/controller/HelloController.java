package org.softcits.cn.controller;

import java.util.ArrayList;
import java.util.List;

import org.softcits.cn.model.ClassRoom;
import org.softcits.cn.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HelloController {
	
	@Autowired
	private Person person;
	
	@Autowired
	private ClassRoom classRoom;
	
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
	
	@GetMapping("/classroom/get")
	public ClassRoom getClassRoom() {
		List<Person> list = new ArrayList<>();
		
		Person p1 = new Person();
		p1.setName("Juno");
		p1.setAge(20);
		
		Person p2 = new Person();
		p2.setName("Mark");
		p2.setAge(30);
		
		list.add(p1);
		list.add(p2);
		
		classRoom.setPerson(list);
		
		return classRoom;
	}
	

}
