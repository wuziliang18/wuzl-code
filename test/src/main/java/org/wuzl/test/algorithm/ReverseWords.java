package org.wuzl.test.algorithm;

/**
 * 翻转单词
 * 
 * @author ziliang.wu
 *
 */
public class ReverseWords {
    public static void main(String[] args) {
        ReverseWords obj = new ReverseWords();
        System.out.println(obj.reverseWords(" "));
        System.out.println(obj.reverseWords("the sky is blue"));
        System.out.println(obj.reverseWords("  hello world!  "));
        System.out.println(obj.reverseWords("a good example"));
        System.out.println(obj.reverseWords("a good example b"));
    }

    public String reverseWords(String s) {
        if (s == null || s.equals("")) {
            return s;
        }
        // TODO
        // 考虑先循环计算需要多少 char 剔除多余空白
        // 然后循环char数组
        return s;
    }

    /**
     * 考虑从后往前查找 竟然他妈只快一点 内存还多了点
     * 
     * @param s
     * @return
     */
    public String reverseWordsV2(String s) {
        if (s == null || s.equals("")) {
            return s;
        }
        char empty = ' ';
        StringBuilder sb = new StringBuilder(s.length());
        int endIndex = -1;
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c != ' ') {
                if (endIndex == -1) {
                    endIndex = i;
                }

            } else {
                if (endIndex == -1) {
                    continue;
                }
                for (int j = i + 1; j <= endIndex; j++) {
                    sb.append(s.charAt(j));
                }
                sb.append(empty);
                endIndex = -1;
            }
        }
        if (endIndex != -1) {
            for (int j = 0; j <= endIndex; j++) {
                sb.append(s.charAt(j));
            }
        } else {
            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        return sb.toString();
    }

    public String reverseWordsV1(String s) {
        if (s == null || s.equals("")) {
            return s;
        }
        String[] array = s.trim().split("[ ]+");
        StringBuilder sb = new StringBuilder(s.length());
        sb.append(array[array.length - 1]);
        for (int i = array.length - 2; i >= 0; i--) {
            sb.append(" ").append(array[i]);
        }
        return sb.toString();
    }
}
