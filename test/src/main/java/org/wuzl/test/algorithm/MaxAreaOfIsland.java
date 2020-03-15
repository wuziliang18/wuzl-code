package org.wuzl.test.algorithm;

/**
 * 岛屿的最大面积 给定一个包含了一些 0 和 1的非空二维数组 grid , 一个 岛屿 是由四个方向 (水平或垂直) 的 1 (代表土地) 构成的组合。你可以假设二维矩阵的四个边缘都被水包围着。
 * 
 * 链接：https://leetcode-cn.com/problems/max-area-of-island
 * 
 * @author ziliang.wu
 *
 */
public class MaxAreaOfIsland {

    public static void main(String[] args) {
        MaxAreaOfIsland obj = new MaxAreaOfIsland();
        System.out.println(obj.maxAreaOfIsland(new int[][] { { 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 }, //
                { 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0 }, //
                { 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 }, //
                { 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0 }, //
                { 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0 }, //
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 }, //
                { 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0 }, //
                { 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0 } }));

        System.out.println(obj.maxAreaOfIsland(new int[][] { { 1, 1, 0, 0, 0 }, //
                { 1, 1, 0, 0, 0 }, //
                { 0, 0, 0, 1, 1 }, //
                { 0, 0, 0, 1, 1 } }));
    }

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    int temp = dpClearLand(grid, i, j);
                    max = Math.max(max, temp);
                }
            }
        }
        return max;
    }

    private int dpClearLand(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) {
            return 0;
        }
        grid[i][j] = 0;
        return 1 + dpClearLand(grid, i - 1, j) + dpClearLand(grid, i + 1, j) //
                + dpClearLand(grid, i, j - 1) + dpClearLand(grid, i, j + 1);
    }
}
