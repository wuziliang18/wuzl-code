package org.wuzl.test.algorithm;

/**
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * 
 * 
 * @author ziliang.wu
 *
 */
public class IsPalindrome {
    public static void main(String[] args) {
        IsPalindrome obj = new IsPalindrome();
        System.out.println(obj.isPalindrome(121));
        System.out.println(obj.isPalindrome(122));
        System.out.println(obj.isPalindrome(1221));
        System.out.println(obj.isPalindrome(1231));
    }

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        String s = String.valueOf(x);
        if (s.length() == 1) {
            return true;
        }
        int left = 0;
        int right = s.length() - 1;
        while (left <= right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
