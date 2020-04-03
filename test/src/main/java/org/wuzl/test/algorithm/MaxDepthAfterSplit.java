package org.wuzl.test.algorithm;

/**
 * 有效括号的嵌套深度
 * 
 * 
 * 给你一个有效括号字符串 seq，将其分成两个不相交的子序列 A 和 B，且 A 和 B 满足有效括号字符串的定义（注意：A.length + B.length = seq.length）。
 * 
 * 现在，你需要从中选出 任意 一组有效括号字符串 A 和 B，使 max(depth(A), depth(B)) 的可能取值最小。
 * 
 * 返回长度为 seq.length 答案数组 answer ，选择 A 还是 B 的编码规则是：如果 seq[i] 是 A 的一部分，那么 answer[i] = 0。否则，answer[i] =
 * 1。即便有多个满足要求的答案存在，你也只需返回 一个。
 * 
 * 链接：https://leetcode-cn.com/problems/maximum-nesting-depth-of-two-valid-parentheses-strings
 * 
 * @author ziliang.wu
 *
 */
public class MaxDepthAfterSplit {
    public int[] maxDepthAfterSplit(String seq) {
        if (seq == null || seq.isEmpty()) {
            return new int[0];
        }
        int[] array = new int[seq.length()];
        for (int i = 0; i < seq.length(); i++) {
            array[i] = seq.charAt(i) == '(' ? i & 1 : ((i + 1) & 1);
        }
        return array;
    }
}
