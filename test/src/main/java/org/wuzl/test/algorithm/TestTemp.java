package org.wuzl.test.algorithm;

import java.util.ArrayList;
import java.util.List;

public class TestTemp {
    public static void main(String[] args) {
        String cover = "ack..ket..asdm..saf";
        String recover = "";
        List<StringBuilder> list = new ArrayList<>();
        char[] arr = cover.toCharArray();
        int k = arr.length;
        StringBuilder sb = new StringBuilder();
        int sign = 0;// 0开始1字母2.
        for (int i = 0; i <= k - 1; i++) {
            if ('a' <= arr[i] && 'z' >= arr[i]) {
                if (sign == 2) {
                    list.add(sb);
                    sb = new StringBuilder();
                }
                sign = 1;
                sb.append(arr[i]);
            } else {
                if (sign == 1) {
                    list.add(sb);
                    sb = new StringBuilder();
                }
                sign = 2;
                sb.append(arr[i]);
            }
        }
        if (sb.length() > 0) {
            list.add(sb);
        }
        for (int i = list.size() - 1; i >= 0; i--) {
            recover += list.get(i);
        }
        System.out.println(recover);

    }
}
