package com.sclin.class39;

import java.util.Iterator;
import java.util.TreeSet;

import static class39.Code01_SubsquenceMaxModM.generateRandomArray;
import static class39.Code01_SubsquenceMaxModM.max4;

public class SubMMax {

//    给定一个非负数组arr，和一个正数m，
//    返回arr的所有子序列（自由组合）中，累加和%m之后的最大值。
    public static int method1(int[] arr, int m){
        int res = 0;
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        for (int i = 0; i < sum + 1; i++) {
            if (process1(arr, m, arr.length - 1, i)) {
                res = Math.max(res, i % m);
            }
        }
        return res;
    }

    public static int dp1(int[] arr, int m){
        int res = 0;
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }

        boolean[][] dp = new boolean[arr.length + 1][sum + 1];
        for (int j = 0; j < sum + 1; j++) {
            dp[0][j] = j == arr[0] || j == 0;
        }
        for (int index = 1; index < arr.length; index++) {
            for (int j = 0; j < sum + 1; j++) {
                boolean p1 = false;
                if (j - arr[index] >= 0) {
                    p1 = dp[index - 1][j - arr[index]];
                }
                boolean p2 = dp[index - 1][j];
                dp[index][j] = p1 || p2;
            }
        }

        for (int i = 0; i < sum + 1; i++) {
            if (dp[arr.length - 1][i]) {
                res = Math.max(res, i % m);
            }
        }
        return res;
    }

    private static boolean process1(int[] arr, int m, int index, int j){
        if (index == -1) {
            return j == 0;
        }
        // 选择要这个数字
        boolean p1 = process1(arr, m, index - 1, j - arr[index]);
        boolean p2 = process1(arr, m, index - 1, j);
        return p1 || p2;
    }

    public static int method2(int[] arr, int m){
        int res = 0;

        for (int i = 0; i < m; i++) {
            if (process2(arr, m, arr.length - 1, i)) {
                res = Math.max(res, i);
            }
        }
        return res;
    }

    // j为余数
    private static boolean process2(int[] arr, int m, int index, int j){
        if (index == -1) {
            return j == 0;
        }
        // 选择要这个数字
        boolean p1 = false;
        if (arr[index]%m <= j) {
            p1 = process2(arr, m, index - 1, j - arr[index]%m);
        } else {
            p1 |= process2(arr, m, index - 1, m + j - arr[index]%m);
        }
        // 不要这个数字
        boolean p2 = process2(arr, m, index - 1, j);
        return p1 || p2;
    }

    public static int method3(int[] arr, int m){
        int res = 0;
        int mid = arr.length >> 1;
        TreeSet<Integer> leftTree = new TreeSet<>();
        process3(arr, m, 0, mid, 0, 0, leftTree);
        TreeSet<Integer> rightTree = new TreeSet<>();
        process3(arr, m, mid + 1, arr.length - 1, mid + 1, 0, rightTree);

        for (Integer leftMod : leftTree) {
            res = Math.max(res, leftMod + rightTree.floor(m - 1 - leftMod));
        }
        return res;
    }

    private static void process3(int[] arr, int m, int start, int end, int index, int sum, TreeSet<Integer> set) {
        if (index == end + 1) {
            set.add(sum%m);
        } else {
            process3(arr, m, start, end, index + 1, sum + arr[index], set);
            process3(arr, m, start, end, index + 1, sum, set);
        }
    }


    public static void main(String[] args) {
        int len = 10;
        int value = 100;
        int m = 76;
        int testTime = 100000;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(len, value);
            int ans1 = method3(arr, m);
            int ans4 = max4(arr, m);
            if (ans1 != ans4) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish!");

    }

}
