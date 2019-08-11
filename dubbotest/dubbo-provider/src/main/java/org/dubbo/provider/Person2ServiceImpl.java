package org.dubbo.provider;

import org.dubbo.api.bean.Person;
import org.dubbo.api.exception.UicException;
import org.dubbo.api.interfaces.IPerson2Service;

public class Person2ServiceImpl implements IPerson2Service {

	@Override
	public Person getPerson(long id) {
		if(id==-1){
			throw new UicException("id不能为-1",1000);
		}
		Person person=new Person();
		person.setId(id);
		person.setName("某某2");
//		try {
//			Thread.sleep(2000l);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return person;
	}

}
