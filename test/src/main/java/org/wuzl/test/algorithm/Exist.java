package org.wuzl.test.algorithm;

/**
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一格开始，每一步可以在矩阵中向左、右、上、下移动一格。如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。例如，在下面的3×4的矩阵中包含一条字符串“bfce”的路径（路径中的字母用加粗标出）。
 * 
 * [['a','b','c','e'], ['s','f','c','s'], ['a','d','e','e']]
 * 
 * 但矩阵中不包含字符串“abfb”的路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入这个格子。
 * 
 * 链接：https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof
 * 
 * @author ziliang.wu
 *
 */
public class Exist {
    public static void main(String[] args) {
        Exist exist = new Exist();
        // System.out.println(exist.exist(new char[][] { { 'a', 'b', 'c', 'e' }, //
        // { 's', 'f', 'c', 's' }, //
        // { 'a', 'd', 'e', 'e' } }, "1"));
        // System.out.println(exist.exist(new char[][] { { 'a', 'b', 'c', 'e' }, //
        // { 's', 'f', 'c', 's' }, //
        // { 'a', 'd', 'e', 'e' } }, "d"));
        // System.out.println(exist.exist(new char[][] { { 'a', 'b', 'c', 'e' }, //
        // { 's', 'f', 'c', 's' }, //
        // { 'a', 'd', 'e', 'e' } }, "bfce"));
        // System.out.println(exist.exist(new char[][] { { 'a', 'b', 'c', 'e' }, //
        // { 's', 'f', 'c', 's' }, //
        // { 'a', 'd', 'e', 'e' } }, "abfb"));

        System.out.println(exist.exist(new char[][] { { 'a', 'b' }, { 'c', 'd' } }, "dbac"));
    }

    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0) {
            return word == null || word.isEmpty();
        }
        return find(board, word, 0, new boolean[board.length][board[0].length], 0, 0);
    }

    private boolean find(char[][] board, String word, int index, boolean[][] array, int i, int j) {
        char cSearch = word.charAt(index);
        int row = array.length;
        int column = array[0].length;
        if (index == 0) {// 第一个
            for (int r = 0; r < row; r++) {
                for (int c = 0; c < column; c++) {
                    if (board[r][c] == cSearch) {
                        if (index == word.length() - 1) {
                            return true;
                        }
                        array[r][c] = true;
                        boolean result = find(board, word, index + 1, array, r, c);
                        if (result) {
                            return true;
                        }
                        array[r][c] = false;
                    }
                }
            }
            return false;
        }
        if (i != 0) {
            if (board[i - 1][j] == cSearch && !array[i - 1][j]) {
                if (index == word.length() - 1) {
                    return true;
                }
                array[i - 1][j] = true;
                boolean result = find(board, word, index + 1, array, i - 1, j);
                if (result) {
                    return true;
                }
                array[i - 1][j] = false;
            }
        }
        if (i < row - 1) {
            if (board[i + 1][j] == cSearch && !array[i + 1][j]) {
                if (index == word.length() - 1) {
                    return true;
                }
                array[i + 1][j] = true;
                boolean result = find(board, word, index + 1, array, i + 1, j);
                if (result) {
                    return true;
                }
                array[i + 1][j] = false;
            }
        }
        if (j != 0) {
            if (board[i][j - 1] == cSearch && !array[i][j - 1]) {
                if (index == word.length() - 1) {
                    return true;
                }
                array[i][j - 1] = true;
                boolean result = find(board, word, index + 1, array, i, j - 1);
                if (result) {
                    return true;
                }
                array[i][j - 1] = false;
            }
        }
        if (j < column - 1) {
            if (board[i][j + 1] == cSearch && !array[i][j + 1]) {
                if (index == word.length() - 1) {
                    return true;
                }
                array[i][j + 1] = true;
                boolean result = find(board, word, index + 1, array, i, j + 1);
                if (result) {
                    return true;
                }
                array[i][j + 1] = false;
            }
        }
        return false;
    }
}
