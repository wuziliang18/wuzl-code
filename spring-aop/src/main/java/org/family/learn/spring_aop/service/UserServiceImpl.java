package org.family.learn.spring_aop.service;

import org.family.learn.spring_aop.service.bean.UserInfo;
import org.family.learn.spring_aop.service.interfaces.UserService;
import org.springframework.stereotype.Service;
@Service
public class UserServiceImpl implements UserService {

	@Override
	public UserInfo get(int id) {
		System.out.println("获取信息");
		return null;
	}

	@Override
	public void update(UserInfo user) {
		System.out.println("修改信息");

	}

	@Override
	public UserInfo secGet(int id) {
		System.out.println("获取信息sec");
		this.get(id);
		return null;
	}

}
