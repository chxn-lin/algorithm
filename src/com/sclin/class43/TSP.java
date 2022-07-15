package com.sclin.class43;

import java.util.ArrayList;
import java.util.List;

import static class43.Code02_TSP.*;
import static com.sclin.class18.MinCoins.printArr;

public class TSP {

    public static int method1(int[][] matrix) {
        int N = matrix.length;

        return process1(matrix, (1 << N) - 1, 0);
    }

    private static int process1(int[][] matrix, int cityStatus, int start) {
        if ((cityStatus & (~cityStatus + 1)) == cityStatus) {
            return matrix[start][0];
        }
        cityStatus = cityStatus - (1 << start);
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            if ((cityStatus & (1 << i)) != 0) {
                int cur = matrix[start][i] + process1(matrix, cityStatus, i);
                res = Math.min(cur, res);
            }
        }

        return res;
    }

    public static int method2(int[][] matrix) {
        int N = matrix.length;
        int[][] dp = new int[1 << N][N];
        for (int i = 0; i < (1 << N); i++) {
            for (int j = 0; j < N; j++) {
                dp[i][j] = -1;
            }
        }
        return process2(matrix, (1 << N) - 1, 0, dp);
    }

    private static int process2(int[][] matrix, int cityStatus, int start, int[][] dp) {
        if (dp[cityStatus][start] != -1) {
            return dp[cityStatus][start];
        }
        int res = Integer.MAX_VALUE;
        if ((cityStatus & (~cityStatus + 1)) == cityStatus) {
            res = matrix[start][0];
        } else {
            cityStatus = cityStatus - (1 << start);

            for (int i = 0; i < matrix.length; i++) {
                if ((cityStatus & (1 << i)) != 0) {
                    int cur = matrix[start][i] + process2(matrix, cityStatus, i, dp);
                    res = Math.min(cur, res);
                }
            }
        }
        dp[cityStatus][start] = res;
        return dp[cityStatus][start];
    }

    public static void main(String[] args) {
//        int[][] matrix = {
////                {0, 1, 8},
////                {1, 0, 5},
////                {8, 5, 0}
//                {0,3},
//                {3,0}
//        };
//        int origin = 0;
//        int ans1 = method1(matrix);
//        int ans3 = t2(matrix);
//        System.out.println("my : " + ans1 + ", true : " + ans3);

        int len = 10;
        int value = 100;
        System.out.println("功能测试开始");
        for (int i = 0; i < 20000; i++) {
            int[][] matrix = generateGraph(len, value);
            int origin = (int) (Math.random() * matrix.length);
            int ans1 = method2(matrix);
//            int ans1 = tsp2(matrix, origin);
            int ans3 = t2(matrix);
            if (ans1 != ans3) {
                printArr(matrix);
                System.out.println("fuck");
            }
        }
        System.out.println("功能测试结束");

//        len = 22;
//        System.out.println("性能测试开始，数据规模 : " + len);
//        int[][] matrix = new int[len][len];
//        for (int i = 0; i < len; i++) {
//            for (int j = 0; j < len; j++) {
//                matrix[i][j] = (int) (Math.random() * value) + 1;
//            }
//        }
//        for (int i = 0; i < len; i++) {
//            matrix[i][i] = 0;
//        }
//        long start;
//        long end;
//        start = System.currentTimeMillis();
//        t4(matrix);
//        end = System.currentTimeMillis();
//        System.out.println("运行时间 : " + (end - start) + " 毫秒");
//        System.out.println("性能测试结束");

    }

}
