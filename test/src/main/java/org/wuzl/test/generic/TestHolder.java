package org.wuzl.test.generic;

public class TestHolder {
	public static <T>void f1(Holder<T> holder){
		System.out.println(holder.getClass().getSimpleName());
	}
	public static void main(String[] args) {
		Holder holder=new Holder();
		f1(holder);
	}
}
class Holder<T>{
	
}