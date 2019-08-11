package org.wuzl.test.design_mode.filter;

import java.util.ArrayList;
import java.util.List;

import org.wuzl.test.design_mode.IWork;
import org.wuzl.test.design_mode.Work;

public class TestMain {
	public static void main(String[] args) {
		IWork work=new Work();
		work.begin();
		work.work("陪睡觉");//直接运行
		work.work("陪上街");//直接运行
		work.end();
		//加入拦截连
		System.out.println("##拦截以后");
		work=buildInvokerChain(work);
		work.begin();
		System.out.println("工作答复是"+work.work("陪睡觉"));//直接运行
		System.out.println("工作答复是"+work.work("陪上街"));//直接运行
		work.end();
	}
	/**
	 * 重要的构造拦截链
	 * @param work
	 * @return
	 */
	private static IWork buildInvokerChain(final IWork work){
		IWork last=work;
		List<WorkFilter> filters=new ArrayList();//此处是模拟
		filters.add(new WorkFilter1());
		filters.add(new WorkFilter2());
		for(final WorkFilter filter:filters){
			final IWork next=last;
			last=new IWork() {
				
				@Override
				public String work(String content) {
					return filter.work(next, content);
				}
				
				@Override
				public String getName() {
					return work.getName();
				}
				
				@Override
				public void end() {
					work.end();
				}
				
				@Override
				public void begin() {
					work.begin();
				}
			};
		}
		return last;
	}
}
