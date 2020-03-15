package org.wuzl.test.algorithm;

/**
 * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof
 * 
 * @author ziliang.wu
 *
 */
public class FindNumberIn2DArray {
    public static void main(String[] args) {
        FindNumberIn2DArray obj = new FindNumberIn2DArray();
        // System.out.println(obj.findNumberIn2DArray(new int[][] { { 1, 4, 7, 11, 15 }, //
        // { 2, 5, 8, 12, 19 }, //
        // { 3, 6, 9, 16, 22 }, //
        // { 10, 13, 14, 17, 24 }, //
        // { 18, 21, 23, 26, 30 } }
        //
        // , 5));
        // System.out.println(obj.findNumberIn2DArray(new int[][] { { 1, 4, 7, 11, 15 }, //
        // { 2, 5, 8, 12, 19 }, //
        // { 3, 6, 9, 16, 22 }, //
        // { 10, 13, 14, 17, 24 }, //
        // { 18, 21, 23, 26, 30 } }
        //
        // , 20));

        // System.out.println(obj.findNumberIn2DArray(new int[][] { { -1, 3 } }, 3));
        System.out.println(obj.findNumberIn2DArray(new int[][] { { 1, 2, 3, 4, 5 }, { 6, 7, 8, 9, 10 },
                { 11, 12, 13, 14, 15 }, { 16, 17, 18, 19, 20 }, { 21, 22, 23, 24, 25 } }, 19));
    }

    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int row = matrix.length;
        int column = matrix[0].length;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        // 从一个角开始
        int i = 0;
        int j = column - 1;
        while (i < row && j >= 0) {
            if (matrix[i][j] == target) {
                return true;
            }
            if (matrix[i][j] > target) {
                j--;
                continue;
            }
            i++;
        }
        return false;
    }
    // public boolean findNumberIn2DArray(int[][] matrix, int target) {
    // if (matrix == null || matrix.length == 0) {
    // return false;
    // }
    // int row = matrix.length;
    // int column = matrix[0].length;
    // if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
    // return false;
    // }
    // int i = 0;
    // while (i < column) {
    // if (matrix[0][i] == target) {
    // return true;
    // }
    // if (matrix[0][i] > target) {
    // return false;
    // }
    //
    // int j = 1;
    // while (j < row) {
    // if (matrix[j][i] == target) {
    // return true;
    // }
    // if (matrix[j][i] > target) {
    // break;
    // }
    // j++;
    // }
    //
    // i++;
    // }
    // return false;
    // }
}
