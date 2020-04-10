package org.wuzl.test.algorithm;

import java.util.Arrays;

/**
 * 构建乘积数组 给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，其中 B 中的元素 B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法。
 * 
 * 链接：https://leetcode-cn.com/problems/gou-jian-cheng-ji-shu-zu-lcof 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author ziliang.wu
 *
 */
public class ConstructArr {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new ConstructArr().constructArr(new int[] { 1, 2, 3, 4, 5 })));
    }

    public int[] constructArr(int[] a) {
        if (a == null || a.length <= 1) {
            return a;
        }
        int temp = 1;
        boolean zero = false;
        for (int num : a) {
            if (num == 0) {
                if (zero) {
                    temp = 0;
                    break;
                }
                zero = true;
            } else {
                temp *= num;
            }
        }
        int[] result = new int[a.length];
        if (temp == 0) {
            return result;
        }
        for (int i = 0; i < a.length; i++) {
            int num = a[i];
            if (num == 0) {
                result[i] = temp;
            } else {
                if (!zero) {
                    result[i] = temp / num;
                }
            }
        }
        return result;
    }
}
