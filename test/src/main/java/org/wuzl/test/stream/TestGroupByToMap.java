package org.wuzl.test.stream;

import static java.util.stream.Collectors.groupingBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import org.wuzl.test.base.bean.Person;

/**
 * 测试根据list生成map
 * 
 * @author ziliang.wu
 *
 */
public class TestGroupByToMap {
	public static void main(String[] args) {
		List<Person> personList = new ArrayList<>();
		IntStream.range(1, 10).forEach(i -> {
			Person person = new Person();
			person.setId(i);
			person.setName("name" + i);
			personList.add(person);
		});
		Map<String, List<Person>> map1 = personList.stream().collect(groupingBy(Person::getName));// 生成map<String,List>
		System.out.println(map1);//
//		Map<String, Person> map2 = personList.stream().collect(groupingBy(Person::getName));// 生成map<String,List>

	}
}
