package org.wuzl.test.concurrent;

public class VolatileExample {
    int a = 0;
    boolean flag = false;

    public void writer() {
        a = 1; // 1
        flag = true;// 2
    }

    public void reader() {
        int i = -1;
        if (flag) { // 3
            i = a;// 4
            if (i != 1) {
                System.out.println("error:i=" + i + ", flag=" + flag);
            }
        }
        // if (i != 1) {
        // System.out.println("error:i=" + i + ", flag=" + flag);
        // }
    }

    public static void main(String[] args) throws InterruptedException {
        int count = 0;
        while (true) {
            if (count++ % 10000 == 0) {
                System.out.println(count);
            }
            VolatileExample obj = new VolatileExample();
            Thread tread1 = new Thread() {
                public void run() {
                    obj.writer();
                };
            };
            tread1.start();
            // Thread.sleep(10);
            Thread thread2 = new Thread() {
                public void run() {
                    obj.reader();
                };
            };
            thread2.start();
            tread1.join();
            thread2.join();

        }
    }
}
