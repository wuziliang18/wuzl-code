package org.wuzl.test.algorithm;

import org.wuzl.test.comm.ListNode;
import org.wuzl.test.comm.ListNodeUtil;

/**
 * 回文链表
 * 
 * 请判断一个链表是否为回文链表。
 * 
 * 示例 1:
 * 
 * 输入: 1->2 输出: false 示例 2:
 * 
 * 输入: 1->2->2->1 输出: true 进阶： 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 * 
 * 链接：https://leetcode-cn.com/problems/palindrome-linked-list
 * 
 * @author ziliang.wu
 *
 */
public class IsPalindromeV2 {
    public static void main(String[] args) {
        IsPalindromeV2 obj = new IsPalindromeV2();
        obj.isPalindrome(ListNodeUtil.getListNode(new Integer[] { 1, 2, 3, 3, 2, 1 }));
        obj.isPalindrome(ListNodeUtil.getListNode(new Integer[] { 1, 2, 3, 4, 3, 2, 1 }));
        // System.out.println(obj.isPalindrome(ListNodeUtil.getListNode(1)));
        // System.out.println(obj.isPalindrome(ListNodeUtil.getListNode(new Integer[] { 1, 2, 1 })));
        // System.out.println(obj.isPalindrome(ListNodeUtil.getListNode(new Integer[] { 1, 2, 2, 1 })));
        // System.out.println(obj.isPalindrome(ListNodeUtil.getListNode(new Integer[] { 1, 2, 3, 1 })));

    }

    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        // 找到中间节点
        ListNode slow = head;
        ListNode fast = head;
        boolean evenNum = false;
        while (slow.hasNext() && fast.hasNext()) {
            fast = fast.next;
            if (fast.hasNext()) {
                fast = fast.next;
            } else {
                evenNum = true;
            }
            slow = slow.next;

        }
        System.out.println(slow);
        ListNode flip = head;
        while (flip.hasNext()) {
            ListNode temp = flip.next;
            flip.next = temp.next;
            temp.next = head;
            head = temp;
            if (head.next == slow) {
                break;
            }
        }
//        head = head.next;
        System.out.println(head);
        System.out.println(slow);

        return false;
    }
}
