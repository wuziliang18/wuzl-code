package org.wuzl.test.concurrent;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 线程有4种阻塞： 1sleep 2wait 3io 解决方法直接关闭io 4synchronized等待获取锁 解决方法使用lock替代 测试哪种可以中断 只有sleep和wart
 * 有抛出InterruptedException异常的才可以被代码中断
 * 
 * @author wuzl
 *
 */
public class TestThreadInterrupt {
    static ExecutorService executor = Executors.newCachedThreadPool();

    public static void test(Runnable runnable) {
        Future<?> future = executor.submit(runnable);
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("开始中断" + runnable.getClass());
        future.cancel(true);
        System.out.println("发送中断请求完毕" + runnable.getClass());
    }

    public static void main(String[] args) {
        SleepThread thread = new SleepThread();
        thread.run();
        thread.run();//runable可以执行两次没有意义 thread的start不可以
        test(new SleepThread());
        test(new IoThread());
        test(new SynchronizedThread());
        // executor.shutdownNow();
    }
}

class SleepThread implements Runnable {

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            System.out.println("SleepThread被中断");
        }
        System.out.println("SleepThread线程结束");
    }
}

class IoThread implements Runnable {
    @Override
    public void run() {
        System.out.println("等待输入");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("IoThread线程结束");
    }

}

class SynchronizedThread implements Runnable {
    public SynchronizedThread() {// 先获取锁让其他的去等待
        new Thread() {
            public void run() {
                f();
            };
        }.start();
    }

    public synchronized void f() {// 拿住锁 死活不放了
        while (true) {
            Thread.yield();
        }
    }

    @Override
    public void run() {
        System.out.println("尝试获取锁");
        f();
        System.out.println("SynchronizedThread结束");
    }

}
