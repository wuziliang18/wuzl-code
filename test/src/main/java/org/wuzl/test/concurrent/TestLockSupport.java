package org.wuzl.test.concurrent;

import java.util.concurrent.locks.LockSupport;

public class TestLockSupport {
    public static void main(String[] args) {
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
        Thread thread1 = new Thread("testThread1") {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println("我要睡觉 ");
                LockSupport.park();
                System.out.println("谁把我叫醒了");
            }
        };
        thread1.start();
        new Thread("testThread2") {
            @Override
            public void run() {
                System.out.println("看他要睡着了 先叫醒他 ");
                LockSupport.unpark(thread1);
                System.out.println("别睡了起来嗨");
            }
        }.start();

    }
}
