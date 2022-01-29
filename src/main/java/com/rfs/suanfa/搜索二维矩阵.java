package com.rfs.suanfa;

/**
 * @author renfushuai
 * @date 2022/1/28
 */
public class 搜索二维矩阵 {
    public static void main(String[] args) {

    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        int r = matrix.length;
        int c = matrix[0].length;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (matrix[i][j] == target) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean searchMatrix1(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int m = matrix.length, n = matrix[0].length;
        //右上角的元素是这一行元素的最大值，又是这一列的最小值
        int row = 0, col = n - 1;
        while (row < m && col >= 0) {
            if (matrix[row][col]==target){
                return true;
            }else if(matrix[row][col]>target){
                col--;
            }else {
                 row++;
            }
        }
        return false;
    }
}
