package org.wuzl.test.guava;

import com.google.common.util.concurrent.RateLimiter;

public class TestRateLimiter {
    public static void main(String[] args) {
        // 每秒产生10个令牌
        RateLimiter rateLimiter = RateLimiter.create(10);
        // try {
        // Thread.sleep(2000l);// 如果令牌桶满的 会在请求一秒内处理 *2
        // } catch (InterruptedException e) {
        // }
        // long start = System.currentTimeMillis();
        // for (int i = 0; i < 20; i++) {
        // System.out.println(i + " " + System.currentTimeMillis() + " 等待时间 : " + rateLimiter.acquire(1));
        // }
        // System.out.println(System.currentTimeMillis() - start);
        System.out.println(System.currentTimeMillis() + " 等待时间 : " + rateLimiter.acquire(1));
        System.out.println(System.currentTimeMillis() + " 等待时间 : " + rateLimiter.acquire(1));
        // 预消费 自己等待时间正常 后来的人等待时间长
        System.out.println(System.currentTimeMillis() + " 等待时间 : " + rateLimiter.acquire(5));
        System.out.println(System.currentTimeMillis() + " 等待时间 : " + rateLimiter.acquire(1));

    }
}
