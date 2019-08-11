package org.wuzl.test.concurrent;
/**
 * <br>
 * 说明:面试题
 * <p/>
 * start() 方法中启动的两个线程T1,T2,请问：
 * 1.	T1，T2 能同时并发访问A.m1() 吗？ 不可以
 * 2.	T1访问A.m1（） 方法，T2访问A.m2() 方法，这样能否并发访问？ 不可以
 * 3.	T1,T2能同时并发访问a.m3()吗？不可以
 * 4.	T1访问a.m3() 方法，T2访问a.m4() 方法，这样能否并发访问？不可以
 * 5.	T1访问a.m3() 方法，T2访问a.m3() 方法，这样能否并发访问？不可以
 */
public class TestSynchronized  {
    public synchronized static void m1() {
    	System.out.println(Thread.currentThread().getName()+"进入了m1");
        System.out.println("m1 is executed");

    }

    public synchronized static void m2() {
    	System.out.println(Thread.currentThread().getName()+"进入了m2");
        System.out.println("m2 is executed");
    }

    public synchronized void m3() {
    	System.out.println(Thread.currentThread().getName()+"进入了m3");
        System.out.println("m3 is executed");
    }

    public synchronized void m4() {
    	System.out.println(Thread.currentThread().getName()+"进入了m4");
        System.out.println("m4 is executed");
    }

    public void start() {
        final TestSynchronized a = new TestSynchronized();
        final TestSynchronized a2 = new TestSynchronized();

        new Thread(new Runnable() {
            //            @Override
            public void run() {
            	System.out.println("T1启动");
            	TestSynchronized.m1();
                a.m3();

            }
        }, "T1").start();

        new Thread(new Runnable() {
            //            @Override
            public void run() {
            	System.out.println("T2启动");
            	TestSynchronized.m1();
            	TestSynchronized.m2();
                a.m3();
                a.m4();
                a2.m3();
            }
        }, "T2").start();

    }

    public static void main(String[] args) {
        new TestSynchronized().start();
    }

}

