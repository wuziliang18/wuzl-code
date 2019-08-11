package org.wuzl.test.collectiontest;

import java.util.ArrayList;
import java.util.List;

import org.wuzl.test.base.bean.Person;
/**
 * 测试list的contains 与hashmap之类类似 注意要重写equals
 * @author wuzl
 *
 */
public class TestListGetObject {
	public static void main(String[] args) {
		List<Person> rows1=new ArrayList<Person>();
		Person person=new Person();
		person.setId(1);
		person.setSex((short) 1);
		person.setName("111");
		rows1.add(person);
		person=new Person();
		person.setId(2);
		person.setSex((short) 1);
		person.setName("222");
		rows1.add(person);
		System.out.println(rows1);
		System.out.println(rows1.contains(person));//true
		person=new Person();
		person.setId(2);
		person.setSex((short) 1);
		person.setName("222");
		System.out.println(rows1.contains(person));//false
	}
}
