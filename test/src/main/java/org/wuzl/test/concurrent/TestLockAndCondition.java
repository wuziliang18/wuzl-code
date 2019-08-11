package org.wuzl.test.concurrent;

import java.util.Random;

public class TestLockAndCondition {
	public static void main(String[] args) {
		BoundedBuffer bound=new BoundedBuffer();
		new TestInThread(bound).start();
		new TestOutThread(bound).start();
	}
}
class TestInThread extends Thread{
	int i=0;
	BoundedBuffer bound;
	TestInThread(BoundedBuffer bound){
		this.bound=bound;
	}
	@Override
	public void run() {
		while(true){
			bound.put(i+++"");
			try {
				Thread.sleep(new Random().nextInt(1000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
class TestOutThread extends Thread{
	BoundedBuffer bound;
	TestOutThread(BoundedBuffer bound){
		this.bound=bound;
	}
	@Override
	public void run() {
		while(true){
			bound.get();
			try {
				Thread.sleep(new Random().nextInt(1000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}