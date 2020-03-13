package org.wuzl.test.algorithm;

/**
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。  
 * 
 * 链接：https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof
 * 
 * @author ziliang.wu
 *
 */
public class MinArray {
    public static void main(String[] args) {
        MinArray obj = new MinArray();
        System.out.println(obj.minArray(new int[] { 3, 4, 5, 1, 2 }));
        System.out.println(obj.minArray(new int[] { 2, 2, 2, 0, 1 }));
        System.out.println(obj.minArray(new int[] { 1, 2, 3, 4, 5 }));
        System.out.println(obj.minArray(new int[] { 6, 1, 2, 3, 4, 5 }));
    }

    public int minArray(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return 0;
        }
        int i = 0, j = numbers.length - 1;
        while (i < j) {
            int m = (i + j) / 2;
            if (numbers[m] > numbers[j])
                i = m + 1;
            else if (numbers[m] < numbers[j])
                j = m;
            else
                j--;

        }
        return numbers[i];
    }

    public int minArrayV1(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return 0;
        }
        for (int i = 0; i < numbers.length; i++) {
            if (i == numbers.length - 1) {
                return numbers[0];
            }
            if (numbers[i] > numbers[i + 1]) {
                return numbers[i + 1];
            }
        }
        return 0;
    }
}
