package org.wuzl.test.extend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestOverwrite {
	public static void main(String[] args) {
		Son son=new Son();
		Param param=new Param();
		Param2 param2=new Param2();
		son.say(param2);
		son.say(param);
		List<Son> rows=Collections.checkedList(new ArrayList<Son>(), Son.class);
	}
}
class Parent{
	public void say(Param2 param){
		System.out.println("parent");
		param.say();
	}
}
class Son extends Parent{
	public void say(Param param){//不会重写父类
		System.out.println("son1");
		param.say();
	}
	public void say(Param2 param){//重写父类
		System.out.println("son2");
		param.say();
	}
}
class Param{
	public void say(){
		System.out.println("param");
	}
}
class Param2 extends Param{
	public void say(){
		System.out.println("Param2");
	}
}