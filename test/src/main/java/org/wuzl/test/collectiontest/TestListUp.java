package org.wuzl.test.collectiontest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestListUp {
	public static void main(String[] args) {
		List<String> rows=new ArrayList<String>();
		rows.add("a");
		rows.add("b");
		rows.add("c");
		Iterator<String> ite=rows.iterator();
		while(ite.hasNext()){
			if(!ite.next().equals("1")){
				ite.remove();
				rows.add("1");
			}
		}
		System.out.println(rows);
	}
}
