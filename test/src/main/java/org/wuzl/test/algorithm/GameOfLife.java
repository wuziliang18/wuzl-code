package org.wuzl.test.algorithm;

/**
 * 生命游戏
 * 
 * 给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞都具有一个初始状态：1 即为活细胞（live），或 0
 * 即为死细胞（dead）。每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
 * 
 * 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
 * 
 * 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
 * 
 * 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
 * 
 * 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
 * 
 * 链接：https://leetcode-cn.com/problems/game-of-life
 * 
 * @author ziliang.wu
 *
 */
public class GameOfLife {
    public static void main(String[] args) {
        int[][] board = new int[][] { //
                { 0, 1, 0 }, //
                { 0, 0, 1 }, //
                { 1, 1, 1 }, //
                { 0, 0, 0 }//
        };
        new GameOfLife().gameOfLife(board);
    }

    public void gameOfLife(int[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        int[][] copy = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                copy[i][j] = board[i][j];
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int liveNum = getLiveNum(copy, i, j);
                if (copy[i][j] == 1) {
                    if (liveNum < 2 || liveNum > 3) {
                        board[i][j] = 0;
                    }
                } else {
                    if (liveNum == 3) {
                        board[i][j] = 1;
                    }
                }

            }
        }
    }

    private int getLiveNum(int[][] copy, int i, int j) {
        int count = 0;
        int row = i - 1 >= 0 ? i - 1 : 0;
        int rowEnd = i + 1 < copy.length - 1 ? i + 1 : copy.length - 1;
        int columnEnd = j + 1 < copy[0].length - 1 ? j + 1 : copy[0].length - 1;
        while (row <= rowEnd) {
            int column = j - 1 >= 0 ? j - 1 : 0;
            while (column <= columnEnd) {
                count += copy[row][column];
                column++;
            }
            row++;
        }
        return count - copy[i][j];
    }
}
