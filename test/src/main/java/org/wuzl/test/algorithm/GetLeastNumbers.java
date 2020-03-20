package org.wuzl.test.algorithm;

import java.util.Arrays;

public class GetLeastNumbers {
    public int[] getLeastNumbers(int[] arr, int k) {
        if (arr == null || arr.length == 0 || arr.length <= k) {
            return arr;
        }
        if (k == 0) {
            return new int[0];
        }
        if (k == 1) {
            int first = arr[0];
            for (int i = 1; i < arr.length; i++) {
                if (first > arr[i]) {
                    first = arr[i];
                }
            }
            return new int[] { first };
        }
        Arrays.sort(arr);
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = arr[i];
        }
        return result;
    }
}
