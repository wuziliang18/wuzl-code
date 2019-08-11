package org.wuzl.test.collectiontest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestCollection {
	public static void main(String[] args) {
		List first=new ArrayList();
		Map<String,String> dto=new HashMap<String,String>();
		dto.put("a", "1");
		first.add(dto);
		dto=new HashMap<String,String>();
		dto.put("b", "2");
		first.add(dto);
		System.out.println(first);
//		Collection sec=Collections.unmodifiableList(first);//共享数据 
//		System.out.println(sec);
////		sec.remove(dto);//报错 只读
//		first.remove(1);
//		System.out.println(first);
//		System.out.println(sec);
//		first.clear();
//		System.out.println(first);
//		System.out.println(sec);
		
		Collection sec=Collections.unmodifiableCollection(first);//共享数据 
		System.out.println(sec);
//		sec.remove(dto);//报错 只读
		first.remove(1);
		System.out.println(first);
		System.out.println(sec);
		first.clear();
		System.out.println(first);
		System.out.println(sec);
	}
}
