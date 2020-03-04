package org.wuzl.test.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 排序
 * 
 * @author ziliang.wu
 *
 */
public class TestSort {
    // private static final int[] baseArray = { 81, 94, 11, 96, 12, 35, 17, 95, 28, 58, 41, 75, 15 };
    private static final int[] baseArray = { 81, 94, 11, 96, 12, 35, 17, 95, 28, 58 };

    public static void main(String[] args) {
        System.out.println("输入:" + Arrays.toString(baseArray));
        // outArray(shellSort(baseArray.clone()));
        // List<Integer> list = new ArrayList<>(Arrays.asList(baseArray));
        quickSort(baseArray);
        System.out.println(Arrays.toString(baseArray));

    }

    private static void outArray(int[] array) {
        System.out.println(Arrays.toString(array));
    }

    /**
     * 插入排序
     * 
     * @param array
     * @return
     */
    public static int[] insertionSort(int[] array) {
        int j;
        for (int p = 1; p < array.length; p++) {
            int temp = array[p];
            // 没进循环的话j--失效
            for (j = p; j > 0 && temp < array[j - 1]; j--) {
                array[j] = array[j - 1];
            }
            array[j] = temp;
        }
        return array;
    }

    /**
     * 希尔排序
     * 
     * @param array
     * @return
     */
    public static int[] shellSort(int[] array) {
        int j;
        for (int gap = array.length; gap > 0; gap = gap / 2) {
            for (int i = gap; i < array.length; i++) {
                int temp = array[i];
                for (j = i; j >= gap && temp < array[j - gap]; j = j - gap) {
                    array[j] = array[j - gap];
                }
                array[j] = temp;
            }
        }
        return array;
    }

    /**
     * 有时间再写吧 心乱
     * 
     * @param array
     */
    public static void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private static void quickSort(int[] array, int i, int j) {
        if (i >= j) {
            return;
        }
        if (i + 1 == j) {
            if (array[i] > array[j]) {
                swap(array, i, j);
            }
            return;
        }
        int value = array[i];
        // int tempIndex = i;
        // if (value < array[(j - i) / 2]) {
        // tempIndex = (j - i) / 2;
        // }
        // if (value < array[j]) {
        // tempIndex = j;
        // }
        // if (i != tempIndex) {
        // swap(array, i, tempIndex);
        // }
        int left = i + 1;
        int right = j;
        while (left <= right) {
            while (array[right] > value) {
                right--;
            }
            while (array[left] < value) {
                left++;
            }
            if (left == right) {
                swap(array, left, i);
            } else {
                swap(array, left, right);
            }
        }
        System.out.println(Arrays.toString(array));
        // quickSort(array, i, left);
        // quickSort(array, left, j);
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * 快速排序 这种有问题呀 ???大量的list
     * 
     * @param rows
     */
    public static void quickSortV1(List<Integer> rows) {
        if (rows.size() <= 1) {
            return;
        }
        List<Integer> smaller = new ArrayList<Integer>();
        List<Integer> same = new ArrayList<>();
        List<Integer> lager = new ArrayList<>();
        int choseItem = rows.get(rows.size() / 2);
        for (Integer item : rows) {
            if (item < choseItem) {
                smaller.add(item);
            } else if (item > choseItem) {
                lager.add(item);
            } else {
                same.add(item);
            }
        }
        quickSortV1(smaller);
        quickSortV1(lager);
        rows.clear();
        rows.addAll(smaller);
        rows.addAll(same);
        rows.addAll(lager);
    }
}
