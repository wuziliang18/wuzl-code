package org.wuzl.test.design_mode.filter;

import org.wuzl.test.design_mode.IWork;

public interface WorkFilter {
	public String work(IWork work,String content);//对work进行拦截
}
