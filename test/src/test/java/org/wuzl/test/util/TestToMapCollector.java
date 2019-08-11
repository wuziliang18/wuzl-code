package org.wuzl.test.util;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import org.junit.Test;
import org.wuzl.test.base.bean.Person;

public class TestToMapCollector {
	@Test
	public void test() {
		List<Person> personList = new ArrayList<>();
		IntStream.range(1, 10).forEach(i -> {
			Person person = new Person();
			person.setId(i + 0l);
			person.setName("name" + i);
			personList.add(person);
		});
		ToMapCollector<Person, Long> collector = new ToMapCollector<>(Person::getId);

		Map<Long, Person> map = personList.stream().collect(collector);
		IntStream.range(1, 10).forEach(i -> {
			assertEquals(map.get(i + 0l).getName(), "name" + i);
		});
//		Person person = new Person();
//		person.setId(5l);// 覆盖5
//		person.setName("name10");
//		personList.add(person);
//		Map<Long, Person> map2 = personList.stream().collect(collector);
//		IntStream.range(1, 10).forEach(i -> {
//			assertEquals(map2.get(i + 0l).getName(), "name" + i);// 会报错
//		});
	}
}
