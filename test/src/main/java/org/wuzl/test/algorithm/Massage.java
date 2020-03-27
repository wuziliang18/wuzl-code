package org.wuzl.test.algorithm;

/**
 * 按摩师
 * 
 * 一个有名的按摩师会收到源源不断的预约请求，每个预约都可以选择接或不接。在每次预约服务之间要有休息时间，因此她不能接受相邻的预约。给定一个预约请求序列，替按摩师找到最优的预约集合（总预约时间最长），返回总的分钟数。
 * 
 * 链接：https://leetcode-cn.com/problems/the-masseuse-lcci
 * 
 * @author ziliang.wu
 *
 */
public class Massage {
    public static void main(String[] args) {
        Massage obj = new Massage();
        System.out.println(obj.massage(new int[] { 1, 2, 3, 1 }));
        System.out.println(obj.massage(new int[] { 2, 7, 9, 3, 1 }));
        System.out.println(obj.massage(new int[] { 2, 1, 4, 5, 3, 1, 1, 3 }));
    }

    public int massage(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        int[][] array = new int[length][2];
        array[0][0] = nums[0];
        array[0][1] = 0;
        for (int i = 1; i < length; i++) {
            array[i][0] = array[i - 1][1] + nums[i];
            array[i][1] = Math.max(array[i - 1][0], array[i - 1][1]);
        }
        return Math.max(array[length - 1][0], array[length - 1][1]);
    }
}
