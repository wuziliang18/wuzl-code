package org.wuzl.test.base.impl;

import org.wuzl.test.base.IWorkService;

public class WorkServiceImpl implements IWorkService {

	@Override
	public void work(String content) {
		System.out.println("工作内容："+content);

	}

	@Override
	public int getStatus() {
		return 0;
	}
	@Override
	public String toString() {
		return "toString";
	}
}
