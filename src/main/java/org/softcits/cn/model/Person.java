package org.softcits.cn.model;

import org.springframework.stereotype.Component;

@Component
public class Person {
	
	private String name;

	private Integer age;
	
	public Person() {
		this.name = "Alex";
		this.age = 33;
	}
	
	public Person(String name) {
		
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
	
	

}
