package org.wuzl.test.array;

import java.util.ArrayList;
import java.util.List;

public class TestArrayOperator {
	public static void main(String[] args) {
		Param[] params={new Param(),new Param()};
		System.out.println(params[0]);
		Param param=params[0];
		System.out.println(param);
		param.setName("修改了");
		System.out.println(param);
		System.out.println(params[0]);//数组里的也变了
		
		int[] intParams={1,2};
		System.out.println(intParams[0]);
		int intParam=intParams[0];
		System.out.println(intParam);
		intParam=-1;
		System.out.println(intParam);
		System.out.println(intParams[0]);//数组里的没变
		
		
		String[] strParams={"1","2"};
		System.out.println(strParams[0]);
		String strParam=strParams[0];
		System.out.println(strParam);
		strParam="变了";
		System.out.println(strParam);
		System.out.println(strParams[0]);//数组里的没变
	}
}
