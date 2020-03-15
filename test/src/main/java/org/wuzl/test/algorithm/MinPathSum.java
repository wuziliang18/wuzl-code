package org.wuzl.test.algorithm;

public class MinPathSum {
    public static void main(String[] args) {
        MinPathSum obj = new MinPathSum();
        System.out.println(obj.minPathSum(new int[][] { //
                { 1, 3, 1 } }));
        System.out.println(obj.minPathSum(new int[][] { //
                { 1 }, //
                { 1 }, //
                { 4 } }));
        System.out.println(obj.minPathSum(new int[][] { //
                { 1, 3, 1 }, //
                { 1, 5, 1 }, //
                { 4, 2, 1 } }));
    }

    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        // 处理第一行
        for (int i = 1; i < grid[0].length; i++) {
            grid[0][i] = grid[0][i - 1] + grid[0][i];
        }
        if (grid.length == 1) {
            return grid[0][grid[0].length - 1];
        }
        // 处理第一列
        for (int i = 1; i < grid.length; i++) {
            grid[i][0] = grid[i - 1][0] + grid[i][0];
        }
        if (grid[0].length == 1) {
            return grid[grid.length - 1][0];
        }
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                grid[i][j] = grid[i][j] + Math.min(grid[i - 1][j], grid[i][j - 1]);
            }
        }
        return grid[grid.length - 1][grid[0].length - 1];
    }
}
