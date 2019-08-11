package org.wuzl.test.innerclass;

import org.wuzl.test.innerclass.OuterClass.InnerClass;


public class Test {
	public static void main(String[] args) {
		OuterClass out=new OuterClass();
		InnerClass inner=out.new InnerClass();//创建内部类
		inner.getOut().outSay();//inner获取out
	}
}
