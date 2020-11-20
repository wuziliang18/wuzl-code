package org.wuzl.test.algorithm;

/**
 * 比较含退格的字符串 给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符。
 * 
 * 注意：如果对空文本输入退格字符，文本继续为空。
 * 
 * @author ziliang.wu
 *
 */
public class BackspaceCompare {
    public static void main(String[] args) {
        BackspaceCompare obj = new BackspaceCompare();
        System.out.println(obj.backspaceCompare("ab#c", "ad#c"));
        System.out.println(obj.backspaceCompare("ab##", "c#d#"));
    }

    public boolean backspaceCompare(String s, String t) {
        if (s == t) {
            return true;
        }
        if (s == null) {
            s = "";
        }
        if (t == null) {
            t = "";
        }
        int sSignCount = 0;
        int tSignCount = 0;
        return false;
    }
}
