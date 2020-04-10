package org.wuzl.test.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 括号生成
 * 
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * 
 * https://leetcode-cn.com/problems/generate-parentheses/
 * 
 * @author ziliang.wu
 *
 */
public class GenerateParenthesis {
    public static void main(String[] args) {
        System.out.println(new GenerateParenthesis().generateParenthesis(1));
        System.out.println(new GenerateParenthesis().generateParenthesis(3));
    }

    public List<String> generateParenthesis(int n) {
        if (n <= 0) {
            return Collections.emptyList();
        }
        List<String> result = new ArrayList();
        this.dfs(result, n, n, "");
        return result;
    }

    private void dfs(List<String> result, int left, int right, String s) {
        if (left == 0 && right == 0) {
            result.add(s);
            return;
        }
        if (left > 0) {
            this.dfs(result, left - 1, right, s + "(");
        }
        if (right > left) {
            this.dfs(result, left, right - 1, s + ")");
        }
    }
}
