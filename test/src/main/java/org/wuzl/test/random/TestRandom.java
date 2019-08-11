package org.wuzl.test.random;

import java.util.Random;

public class TestRandom {
	public static void main(String[] args) {
		Random random=new Random(128);//加入种子后 每次随机的结果是一致的  如果不加入种子 或取时间戳 是真正的随机
		System.out.println(random.nextInt(1000));
		System.out.println(random.nextInt(1000));
		System.out.println(random.nextInt(1000));
		System.out.println(random.nextInt(1000));
		while(true){
			random=new Random(128);
			System.out.println(random.nextInt(1000));//结果始终不变
		}
	}
}
