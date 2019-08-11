package org.wuzl.test.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * 使用信号量semaphore 可以来实现线程池
 * @author wuzl
 *
 */
public class TestPoolUseSemaphore<T> {
	private Semaphore available;
	private List<T> items=new ArrayList<T>();
	private boolean[] checkout;
	public TestPoolUseSemaphore(Class<T> clazz,int size) {
		available=new Semaphore(size, true);
		checkout=new boolean[size];
		try {
			for(int i=0;i<size;i++){
				items.add(clazz.newInstance());
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
	}
	public T checkOut() throws InterruptedException{
		available.acquire();//阻塞 直到有资源
		return getItem();
		
	}
	public synchronized T getItem(){
		for(int i=0;i<checkout.length;i++){
			if(!checkout[i]){
				checkout[i]=true;
				return items.get(i);
			}
		}
		return null;
	}
	
	public void checkIn(T t){
		if(releaseItem(t)){
			available.release();
		}
	}
	public synchronized boolean releaseItem(T t){
		int index=items.indexOf(t);
		if(index<0){
			return false;
		}else if(checkout[index]){
			checkout[index]=false;
			return true;
		}
		return false;
	}
}
