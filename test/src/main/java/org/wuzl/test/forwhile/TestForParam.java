package org.wuzl.test.forwhile;

import java.util.Collections;

public class TestForParam {
	public static void main(String[] args) {
		for(int i=0,n=getNum();i<n;i++){//只调用了一次getNum 作用在于减小了getNum的作用域
			
			System.out.println(i);
		}
	}
	public static int getNum(){
		System.out.println("进入方法");
		return 10;
	}
}
