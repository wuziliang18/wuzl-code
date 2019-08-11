package org.wuzl.test.yoloho;

public class Points {
	public static void main(String[] args) {
		int[] array={29,18,8,14,13,10};
		int count=0;
		for(int point:array){
			count+=point;
		}
		System.out.println(count);
	}
}
