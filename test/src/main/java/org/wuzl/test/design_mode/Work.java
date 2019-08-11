package org.wuzl.test.design_mode;

public class Work implements IWork{
	public void begin(){
		System.out.println("开始工作");
	}
	public String getName(){
		return "work";
	}
	public String work(String content){
		System.out.println("工作"+content);
		return "success";
	}
	public void end(){
		System.out.println("完成工作");
	}
}
