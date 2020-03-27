package org.wuzl.test.algorithm;

public class TranslateNum {
    public static void main(String[] args) {
        TranslateNum obj = new TranslateNum();
//        System.out.println(obj.translateNum(12258));
        System.out.println(obj.translateNum(10));
    }

    public int translateNum(int num) {
        if (num < 10) {
            return 1;
        }
        String s = String.valueOf(num);
        int[] array = new int[s.length()];
        array[0] = 1;
        array[1] = ((s.charAt(0) == '2' && s.charAt(1) <= '5') || s.charAt(0) == '1') ? 2 : 1;
        char pre = s.charAt(1);
        for (int i = 2; i < s.length(); i++) {
            array[i] = array[i - 1];
            if ((pre == '2' && s.charAt(i) <= '5') || pre == '1') {
                array[i] = array[i - 1] + array[i - 2];
            }
            pre = s.charAt(i);
        }
        return array[s.length() - 1];
    }
}
