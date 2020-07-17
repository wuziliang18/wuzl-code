package org.wuzl.test.obj;

public class SafepointTest {
    static double sum = 0;

    public static void foo() {
        long start=System.currentTimeMillis();
        for (int i = 0; i < 0x77777777; i++) {
            sum += Math.sqrt(i);
        }
        System.out.println("foo"+(System.currentTimeMillis()-start));
    }

    public static void bar() {
        long start=System.currentTimeMillis();
        for (int i = 0; i < 50_000_000; i++) {
            new Object().hashCode();
        }
        System.out.println("bar"+(System.currentTimeMillis()-start));
    }

    public static void main(String[] args) {
        //一起跑9529
        new Thread(SafepointTest::foo).start();//9055
        new Thread(SafepointTest::bar).start();//2291
    }
}
