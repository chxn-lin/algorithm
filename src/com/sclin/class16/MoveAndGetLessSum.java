package com.sclin.class16;

import static class21.Code01_MinPathSum.generateRandomMatrix;
import static class21.Code01_MinPathSum.minPathSum2;

public class MoveAndGetLessSum {

//    给定一个二维数组matrix，一个人必须从左下出发，
//    最后到达右下角，沿途只可以向下或者向右走，
//    沿途的数字都累加就是距离累加和，
//    返回最小的累加和

    public static int method1(int[][] matrix){
        if (matrix.length == 0)
            return 0;
        return process(matrix, matrix.length - 1, matrix[0].length - 1);
    }

    // 含义为到row，col位置，最短的累加和
    public static int process(int[][] arr, int row, int col){
        if (row < 0 || col < 0) {
            return Integer.MAX_VALUE;
        }
        if (row == 0 && col == 0) {
            return arr[0][0];
        }
        int p1 = process(arr, row - 1, col);
        int p2 = process(arr, row, col - 1);
        int result = Math.min(p1, p2);
        if (result != Integer.MAX_VALUE) {
            result += arr[row][col];
        }
        return result;
    }

    public static int method2(int[][] arr){
        if (arr.length == 0)
            return 0;
        int[][] dp = new int[arr.length][arr[0].length];

        dp[0][0] = arr[0][0];
        for (int row = 1; row < arr.length; row++) {
            dp[row][0] = dp[row - 1][0] + arr[row][0];
        }
        for (int col = 1; col < arr[0].length; col++) {
            dp[0][col] = dp[0][col - 1] + arr[0][col];
        }
        for (int row = 1; row < arr.length; row++) {
            for (int col = 1; col < arr[0].length; col++) {
                int p1 = dp[row - 1][col];
                int p2 = dp[row][col - 1];
                dp[row][col] = Math.min(p1, p2) + arr[row][col];
            }
        }
        return dp[arr.length - 1][arr[0].length - 1];
    }

    // 按行存储数据
    public static int method3(int[][] arr){
        if (arr.length == 0)
            return 0;
        int[] dp = new int[arr[0].length];
        dp[0] = arr[0][0];
        for (int i = 1; i < arr[0].length; i++) {
            dp[i] = dp[i - 1] + arr[0][i];
        }
        for (int j = 1; j < arr.length; j++) {
            for (int i = 0; i < arr[0].length; i++) {
                if (i == 0) {
                    dp[0] = dp[0] + arr[j][0];
                } else {
                    dp[i] = Math.min(dp[i - 1], dp[i]) + arr[j][i];
                }
            }
        }

        return dp[arr.length - 1];
    }

    public static void main(String[] args) {
        int rowSize = 10;
        int colSize = 10;
//        int[][] m = generateRandomMatrix(rowSize, colSize);
        int[][] m = {
                {1,4},
                {8,3}
        };

        System.out.println("true :" + minPathSum2(m));
        System.out.println("mine1:" + method1(m));
        System.out.println("mine2:" + method2(m));
        System.out.println("mine3:" + method3(m));
    }

}
