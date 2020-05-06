package org.wuzl.test.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列
 * 
 * @author ziliang.wu
 *
 */
public class Permute {
    public static void main(String[] args) {
        Permute obj = new Permute();
        System.out.println(obj.permute(new int[] { 1, 2, 3 }));
    }

    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Collections.emptyList();
        }
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            
        }
        return result;
    }
  
}
