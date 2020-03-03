package org.wuzl.test.algorithm;

import java.util.LinkedList;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * 
 * 有效字符串需满足：
 * 
 * 左括号必须用相同类型的右括号闭合。 左括号必须以正确的顺序闭合。 注意空字符串可被认为是有效字符串。
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/valid-parentheses 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。给定一个只包括
 * '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * 
 * 有效字符串需满足：
 * 
 * 左括号必须用相同类型的右括号闭合。 左括号必须以正确的顺序闭合。 注意空字符串可被认为是有效字符串。
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/valid-parentheses 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author ziliang.wu
 *
 */
public class BracketsIsValid {
    public static void main(String[] args) {
        System.out.println(isValid("){"));
    }

    public static boolean isValid(String input) {
        if (input == null || input.equals("")) {
            return true;
        }
        if (input.length() % 2 != 0) {
            return false;
        }
        LinkedList<Character> linkList = new LinkedList<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                linkList.addLast(c);
            } else {
                if (linkList.isEmpty()) {
                    return false;
                }
                char last = linkList.removeLast();
                switch (c) {
                case ')':
                    if (last != '(') {
                        return false;
                    }
                    break;
                case ']':
                    if (last != '[') {
                        return false;
                    }
                    break;
                case '}':
                    if (last != '{') {
                        return false;
                    }
                    break;
                default:
                    return false;
                }

            }
        }
        return linkList.isEmpty();
    }
}
