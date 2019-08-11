package org.wuzl.test.array;

public class TestArray {
	public static void main(String[] args) {
		Param[] array=new Param[5];
		System.out.println(array.length);
//		array={new Param(),new Param()};//不可以编译
		array=new Param[]{new Param(),new Param()};//重新指向新数组
		System.out.println(array.length);
		Param[] array2={new Param(),new Param()};//可以编译
		System.out.println(array2.length);
	}
}
