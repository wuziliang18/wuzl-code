package org.wuzl.test.algorithm;

/**
 * 车的可用捕获量
 * 
 * 在一个 8 x 8 的棋盘上，有一个白色车（rook）。也可能有空方块，白色的象（bishop）和黑色的卒（pawn）。它们分别以字符 “R”，“.”，“B” 和 “p” 给出。大写字符表示白棋，小写字符表示黑棋。
 * 
 * 链接：https://leetcode-cn.com/problems/available-captures-for-rook
 * 
 * @author ziliang.wu
 *
 */
public class NumRookCaptures {
    public static void main(String[] args) {
        NumRookCaptures obj = new NumRookCaptures();
        System.out.println(obj.numRookCaptures(new char[][] { { '.', '.', '.', '.', '.', '.', '.', '.' }, //
                { '.', '.', '.', 'p', '.', '.', '.', '.' }, //
                { '.', '.', '.', 'R', '.', '.', '.', 'p' }, //
                { '.', '.', '.', '.', '.', '.', '.', '.' }, //
                { '.', '.', '.', '.', '.', '.', '.', '.' }, //
                { '.', '.', '.', 'p', '.', '.', '.', '.' }, //
                { '.', '.', '.', '.', '.', '.', '.', '.' }, //
                { '.', '.', '.', '.', '.', '.', '.', '.' } }));
    }

    public int numRookCaptures(char[][] board) {
        if (board == null || board.length == 0) {
            return 0;
        }
        int row = 0;
        int column = 0;
        out: for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'R') {
                    row = i;
                    column = j;
                    break out;
                }
            }
        }
        int count = 0;
        // 处理行
        for (int i = column - 1; i >= 0; i--) {
            if (board[row][i] == 'p') {
                count++;
                break;
            }
            if (board[row][i] == 'B') {
                break;
            }
        }

        for (int i = column + 1; i < board[0].length; i++) {
            if (board[row][i] == 'p') {
                count++;
                break;
            }
            if (board[row][i] == 'B') {
                break;
            }
        }
        // 处理列

        for (int i = row - 1; i >= 0; i--) {
            if (board[i][column] == 'p') {
                count++;
                break;
            }
            if (board[i][column] == 'B') {
                break;
            }
        }

        for (int i = row + 1; i < board.length; i++) {
            if (board[i][column] == 'p') {
                count++;
                break;
            }
            if (board[i][column] == 'B') {
                break;
            }
        }
        return count;
    }
}
