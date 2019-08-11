package org.wuzl.test.forwhile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestFor {
	public static void main(String[] args) {
		int order=1;
		for(int i=0;i<10;i++){
			System.out.println(order++);
		}
		int forNum=20000;
		Long l1=1l;
		List<Long> longs=new ArrayList<Long>();
		for(int i=0;i<forNum;i++){
			longs.add(i+0l);
		}
		Random random=new Random();
		Long begin=System.currentTimeMillis();
		for(int i=0;i<forNum;i++){
			if(l1.equals(longs.get(i))){
				
			}
		}
		Long useTime=System.currentTimeMillis()-begin;
		System.out.println(String.format("循环%d使用时间%d", forNum,useTime));
		
		int i=10;
		for(;i>0;i--){
			System.out.println(i);
		}
	}
}
