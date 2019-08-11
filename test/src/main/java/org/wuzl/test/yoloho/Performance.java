package org.wuzl.test.yoloho;

public class Performance {
	public static void main(String[] args) {
		int rule[]={12,6,32,5,23,22};
		int array[]={83,82,89,85,90,92};
		int count=0;
		int jishu=0;
		for(int i=0;i<rule.length;i++){
			count+=rule[i];
			jishu+=array[i]*rule[i];
		}
		System.out.println(count);
		System.out.println("技术"+jishu);
	}
}
