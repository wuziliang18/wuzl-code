package org.wuzl.test.collectiontest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Collections.fill使用的实际上是同一个对象
 * @author wuzl
 *
 */
public class TestCollections {
	public static void main(String[] args) {
		List<Map> rows=new ArrayList<Map>();
		Map dto=new HashMap();
		dto.put("a", "1");
		dto.put("b", "2");
		Collections.fill(rows, dto);
		System.out.println(rows.size());//0
		rows.add(null);
		rows.add(null);
		Collections.fill(rows, dto);
		System.out.println(rows.size());//2
		System.out.println(rows);//[{b=2, a=1}, {b=2, a=1}]
		rows.get(0).put("a", "a");
		System.out.println(rows);//[{b=2, a=a}, {b=2, a=a}]
	}
}
