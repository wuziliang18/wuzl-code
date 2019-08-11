package org.wuzl.test.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

public class SimpleThread implements Runnable{
	public static AtomicInteger id=new AtomicInteger();
	private int currentId;
	private int count=20;
	public SimpleThread() {
		currentId=id.getAndIncrement();
	}
	@Override
	public void run() {
		while(count-->=0){
			System.out.println("id["+currentId+"] out:"+count+"msg");
		}
		System.out.println("id["+currentId+"] stop");
	}

}
