package org.wuzl.test.algorithm;

public class MovingCount {
    public static void main(String[] args) {
        MovingCount obj = new MovingCount();
//        System.out.println(obj.movingCount(2, 3, 1));
        // System.out.println(obj.movingCount(3, 1, 0));
        // System.out.println(obj.movingCount(16, 8, 4));
         System.out.println(obj.movingCount(38, 15, 9));
    }

    public int movingCount(int m, int n, int k) {
        if (k <= 0) {
            return 1;
        }
        return movingCount(0, 0, k, new boolean[m][n]);
    }

    private int movingCount(int rowIndex, int columnIndex, int k, boolean[][] arrived) {
        if (rowIndex < 0 || columnIndex < 0 || rowIndex >= arrived.length || columnIndex >= arrived[0].length) {
            return 0;
        }
        if (!arrived[rowIndex][columnIndex] && check(rowIndex, columnIndex, k)) {
            arrived[rowIndex][columnIndex] = true;
            return 1 + movingCount(rowIndex, columnIndex + 1, k, arrived)
                    + movingCount(rowIndex + 1, columnIndex, k, arrived)
                    + movingCount(rowIndex - 1, columnIndex, k, arrived)
                    + movingCount(rowIndex, columnIndex - 1, k, arrived);
        }
        return 0;
    }

    private boolean check(int rowIndex, int columnIndex, int k) {
        return rowIndex + columnIndex <= k ? true : getValue(rowIndex) + getValue(columnIndex) <= k;
    }

    private int getValue(int num) {
        if (num >= 100) {
            return num / 100 + getValue(num % 100);
        }
        if (num >= 10) {
            return num / 10 + num % 10;
        }
        return num;
    }
    // public int movingCount(int m, int n, int k) {
    // if (k <= 0) {
    // return 1;
    // }
    // if (m < 1 || n < 1) {
    // return 0;
    // }
    // return dp(m, n, k, new boolean[m][n], 0, 0);
    // }
    //
    // private int dp(int m, int n, int k, boolean[][] array, int r, int c) {
    // if (r < 0 || r >= m || c < 0 || c >= n) {
    // return 0;
    // }
    // if (array[r][c]) {
    // return 0;
    // }
    // array[r][c] = true;
    // if (!check(r, c, k)) {
    // return 0;
    // }
    // return 1 + dp(m, n, k, array, r - 1, c) + dp(m, n, k, array, r + 1, c) //
    // + dp(m, n, k, array, r, c + 1) + dp(m, n, k, array, r, c - 1);
    // }
    //
    // private boolean check(int m, int n, int k) {
    // if (m + n <= k) {
    // return true;
    // }
    // if (m >= 10) {
    // m = m / 10 + m % 10;
    // }
    // if (n >= 10) {
    // n = n / 10 + n % 10;
    // }
    // return m + n <= k;
    // }
}
