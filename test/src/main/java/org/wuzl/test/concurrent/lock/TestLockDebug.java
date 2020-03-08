package org.wuzl.test.concurrent.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestLockDebug {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        new Thread("test1") {
            @Override
            public void run() {
                System.out.println("lock1");
                lock.lock();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println("lock end 1");
            }
        }.start();
        new Thread("test2") {
            @Override
            public void run() {
                System.out.println("lock2");
                lock.lock();
                System.out.println("lock end 2");
            }
        }.start();
        new Thread("test3") {
            @Override
            public void run() {
                System.out.println("lock3");
                lock.lock();
                System.out.println("lock end 3");
            }
        }.start();
    }
}
