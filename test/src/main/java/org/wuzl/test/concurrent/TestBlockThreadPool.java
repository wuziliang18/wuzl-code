package org.wuzl.test.concurrent;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 只允许执行线程执行的线程池 线程数到达上限后阻塞
 * 
 * @author ziliang.wu
 *
 */
public class TestBlockThreadPool {
    public static void main(String[] args) {
        ExecutorService threadPool = new ThreadPoolExecutor(3, 3, 0L, TimeUnit.MILLISECONDS, new SynchronousQueue<>(),
                new RejectedExecutionHandler() {

                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        if (!executor.isShutdown()) {
                            try {
                                System.out.println("block" + r);
                                executor.getQueue().put(r);
                                System.out.println("block end" + r);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
        for (int i = 1; i <= 12; i++) {
            int temp = i;
            threadPool.submit(new Runnable() {

                @Override
                public void run() {
                    System.out.println("run task start:" + temp + "   " + LocalDateTime.now());
                    try {
                        Thread.sleep(2000l);
                    } catch (InterruptedException e) {
                    }
                    System.out.println("run task end:" + temp + "   " + LocalDateTime.now());

                }
            });
            System.out.println("add a task:" + i +"  "+ LocalDateTime.now());
        }
    }
}
