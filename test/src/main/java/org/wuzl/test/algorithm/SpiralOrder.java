package org.wuzl.test.algorithm;

import java.util.Arrays;

/**
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 * 
 * 
 * @author ziliang.wu
 *
 */
public class SpiralOrder {
    public static void main(String[] args) {
        SpiralOrder obj = new SpiralOrder();
        System.out.println(Arrays.toString(obj.spiralOrder(new int[][] { //
                { 1, 2, 3 }, //
                { 4, 5, 6 }, //
                { 7, 8, 9 } })));

    }

    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null ) {
            return null;
        }
        if(matrix.length==0) {
            return new int[0];
        }
        if (matrix[0].length == 0) {
            return matrix[0];
        }
        int[] result = new int[matrix.length * matrix[0].length];
        int index = 0;
        int upRowIndex = 0;
        int downRowIndex = matrix.length - 1;
        int upColumnIndex = 0;
        int downColumnIndex = matrix[0].length - 1;
        boolean rowAsc = true;
        boolean columnAsc = true;
        while (index < result.length) {
            if (rowAsc) {
                for (int i = upColumnIndex; i <= downColumnIndex; i++) {
                    result[index++] = matrix[upRowIndex][i];
                }
                if (index >= result.length - 1) {
                    break;
                }
                upRowIndex++;
                for (int i = upRowIndex; i <= downRowIndex; i++) {
                    result[index++] = matrix[i][downColumnIndex];
                }
                downColumnIndex--;
            }
            for (int i = downColumnIndex; i >= upColumnIndex; i--) {
                result[index++] = matrix[downRowIndex][i];
            }
            if (index >= result.length - 1) {
                break;
            }
            downRowIndex--;
            for (int i = downRowIndex; i >= upRowIndex; i--) {
                result[index++] = matrix[i][upColumnIndex];
            }
            upColumnIndex++;

        }
        return result;
    }
}
