package org.wuzl.test.generic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestExtendGeneric {
	public static <T extends Base>  void test(T obj){
		obj.say();//可以调用base的
	}
	public static void main(String[] args) {
		test(new TestExtend());
//		List<Map> rows1=new ArrayList<HashMap>();//不可以
		List<Map> rows=new ArrayList<Map>();
		rows.add(new HashMap());
		writeTo(new ArrayList<Base>());
	}
	public static void writeTo(List<? super TestExtend> rows){
		rows.add(new TestExtend());
	}
}
class Base{
	public void say(){
		System.out.println("base function");
	}
}
class TestExtend extends Base{
	public void sayExtend(){
		System.out.println("extend");
	}
}