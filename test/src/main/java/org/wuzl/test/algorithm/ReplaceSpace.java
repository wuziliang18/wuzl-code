package org.wuzl.test.algorithm;

public class ReplaceSpace {
    public static void main(String[] args) {
        System.out.println(new ReplaceSpace().replaceSpace("We are happy."));
    }

    private static String white = "%20";

    public String replaceSpace(String s) {

        if (s == null || s.length() == 0) {
            return s;
        }
        StringBuilder sb = new StringBuilder(s.length() * 2);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                sb.append(white);
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
