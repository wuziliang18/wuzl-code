package org.wuzl.test.concurrent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadSnUtil implements Runnable {
	 
    private final static String str62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final AtomicInteger ATOM_INT = new AtomicInteger(0);
    //不用线程安全 会少
    public static final Set<Object> hash = Collections.synchronizedSet(new HashSet<>(2000));
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            hash.add(create15());
        }
    }
 
    /**
     * 创建一个15位的字符串，一毫秒内不超过1600000个内不重复：36*36*36*36。
     * @return
     */
    public final static String create15() {
        StringBuilder sb = new StringBuilder(15);
        sb.append(Long.toHexString(System.currentTimeMillis()));
        String str = longTo36(ATOM_INT.incrementAndGet());
        if (str.length() == 1) {
            sb.append("000").append(str);
        } else if (str.length() == 2) {
            sb.append("00").append(str);
        } else if (str.length() == 3) {
            sb.append("0").append(str);
        } else {
            sb.append(str);
        }
        return sb.toString();
    }
 
    /**
     * long型10进制转换成36进制的字符串形式
     * @param num
     * @return
     */
    public static final String longTo36(long num) {
        return ten2Any(num, 36);
    }
 
    /**
     * 10进制转任意进制
     * @param num Long型值
     * @param base 转换的进制
     * @return 任意进制的字符形式
     */
    private static final String ten2Any(long num, int base) {
        StringBuilder sb = new StringBuilder(7);
        while (num != 0) {
            sb.append(str62.charAt((int) (num % base)));
            num /= base;
        }
        return sb.reverse().toString();
    }
 
    public static void main(String[] args) throws InterruptedException {
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Thread t = new Thread(new ThreadSnUtil());
            t.start();
            list.add(t);
        }
        for (Thread thread : list) {
            thread.join();
        }
        System.out.println("总和：" + ThreadSnUtil.hash.size());
    }
}