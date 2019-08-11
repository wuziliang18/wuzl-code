package org.wuzl.test.concurrent;

public class TestSynchronizedObject {
	public void test(int i){
		System.out.println("进入方法"+i);
		synchronized (new Object()) {
			try {
				Thread.sleep(5000l);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("完事"+i);
		}
	}
	public static void main(String[] args) {
		final TestSynchronizedObject test=new TestSynchronizedObject();
		new Thread(){
			public void run() {
				test.test(1);
			};
		}.start();;
		new Thread(){
			public void run() {
				test.test(2);
			};
		}.start();;
	}
}
