package org.wuzl.test.aataStructure;

import java.util.Arrays;

/**
 * 循环队列 利用数组来实现
 * 
 * @author ziliang.wu
 *
 */
public class CircleQueue<T> {
    private final int size;
    private final Object[] array;
    private int head;
    private int tail;

    public CircleQueue(int size) {
        this.size = size + 1;
        array = new Object[this.size];
    }

    public boolean add(T obj) {
        int newHead = (head + 1) % size;
        if (newHead == tail) {
            return false;
        }
        array[newHead] = obj;
        head = newHead;
        return true;
    }

    @SuppressWarnings("unchecked")
    public T poll() {
        if (head == tail) {
            return null;
        }
        tail = (tail + 1) % size;
        return (T) array[tail];
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if (head == tail) {
            return null;
        }
        return (T) array[(tail + 1) % size];
    }

    public boolean canAdd() {
        return (head + 1) % size != tail;
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }

    public static void main(String[] args) {
        final CircleQueue<Integer> queue = new CircleQueue<>(6);
        int count = 0;
        poll(queue, 10);
        add(queue, 10, count);
        poll(queue, 10);
        count += 10;
        add(queue, 10, count);
        poll(queue, 10);
        count += 10;
        add(queue, 3, count += 5);
        poll(queue, 6);
        // AtomicInteger id = new AtomicInteger();
        // Executor executor1 = Executors.newFixedThreadPool(3);
        // for (int i = 0; i < 100000; i++) {
        // // if (id.get() % 10000 == 0) {
        // // System.out.println("now add count" + id.get());
        // // try {
        // // Thread.sleep(1000l);
        // // } catch (InterruptedException e) {
        // // e.printStackTrace();
        // // }
        // // }
        // // int addId = id.addAndGet(1);
        //
        // int addId = i;
        // executor1.execute(new Runnable() {
        //
        // @Override
        // public void run() {
        // while (true) {
        // boolean result = queue.add(addId);
        // if (result) {
        // return;
        // }
        // }
        //
        // }
        // });
        // }
        // AtomicInteger count = new AtomicInteger();
        // Executor executor2 = Executors.newFixedThreadPool(6);
        // while (true) {
        // executor2.execute(new Runnable() {
        //
        // @Override
        // public void run() {
        // Integer result = queue.poll();
        // if (result != null) {
        // count.incrementAndGet();
        // } else {
        // System.out.println("now poll count:" + count.get());
        // }
        // }
        // });
        // }
    }

    private static void add(CircleQueue<Integer> queue, int size, int value) {
        for (int i = 1; i <= size; i++) {
            System.out.println("add " + (value + i) + " " + String.valueOf(queue.add(value + i)));
        }
        System.out.println(queue);
    }

    private static void poll(CircleQueue<Integer> queue, int size) {
        for (int i = 0; i < size; i++) {
            System.out.println("poll " + i + " " + String.valueOf(queue.poll()));
        }
        System.out.println(queue);
    }
}
