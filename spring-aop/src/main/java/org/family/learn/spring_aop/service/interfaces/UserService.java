package org.family.learn.spring_aop.service.interfaces;

import org.family.learn.spring_aop.service.bean.UserInfo;

public interface UserService {
	public UserInfo get(int id);

	public void update(UserInfo user);
	
	public UserInfo secGet(int id);
}
