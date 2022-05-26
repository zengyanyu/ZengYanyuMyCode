package com.springboot.vo;

import com.springboot.domain.Department;
import com.springboot.domain.Person;

public class DemoVo {

	private Person person;

	private Department department;

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "DemoVo [person=" + person + ", department=" + department + "]";
	}

}
