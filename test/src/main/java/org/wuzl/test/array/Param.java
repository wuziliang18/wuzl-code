package org.wuzl.test.array;

public class Param {
	private static int NUM=1;
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private int id;
	public Param() {
		id=NUM++;
	}
	@Override
	public String toString() {
		return "param:"+name+"_"+id;
	}
}
