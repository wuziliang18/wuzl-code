package org.wuzl.test.reference;

import java.util.concurrent.Executors;

/**
 * 线程池没有被持有 会报引用错误 JIT优化
 * 
 * @author ziliang.wu
 *
 */
public class SingleThreadPoolTest {
    public static void main(String[] args) {
        for (int i = 0; i < 2000; i++) {
            newSingleThreadPool();
        }
    }

    private static void newSingleThreadPool() {
        Executors.newSingleThreadExecutor().submit(new Runnable() {
            @Override
            public void run() {
                byte[] bytes = new byte[1024 * 1024 * 4];
                System.out.println(Thread.currentThread().getName());
            }
        });
    }
}
