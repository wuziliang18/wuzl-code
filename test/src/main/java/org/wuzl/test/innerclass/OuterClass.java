package org.wuzl.test.innerclass;

public class OuterClass {
	public void outSay(){
		System.out.println("im out");
	}
	//小心作用于 如果改为private 外部不可以访问 只能用接口 和outer里加入inner的初始化方法
	public class InnerClass{
		public InnerClass() {
			System.out.println("inner create");
		}
		public void innnerSay(){
			System.out.println("im innner");
		}
		public OuterClass getOut(){
			System.out.println("return out");
			return OuterClass.this;
		}
	}
	public static void main(String[] args) {
		OuterClass out=new OuterClass();
		InnerClass inner=out.new InnerClass();//创建内部类
		inner.getOut().outSay();//inner获取out
	}
	
}
