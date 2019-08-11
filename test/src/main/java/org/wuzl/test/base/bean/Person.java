package org.wuzl.test.base.bean;

import java.util.Date;

public class Person {
	private Long id;
	private short sex;
	private String name;
	private Date birthDay;
	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	public short getSex() {
		return sex;
	}
	public void setSex(short sex) {
		this.sex = sex;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
}
