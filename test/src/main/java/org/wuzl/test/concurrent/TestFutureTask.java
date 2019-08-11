package org.wuzl.test.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TestFutureTask {
	public <T> Future<T> asyncCall(Callable<T> callable) {
		try {
	    	try {
				final T o = callable.call();//直接获取了结果
				//local调用会直接返回结果.
				if (o != null) {
					FutureTask<T> f = new FutureTask<T>(new Callable<T>() {
						public T call() throws Exception {
							return o;
						}
					});
					f.run();
					return f;
				} else {
					
				}
			} catch (Exception e) {
				throw new Exception(e);
			} finally {
				
			}
		} catch (final Exception e) {
			return new Future<T>() {
				public boolean cancel(boolean mayInterruptIfRunning) {
					return false;
				}
				public boolean isCancelled() {
					return false;
				}
				public boolean isDone() {
					return true;
				}
				public T get() throws InterruptedException, ExecutionException {
					throw new ExecutionException(e.getCause());
				}
				public T get(long timeout, TimeUnit unit)
						throws InterruptedException, ExecutionException,
						TimeoutException {
					return get();
				}
			};
		}
		return null;
	}
	public static void main(String[] args) throws Exception {
		TestFutureTask test=new TestFutureTask();
		Future<Map> result=test.asyncCall(new Callable1());
		System.out.println("reqeust end");
		System.out.println("获得结果："+result.get());
	}
}

class Callable1 implements Callable<Map>{

	@Override
	public Map call() throws Exception {
		Map dto=new HashMap();
		dto.put("test", "success");
		try {
			Thread.sleep(2000l);//模拟运行时间
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dto;
	}
	
}
