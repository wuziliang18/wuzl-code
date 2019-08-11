package org.wuzl.test.yoloho;

public class CopyOfPerformance {
	public static void main(String[] args) {
		int array[]={40,40,5,15};
//		int array2[]={95,90,95,90};//91
		int array2[]={89,89,95,88};
		int count=0;
		for(int i=0;i<array.length;i++){
			int point=array[i]*array2[i]/100;
			System.out.println(point);
			count+=point;
		}
		System.out.println(count);
//		int count=89;
//		int sum=0;
//		int countPoins=0;
//		for(int i=0;i<array.length;i++){
//			int point=array[i]*100;
//			countPoins+=point;
//			System.out.println(point);
//			sum+=array[i];
//		}
//		System.out.println(sum);
//		System.out.println(countPoins);
	}
}
