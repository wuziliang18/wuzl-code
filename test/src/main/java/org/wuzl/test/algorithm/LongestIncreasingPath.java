package org.wuzl.test.algorithm;

public class LongestIncreasingPath {
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        PointValueVO[][] cache = new PointValueVO[matrix.length][matrix[0].length];
        int maxValue = 1;
        cache[0][0] = new PointValueVO(1, 1);
        for (int i = 1; i < matrix[0].length; i++) {// 初始化第一行
            if (matrix[0][i] > matrix[0][i - 1]) {
                cache[0][i] = new PointValueVO(1, cache[0][i - 1].getGtValue() + 1);
                maxValue = Math.max(maxValue, cache[0][i].getGtValue());
            } else if (matrix[0][i] < matrix[0][i - 1]) {
                cache[0][i] = new PointValueVO(cache[0][i - 1].getLtValue() + 1, 1);
                maxValue = Math.max(maxValue, cache[0][i].getLtValue());
            } else {
                cache[0][i] = new PointValueVO(1, 1);
            }
        }
        for (int i = 1; i < matrix.length; i++) {// 初始化第一列
            if (matrix[i][0] > matrix[i - 1][0]) {
                cache[i][0] = new PointValueVO(1, cache[i - 1][0].getGtValue() + 1);
                maxValue = Math.max(maxValue, cache[i][0].getGtValue());
            } else if (matrix[0][i] < matrix[0][i - 1]) {
                cache[i][0] = new PointValueVO(cache[i - 1][0].getLtValue() + 1, 1);
                maxValue = Math.max(maxValue, cache[i][0].getLtValue());
            } else {
                cache[i][0] = new PointValueVO(1, 1);
            }
        }
        for (int row = 1; row < matrix.length; row++) {
            for (int column = 1; column < matrix[0].length; column++) {
                if (matrix[row][column] > matrix[row][column - 1]) {
                    cache[row][column] = new PointValueVO(1, cache[row][column - 1].getGtValue() + 1);
                    maxValue = Math.max(maxValue, cache[row][column].getGtValue());
                } else if (matrix[row][column] < matrix[row][column - 1]) {
                    cache[row][column] = new PointValueVO(cache[row][column - 1].getLtValue() + 1, 1);
                    maxValue = Math.max(maxValue, cache[row][column].getLtValue());
                } else {
                    cache[row][column] = new PointValueVO(1, 1);
                }

                if (matrix[row][column] > matrix[row - 1][column]) {
                    cache[row][column] = new PointValueVO(1, cache[row - 1][column].getGtValue() + 1);
                    maxValue = Math.max(maxValue, cache[row][column].getGtValue());
                } else if (matrix[row][column] < matrix[row - 1][column]) {
                    cache[row][column] = new PointValueVO(cache[row - 1][column].getLtValue() + 1, 1);
                    maxValue = Math.max(maxValue, cache[row][column].getLtValue());
                } else {
                    cache[row][column] = new PointValueVO(1, 1);
                }
            }
        }
        return maxValue;
    }

    static class PointValueVO {

        public PointValueVO(int ltValue, int gtValue) {
            super();
            this.ltValue = ltValue;
            this.gtValue = gtValue;
        }

        private int ltValue;
        private int gtValue;

        public int getLtValue() {
            return ltValue;
        }

        public void setLtValue(int ltValue) {
            this.ltValue = ltValue;
        }

        public int getGtValue() {
            return gtValue;
        }

        public void setGtValue(int gtValue) {
            this.gtValue = gtValue;
        }

    }
}
