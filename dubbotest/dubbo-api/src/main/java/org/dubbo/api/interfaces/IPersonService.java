package org.dubbo.api.interfaces;

import java.util.List;

import org.dubbo.api.bean.Person;

public interface IPersonService {
	public Person getPerson(long id);
	/**
	 * 根据名称获取
	 * 
	 * @param name
	 * @return
	 */
	public Person getByNick(String name);
	/**
	 * 测试合并查询
	 * @return
	 */
	public List<Person> getPeron();
}
