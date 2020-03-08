package org.wuzl.test.concurrent;

import java.util.concurrent.locks.LockSupport;

public class TestLockSupportPrint {
    private static Thread thread1 = null, thread2 = null;

    public static void main(String[] args) throws InterruptedException {
        // new Thread("testThread1") {
        // @Override
        // public void run() {
        // LockSupport.park();
        // }
        // }.start();
        // new Thread("testThread2") {
        // @Override
        // public void run() {
        // LockSupport.park(new Object());
        // }
        // }.start();
        thread1 = new Thread("testThread1") {
            @Override
            public void run() {
                for (int i = 1; i < 100; i++) {
                    if (i % 2 == 1) {
                        System.out.println(i);
                        LockSupport.unpark(thread2);
                        LockSupport.park();
                    }
                }
            }
        };
        thread2 = new Thread("testThread2") {
            @Override
            public void run() {
                for (int i = 1; i < 102; i++) {
                    if (i % 2 == 0) {
                        LockSupport.park();
                        System.out.println(i);
                        LockSupport.unpark(thread1);
                    }
                }
                LockSupport.unpark(thread1);
            }
        };

        thread2.start();
        thread1.start();
        thread2.join();
        thread1.join();
    }
}
