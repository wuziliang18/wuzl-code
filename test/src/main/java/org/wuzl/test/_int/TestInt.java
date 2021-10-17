package org.wuzl.test._int;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

public class TestInt {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(1000l*60*60*10);
        long l=123132l;
        int iii=(int)l;
        System.out.println(iii);
        BigDecimal big = new BigDecimal(20);
        System.out.println(big);
        System.out.println(big.doubleValue());
        int COUNT_BITS = Integer.SIZE - 3;
        int CAPACITY = (1 << COUNT_BITS) - 1;
        int RUNNING = -1 << COUNT_BITS;
        System.out.println(COUNT_BITS);
        System.out.println(CAPACITY);
        System.out.println(RUNNING);
        System.out.println(Integer.compare(1, -1));
        System.out.println(Integer.compareUnsigned(1, -1));
        // System.out.println(Integer.parseUnsignedInt("-22"));
        System.out.println(642489 / 1069);
        System.out.println("54:" + (5169919 - 5134917));
        System.out.println("55:" + (7324818 - 7284817));
        System.out.println(2 << 7);
        boolean success = false;
        int count = 0;
        while (!success) {
            try {
                System.out.println(">>>");
                if (count++ < 5) {
                    throw new RuntimeException("1111");
                }
                System.out.println("正常运行");
                success = true;
            } catch (Exception e) {
                System.out.println("出错" + e.getMessage());
                try {
                    TimeUnit.SECONDS.sleep(1l);
                } catch (InterruptedException e1) {
                }
            }
        }
        int i = 12;
        System.out.println();
        int result = Integer.parseInt("c", 16);
        System.out.println(result);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(35.90 + 34.90 + 9.90 + 1.5 + 1.98 + 16.90 - 5);
        System.out.println(99 / 24);
        System.out.println(0.99 * 10 + 9.9 * 2);

        int a = 10, b = 4, c = 20, d = 6;
        int e = (a++ * b + c * --d);
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
        System.out.println(e);

        Integer i1 = 1;
        Integer i2 = 1;
        System.out.println(i1 == i2);
        Integer i3 = new Integer(3);
        System.out.println(i1 == i3);// false
        Long l1 = 1l;
        Long l2 = 1l;
        System.out.println(l1 == l2);
    }
}
