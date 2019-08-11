package org.wuzl.test.stringtest;


public class TestPerformance {
	public static void main(String[] args) {
		int count=100000;
		long begin=System.currentTimeMillis();
		String s="测试性能formt";
		for(int i=0;i<count;i++){
//			String s3=String.format("测试性能formt%d",i);
			String s1=s+i;
		}
		long end=System.currentTimeMillis();
		System.out.println("时间："+(end-begin));
		
	}
}
