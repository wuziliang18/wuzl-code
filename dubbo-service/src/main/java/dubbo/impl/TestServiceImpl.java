package dubbo.impl;

import dubbo.TestService;

public class TestServiceImpl implements TestService {

	public String sayHello(String name) {
		return name+"是个好人";
	}

}
