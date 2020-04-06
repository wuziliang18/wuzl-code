package org.wuzl.test.algorithm;

/**
 * 地图分析
 * 
 * 你现在手里有一份大小为 N x N
 * 的『地图』（网格） grid，上面的每个『区域』（单元格）都用 0 和 1 标记好了。其中 0 代表海洋，1 代表陆地，你知道距离陆地区域最远的海洋区域是是哪一个吗？请返回该海洋区域到离它最近的陆地区域的距离。
 * 
 * 链接：https://leetcode-cn.com/problems/as-far-from-land-as-possible
 * 
 * @author ziliang.wu
 *
 */
public class MaxDistance {
    public int maxDistance(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return -1;
        }
        int result = -1;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0) {
                    int left = -1;
                    int right = -1;
                    int up = -1;
                    int down = -1;
                    for (int temp = j; temp >= 0; temp--) {
                        if (grid[i][temp] == 1) {
                            left = j - temp;
                            break;
                        }
                    }
                    for (int temp = j; temp < grid[i].length; temp++) {
                        if (grid[i][temp] == 1) {
                            right = temp - j;
                            break;
                        }
                    }
                    for (int temp = i; temp >= 0; temp--) {
                        if (grid[temp][j] == 1) {
                            up = j - temp;
                            break;
                        }
                    }
                    for (int temp = i; temp < grid.length; temp++) {
                        if (grid[temp][j] == 1) {
                            down = temp - j;
                            break;
                        }
                    }
                }
            }
        }
        return result;
    }
}
