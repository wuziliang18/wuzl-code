package org.wuzl.test.algorithm;

/**
 * 如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为摆动序列。第一个差（如果存在的话）可能是正数或负数。少于两个元素的序列也是摆动序列。
 * 
 * 例如， [1,7,4,9,2,5] 是一个摆动序列，因为差值 (6,-3,5,-7,3) 是正负交替出现的。相反, [1,4,7,2,5] 和 [1,7,4,5,5]
 * 不是摆动序列，第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。
 * 
 * 给定一个整数序列，返回作为摆动序列的最长子序列的长度。 通过从原始序列中删除一些（也可以不删除）元素来获得子序列，剩下的元素保持其原始顺序。
 * 
 * 
 * 
 * 链接：https://leetcode-cn.com/problems/wiggle-subsequence
 * 
 * @author ziliang.wu
 *
 */
public class WiggleMaxLength {
    public static void main(String[] args) {
        WiggleMaxLength obj = new WiggleMaxLength();
//        System.out.println(obj.wiggleMaxLength(new int[] { 1, 1 }));
//        System.out.println(obj.wiggleMaxLength(new int[] { 1, 7, 4, 9, 2, 5 }));
//        System.out.println(obj.wiggleMaxLength(new int[] { 1, 17, 5, 10, 13, 15, 10, 5, 16, 8 }));
//        System.out.println(obj.wiggleMaxLength(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }));
        System.out.println(obj.wiggleMaxLength(new int[] { 33,53,12,64,50,41,45,21,97,35,47,92,39,0,93,55,40,46,69,42,6,95,51,68,72,9,32,84,34,64,6,2,26,98,3,43,30,60,3,68,82,9,97,19,27,98,99,4,30,96,37,9,78,43,64,4,65,30,84,90,87,64,18,50,60,1,40,32,48,50,76,100,57,29,63,53,46,57,93,98,42,80,82,9,41,55,69,84,82,79,30,79,18,97,67,23,52,38,74,15 }));
    }
    //TODO 理解错误
    public int wiggleMaxLength(int[] nums) {
        if (nums == null) {
            return 0;
        }
        if (nums.length <= 1) {
            return nums.length;
        }
        int length = 1;
        int preNum = nums[0];
        int pre = 0;// 1是上升2是下降
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            if (preNum == num) {
                continue;
            }
            if (pre == 0) {// 第一次
                pre = preNum > num ? 2 : 1;
                length++;
                preNum = num;
                continue;
            }
            if (preNum > num) {
                if (pre != 2) {
                    pre = 2;
                    preNum = num;
                    length++;
                }
            } else {
                if (pre != 1) {
                    pre = 1;
                    preNum = num;
                    length++;
                }
            }
        }

        return length;
    }
}
