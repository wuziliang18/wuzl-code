package org.wuzl.test.classtest;

import java.util.ArrayList;
import java.util.List;

public class TestInstanceof {
	public static void main(String[] args) {
		List rows=new ArrayList();
		System.out.println(rows instanceof List);//true
		System.out.println(rows instanceof ArrayList);//true
	}
}
