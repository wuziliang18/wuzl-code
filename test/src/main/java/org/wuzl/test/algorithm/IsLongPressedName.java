package org.wuzl.test.algorithm;

/**
 * 长按键入
 * 
 * 你的朋友正在使用键盘输入他的名字 name。偶尔，在键入字符 c 时，按键可能会被长按，而字符可能被输入 1 次或多次。
 * 
 * 你将会检查键盘输入的字符 typed。如果它对应的可能是你的朋友的名字（其中一些字符可能被长按），那么就返回 True。
 * 
 *  
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/long-pressed-name 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author ziliang.wu
 *
 */
public class IsLongPressedName {
    public static void main(String[] args) {
        IsLongPressedName obj = new IsLongPressedName();
        System.out.println(obj.isLongPressedName("a", "ab"));
        System.out.println(obj.isLongPressedName("abc", "ab"));
        System.out.println(obj.isLongPressedName("alex", "aaleex"));
        System.out.println(obj.isLongPressedName("saeed", "ssaaedd"));
        System.out.println(obj.isLongPressedName("leelee", "lleeelee"));
        System.out.println(obj.isLongPressedName("laiden", "laiden"));
        System.out.println(obj.isLongPressedName("kikcxmvzi", "kiikcxxmmvvzz"));
    }

    public boolean isLongPressedName(String name, String typed) {
        if (name == null || name.equals("")) {
            return typed == null || typed.equals("");
        }
        if (typed == null || typed.equals("")) {
            return false;
        }
        int index = 0;
        boolean result = false;
        for (int i = 0; i < name.length(); i++) {
            if (index >= typed.length()) {
                return false;
            }
            result = false;
            char c = name.charAt(i);
            while (index < typed.length()) {
                char check = typed.charAt(index);
                index++;
                if (c == check) {
                    result = true;
                    break;
                }
                // 校验上一字符串
                if (i != 0 && name.charAt(i - 1) == check) {
                    continue;
                }
                return false;
            }
        }
        while (index < typed.length()) {
            char check = typed.charAt(index);
            index++;
            if (name.charAt(name.length() - 1) != check) {
                return false;
            }
        }
        return result;
    }
}
