package org.wuzl.test.algorithm;

/**
 * 背包问题
 * 
 * @author ziliang.wu
 *
 */
public class Package {
    public static void main(String[] args) {
        Package obj = new Package();
        System.out.println(obj.getMaxPackage(16, new int[] { 2, 2, 4, 6, 3 }));
        System.out.println(obj.getMaxPackage(7, new int[] { 1, 2, 3, 5, 4 }));
        System.out.println(obj.getMaxPackage(15, new int[] { 1, 2, 8, 7, 37 }));
    }

    public int getMaxPackage(int maxWeight, int[] weightArray) {
        if (weightArray == null || weightArray.length == 0 || maxWeight <= 0) {
            return 0;
        }
        boolean[] array = new boolean[maxWeight + 1];
        array[0] = true;
        for (int i = 0; i < weightArray.length; i++) {
            int weight = weightArray[i];
            if (weight <= maxWeight) {
                for (int j = maxWeight - weight; j >= 0; j--) {
                    if (array[j] && weight + j <= maxWeight) {
                        array[weight + j] = true;
                    }
                }
            }
        }
        for (int i = maxWeight; i >= 0; i--) {
            if (array[i]) {
                return i;
            }
        }
        return 0;
    }
}
