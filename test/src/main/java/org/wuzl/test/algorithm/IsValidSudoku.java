package org.wuzl.test.algorithm;

public class IsValidSudoku {
    public static void main(String[] args) {
        IsValidSudoku obj = new IsValidSudoku();
        // System.out.println(obj.isValidSudoku(new char[][] { //
        // { '5', '3', '.', '.', '7', '.', '.', '.', '.' }, //
        // { '6', '.', '.', '1', '9', '5', '.', '.', '.' }, //
        // { '.', '9', '8', '.', '.', '.', '.', '6', '.' }, //
        // { '8', '.', '.', '.', '6', '.', '.', '.', '3' }, //
        // { '4', '.', '.', '8', '.', '3', '.', '.', '1' }, //
        // { '7', '.', '.', '.', '2', '.', '.', '.', '6' }, //
        // { '.', '6', '.', '.', '.', '.', '2', '8', '.' }, //
        // { '.', '.', '.', '4', '1', '9', '.', '.', '5' }, //
        // { '.', '.', '.', '.', '8', '.', '.', '7', '9' } }));
        // System.out.println(obj.isValidSudoku(new char[][] { //
        // { '8', '3', '.', '.', '7', '.', '.', '.', '.' }, //
        // { '6', '.', '.', '1', '9', '5', '.', '.', '.' }, //
        // { '.', '9', '8', '.', '.', '.', '.', '6', '.' }, //
        // { '8', '.', '.', '.', '6', '.', '.', '.', '3' }, //
        // { '4', '.', '.', '8', '.', '3', '.', '.', '1' }, //
        // { '7', '.', '.', '.', '2', '.', '.', '.', '6' }, //
        // { '.', '6', '.', '.', '.', '.', '2', '8', '.' }, //
        // { '.', '.', '.', '4', '1', '9', '.', '.', '5' }, //
        // { '.', '.', '.', '.', '8', '.', '.', '7', '9' } }));
        System.out.println(obj.isValidSudoku(new char[][] { //
                { '.', '.', '4', '.', '.', '.', '6', '3', '.' }, //
                { '.', '.', '.', '.', '.', '.', '.', '.', '.' }, //
                { '5', '.', '.', '.', '.', '.', '.', '9', '.' }, //
                { '.', '.', '.', '5', '6', '.', '.', '.', '.' }, //
                { '4', '.', '3', '.', '.', '.', '.', '.', '1' }, //
                { '.', '.', '.', '7', '.', '.', '.', '.', '.' }, //
                { '.', '.', '.', '5', '.', '.', '.', '.', '.' }, //
                { '.', '.', '.', '.', '.', '.', '.', '.', '.' }, //
                { '.', '.', '.', '.', '.', '.', '.', '.', '.' } }));
    }

    public boolean isValidSudoku(char[][] board) {
        if (board == null || board.length != 9 && board[0].length != 9) {
            return false;
        }
        for (int i = 0; i < 9; i++) {
            boolean[] array = new boolean[9];
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int index = board[i][j] - 49;
                    if (array[index]) {
                        return false;
                    }
                    array[index] = true;
                }
            }
        }
        for (int i = 0; i < 9; i++) {
            boolean[] array = new boolean[9];
            for (int j = 0; j < 9; j++) {
                if (board[j][i] != '.') {
                    int index = board[j][i] - 49;
                    if (array[index]) {
                        return false;
                    }
                    array[index] = true;
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boolean[] array = new boolean[9];
                int rStart = i * 3;
                int cStart = j * 3;
                for (int r = rStart; r < rStart + 3; r++) {
                    for (int c = cStart; c < cStart + 3; c++) {
                        if (board[r][c] != '.') {
                            int index = board[r][c] - 49;
                            if (array[index]) {
                                return false;
                            }
                            array[index] = true;
                        }
                    }
                }
            }
        }
        return true;
    }
}
