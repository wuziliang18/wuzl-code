package org.wuzl.test.design_mode.filter;

import org.wuzl.test.design_mode.IWork;

public class WorkFilter2 implements WorkFilter {

	@Override
	public String work(IWork work,String content) {
		System.out.println("拦截工作内容能过是:"+content);
		if(content.contains("陪上街")){
			return "有点累";
		}
		System.out.println(this+"拦截通过");
		return work.work(content);
	}

}
