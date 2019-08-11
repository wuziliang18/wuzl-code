package org.wuzl.test.concurrent;

public class VolatileTest {
    private int i = 0;
    private int j = 0;
    public long exceptionCount = 0;
 
    //线程1调用这个方法
    public void f1() {
        for(int k = 0; k < Integer.MAX_VALUE; k++) {
            i = k;
            j = i;
        }
    }
 
    //线程2调用这个方法
    public void f2() {
        while (true) {
            //如果线程1对i,j的修改都是可见的，就不会出现j>i的情况了，一旦出现这种情况就能说明线程1对i,j的修改不可见
            if(j > i) {
                exceptionCount++;
            }
        }
    }
 
    public static void main(String[] args) {
        final VolatileTest volatileTest = new VolatileTest();
        //线程1
        new Thread(new Runnable() {
            @Override
            public void run() {
                volatileTest.f1();
            }
        }).start();
        //线程2
        new Thread(new Runnable() {
            @Override
            public void run() {
                volatileTest.f2();
            }
        }).start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
 
        }
        System.out.println("exceptionCount:" + volatileTest.exceptionCount);
    }
}