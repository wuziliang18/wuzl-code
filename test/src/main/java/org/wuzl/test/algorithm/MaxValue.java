package org.wuzl.test.algorithm;

/**
 * 礼物的最大价值
 * 
 * 在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于
 * 0）。你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
 * 
 * 链接：https://leetcode-cn.com/problems/li-wu-de-zui-da-jie-zhi-lcof
 * 
 * @author ziliang.wu
 *
 */
public class MaxValue {
    public int maxValue(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int row = grid.length;
        int column = grid[0].length;
        int[][] array = new int[row][column];
        array[0][0] = grid[0][0];
        for (int i = 1; i < column; i++) {
            array[0][i] = array[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < row; i++) {
            array[i][0] = array[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < column; j++) {
                array[i][j] = grid[i][j] + Math.max(array[i - 1][j], array[i][j - 1]);
            }
        }
        return array[row - 1][column - 1];
    }
}
