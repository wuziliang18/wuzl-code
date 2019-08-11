package org.wuzl.test._int;

public class TestAdd {
	public static void main(String[] args) {
		int count=0;
		while(true){
			System.out.println("进入一次");
			if((count++)==2){
				System.out.println(String.format("失败第[%d]次,tuichu ", count));
				break;
			}else{
				System.out.println(String.format("失败第[%d]次", count));
			}
		}
		
	}
}
