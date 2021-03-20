package org.softcits.cn.model;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ClassRoom {

	private List<Person> personList;

	public List<Person> getPerson() {
		return personList;
	}

	public void setPerson(List<Person> personList) {
		this.personList = personList;
	}
	
	
}
