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
        System.out.println(obj.reverseWords("  hello world!  "));
        // System.out.println(obj.reverseWords(" "));
        // System.out.println(obj.reverseWords("the sky is blue"));
        // System.out.println(obj.reverseWords(" hello world! "));
        // System.out.println(obj.reverseWords("a good example"));
        // System.out.println(obj.reverseWords("a good example b"));
        System.out.println(obj.reverseWordsNewV2("Let's take LeetCode contest"));
    }

    /**
     * 反转字符串中的单词 III 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
     * 
     * @param s
     * @return
     */
    public String reverseWordsNewV2(String s) {
        if (s == null || s.trim().equals("")) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int start = -1;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                if (start == -1) {
                    continue;
                }
                for (int end = i - 1; end >= start; end--) {
                    sb.append(s.charAt(end));
                }
                sb.append(" ");
                start = -1;
            } else {
                if (start == -1) {
                    start = i;
                }
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        if (start != -1) {
            sb.append(" ");
            for (int end = s.length() - 1; end >= start; end--) {
                sb.append(s.charAt(end));
            }
        }
        return sb.toString();
    }

    /**
     * 反转字符串中的单词 III 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
     * 
     * @param s
     * @return
     */
    public String reverseWordsNew(String s) {
        if (s == null || s.trim().equals("")) {
            return "";
        }
        String[] array = s.trim().split("[ ]+");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            String temp = array[i];
            for (int j = temp.length() - 1; j >= 0; j--) {
                sb.append(temp.charAt(j));
            }
            sb.append(" ");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    /**
     * 第二次写
     * 
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }
        String[] array = s.trim().split(" ");
        if (array.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(array[array.length - 1].trim());
        for (int i = array.length - 2; i >= 0; i--) {
            String word = array[i].trim();
            if (!word.isEmpty()) {
                sb.append(" ").append(word);
            }
        }
        return sb.toString();
    }

    public String reverseWordsV3(String s) {
        if (s == null || s.trim().equals("")) {
            return "";
        }
        String[] array = s.trim().split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = array.length - 1; i >= 1; i--) {
            if (array[i].equals("")) {
                continue;
            }
            sb.append(array[i].trim()).append(" ");
        }
        sb.append(array[0].trim());
        return sb.toString();
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
