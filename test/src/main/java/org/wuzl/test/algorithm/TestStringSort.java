package org.wuzl.test.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestStringSort {
    public static void main(String[] args) {
        List<String> rows = new ArrayList(Arrays.asList("tH2xc5OF4", "", "zH2xc5O", "tH2xc5O", "tH2xc5OF4z23CXgD"));
        rows.sort((resource1, resource2) -> {
            int lengthCompare = Integer.compare(resource1.length(), resource2.length());
            return lengthCompare;
        });
        System.out.println(rows);
    }
}
