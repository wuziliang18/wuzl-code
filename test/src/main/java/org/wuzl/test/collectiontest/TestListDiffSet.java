package org.wuzl.test.collectiontest;

import java.util.ArrayList;
import java.util.List;

/**
 * list差集
 * @author wuzl
 *
 */
public class TestListDiffSet {
	public static void main(String[] args) {
		List<String> rows=new ArrayList<String>();
		rows.add("a");
		rows.add("b");
		rows.add("c");
		List<String> rows2=new ArrayList<String>();
		rows2.add("a");
		rows2.add("b");
		rows2.add("d");
		rows2.removeAll(rows);
		System.out.println(rows2);
	}
}
