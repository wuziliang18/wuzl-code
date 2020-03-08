package org.wuzl.test.dataStructure;

import java.util.Arrays;

/**
 * 堆
 * 
 * @author ziliang.wu
 *
 */
public class Heap {
    private final boolean bigTop;
    private final int count;
    private final int[] array;
    private int index = 0;

    public Heap(boolean bigTop, int count) {
        super();
        if (count < 0) {
            throw new RuntimeException("count max>0");
        }
        this.bigTop = bigTop;
        this.count = count;
        this.array = new int[count + 1];
    }

    public Heap(int count) {
        this(true, count);
    }

    public boolean insert(int t) {
        if (index >= count) {
            return false;
        }
        int insertIndex = ++index;
        array[insertIndex] = t;
        // 向上递推
        while (insertIndex > 1) {
            int parentIndex = insertIndex / 2;
            if (up(t, parentIndex)) {
                array[insertIndex] = array[parentIndex];
                array[parentIndex] = t;
                insertIndex = parentIndex;
            } else {
                break;
            }
        }
        return true;
    }

    public Integer removeTop() {
        if (index < 1) {
            return null;
        } else {
            int top = array[1];
            // 下移节点
            if (index > 1) {
                // TODO
            }

            return top;
        }
    }

    public int count() {
        return index;
    }

    private boolean up(int t, int parentIndex) {
        if (bigTop) {
            return array[parentIndex] < t;
        } else {
            return array[parentIndex] > t;
        }
    }

    public static void main(String[] args) {
        Heap heap = new Heap(10);
        for (int i = 1; i <= 20; i++) {
            // heap.insert(i)
            System.out.println(heap.insert(i));
        }
        System.out.println(Arrays.toString(heap.array));
        for (int i = 0; i < 15; i++) {
            System.out.println(heap.removeTop());
        }
        System.out.println(Arrays.toString(heap.array));
    }
}
