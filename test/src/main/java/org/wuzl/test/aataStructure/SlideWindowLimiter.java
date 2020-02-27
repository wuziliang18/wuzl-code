package org.wuzl.test.aataStructure;

import java.util.Random;

public class SlideWindowLimiter {
    private final int permitsPerSecond;
    private final CircleQueue<Long> circleQueue;

    public SlideWindowLimiter(int permitsPerSecond) {
        this.permitsPerSecond = permitsPerSecond;
        circleQueue = new CircleQueue<Long>(permitsPerSecond);
    }

    public boolean check() {
        long time = System.currentTimeMillis();
        if (circleQueue.canAdd()) {
            circleQueue.add(time);
            return true;
        }
        while (true) {
            long last = circleQueue.peek();
            if ((time - last) > 1000) {
                circleQueue.poll();
            } else {
                break;
            }
        }
        if (circleQueue.canAdd()) {
            circleQueue.add(time);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Random random = new Random();
        SlideWindowLimiter limiter = new SlideWindowLimiter(6);
        for (int i = 0; i < 6666; i++) {
            try {
                Thread.sleep(random.nextInt(150));
            } catch (InterruptedException e) {
            }
            long time = System.currentTimeMillis() % 1582783700000l;
            System.out.println("now time is:" + time + ",result" + limiter.check());
        }
    }
}
