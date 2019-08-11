package org.wuzl.test.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedBuffer {
	final Lock lock=new ReentrantLock();
	final int max=100;
	int putCount,getCount,count;
	final Condition noFull=lock.newCondition();
	final Condition noEmpty=lock.newCondition();
	final String[] array=new String[max];
	public void put(String in){
		try {
			lock.lock();
			while(max==count){
				System.out.println("等待少点的");
				noFull.await();//如果满的等待
			}
			array[count++]=in;
			System.out.println("放入了"+in);
			noEmpty.signal();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	public String get(){
		String result="";
		try {
			lock.lock();
			while(0==count){
				System.out.println("等待有的");
				noEmpty.await();//如果空的等待
			}
			result=array[count---1];
			System.out.println("获取了"+result);
			noFull.signal();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
			return result;
		}
	}
}
