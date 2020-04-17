package org.wuzl.test.algorithm;

/**
 * 矩阵
 * 
 * 
 * 给定一个由 0 和 1 组成的矩阵，找出每个元素到最近的 0 的距离。
 * 
 * 两个相邻元素间的距离为 1 。
 * 
 * https://leetcode-cn.com/problems/01-matrix/
 * 
 * @author ziliang.wu
 *
 */
public class UpdateMatrix {
    //TODO
    public int[][] updateMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return matrix;
        }
        int[][] result = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if(result[i][j]!=-1) {
                    continue;
                }
                if (matrix[i][j] == 0) {
                    result[i][j] = 0;
                } else {
                    result[i][j] = findNearZero(i, j, matrix, result);
                }
            }
        }
        return result;
    }

    private int findNearZero(int i, int j, int[][] matrix, int[][] result) {
        
        return 0;
    }
}
