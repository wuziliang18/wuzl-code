package org.dubbo.provider;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.ibatis.exceptions.PersistenceException;
import org.dubbo.api.bean.Person;
import org.dubbo.api.exception.UicException;
import org.dubbo.api.interfaces.IPersonService;

public class PersonServiceImpl implements IPersonService {
	private final static AtomicInteger ID = new AtomicInteger();
	int i;
	public PersonServiceImpl() {
		i=new Random().nextInt();
	}
	@Override
	public Person getPerson(long id) {
		System.out.println("2进入getPerson");
		Person person = new Person();
		person.setId(id);
		person.setName("某某2");
		try {
			Thread.sleep(2000l);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return person;
	}

	@Override
	public Person getByNick(String name) {
		if (name == null) {
			// 简单异常
			// throw new RuntimeException("亲你咋没输入昵称呢");
			// 第三方异常
//			throw new PersistenceException("mybatis插入异常");
			// 自定义异常
			throw new UicException("mybatis插入异常", 0);
		}
		name = name.trim();
		Person person = new Person();
		person.setId(ID.getAndIncrement());
		person.setName(name);
		return person;
	}

	@Override
	public List<Person> getPeron() {
		Person person = new Person();
		person.setId(i);
		person.setName("某某"+i);
		List<Person> rows=new ArrayList<Person>();
		rows.add(person);
		return rows;
	}
}
