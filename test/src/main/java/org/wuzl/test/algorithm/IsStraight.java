package org.wuzl.test.algorithm;

import java.util.Arrays;

/**
 * 扑克牌中的顺子
 * 
 * 从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。
 * 
 * 链接：https://leetcode-cn.com/problems/bu-ke-pai-zhong-de-shun-zi-lcof
 * 
 * @author ziliang.wu
 *
 */
public class IsStraight {
    public static void main(String[] args) {
        System.out.println(new IsStraight().isStraight(new int[] { 0, 0, 1, 2, 5 }));
    }

    public boolean isStraight(int[] nums) {
        if (nums == null || nums.length != 5) {
            return false;
        }
        Arrays.sort(nums);
        int zeroCount = 0;
        int preNum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeroCount++;
                continue;
            }
            int num = nums[i];
            if (preNum == 0) {
                preNum = num;
                continue;
            }
            int wantNum = preNum + 1;
            if (num == preNum || (num > wantNum && num > wantNum + zeroCount--)) {
                return false;
            }
            preNum = num;

        }
        return true;
    }
}
