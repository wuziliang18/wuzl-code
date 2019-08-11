package org.wuzl.test.array;

import java.util.Arrays;

public class TestArrayAndGeneric {
	public static void main(String[] args) {
		Param[] params={new Param(),new Param()};
		Integer[] intParams={1,2};
		String[] strParams={"1","2"};
		System.out.println(Arrays.toString(params));
		System.out.println(Arrays.toString(intParams));
		System.out.println(Arrays.toString(strParams));
		System.out.println(Arrays.toString(new ClassParamter<Integer>().get(intParams)));//必须是包装类
//		String[] strParams1=new ClassParamter().get(strParams);//不可以 需要强转
		String[] strParams2=new ClassParamter<String>().get(strParams);//可以

	}
}
class ClassParamter<T>{
	public T[] get(T[] param){
		return param;
	}
}