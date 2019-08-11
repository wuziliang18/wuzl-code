package org.wuzl.test.classtest;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
/**
 * isAssignableFrom是父-->子
 * @author wuzl
 *
 */
public class TestAssignableFrom {
	public static void main(String[] args) {
		System.out.println(List.class.isAssignableFrom(ArrayList.class));//true
		System.out.println(List.class.isAssignableFrom(List.class));//true
		System.out.println(ArrayList.class.isAssignableFrom(List.class));//false
		System.out.println(List.class.isInstance(new ArrayList()));//true
		System.out.println(ArrayList.class.isInstance(new ArrayList()));//true
	}
}
