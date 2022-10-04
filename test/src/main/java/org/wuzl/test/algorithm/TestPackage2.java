package org.wuzl.test.algorithm;

import java.util.Arrays;

/**
 * 背包动态规划 加入价值 求最大价值
 * 
 * @author ziliang.wu
 *
 */
public class TestPackage2 {
    // private static int[] items = { 2, 2, 4, 6, 3 }; // 物品的重量
    // private static int[] values = { 3, 4, 8, 9, 6 }; // 物品的价值
    private static int[] items = { 2, 4, 6, 3, 2 }; // 物品的重量
    private static int[] values = { 3, 8, 9, 6, 4 }; // 物品的价值
    private static int maxWeight = 9;

    public static void main(String[] args) {
        System.out.println(getMaxValue());
    }

    public static int getMaxValue() {
        if (items == null || items.length == 0 || values == null || items.length != values.length || maxWeight <= 0) {
            return 0;
        }
        int[] array = new int[maxWeight + 1];
        for (int i = 0; i < items.length; i++) {
            for (int j = maxWeight - items[i]; j >= 0; j--) {
                if (array[j] != 0) {
                    array[j + items[i]] = Math.max(array[j] + values[i], array[j + items[i]]);
                }
            }
            array[items[i]] = Math.max(array[items[i]], values[i]);
        }
        int maxValue = 0;
        for (int i = maxWeight; i >= 0; i--) {
            maxValue = Math.max(maxValue, array[i]);
        }
        return maxValue;
    }

    // error
    public static int getMaxValueV1() {
        int size = items.length;
        int maxValueArray[] = new int[maxWeight + 1];
        for (int i = 0; i < size; i++) {
            int weight = items[i];
            int value = values[i];
            for (int j = maxWeight; j >= weight; j--) {
                int temp = value + maxValueArray[j - weight];
                if (maxValueArray[j] < temp) {
                    maxValueArray[j] = temp;
                }
            }
            System.out.println(Arrays.toString(maxValueArray));
        }
        // 返回值
        for (int i = maxValueArray.length - 1; i >= 0; i--) {
            if (maxValueArray[i] > 0) {
                return maxValueArray[i];
            }
        }
        return 0;
    }

}
