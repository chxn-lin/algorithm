package com.sclin.class46;

import class47.Code01_StrangePrinter;

public class PrintMachine {

    public static int method1(String str){
        if (str == null || str.length() < 1) {
            return 0;
        }
        return process1(str.toCharArray(), 0, str.length() - 1);
    }

    private static int process1(char[] arr, int L, int R) {
        if (L == R) {
            return 1;
        }
        int res = R - L + 1;// 最差情况，每个字符都刷一遍
        for (int i = L + 1; i <= R; i++) {
            res = Math.min(res, process1(arr, L, i - 1)
                    + process1(arr, i, R)
                    - (arr[L] == arr[i] ? 1 : 0));
        }
        return res;
    }

    public static int method2(String str){
        if (str == null || str.length() < 1) {
            return 0;
        }
        char[] arr = str.toCharArray();
        int[][] dp = new int[arr.length][arr.length];
        for (int i = 0; i < arr.length; i++) {
            dp[i][i] = 1;
        }
        for (int L = 0; L < arr.length; L++) {
            for (int R = L + 1; R < arr.length; R++) {
                int res = R - L + 1;// 最差情况，每个字符都刷一遍
                for (int i = L + 1; i <= R; i++) {
                    res = Math.min(res, process1(arr, L, i - 1)
                            + process1(arr, i, R)
                            - (arr[L] == arr[i] ? 1 : 0));
                }
                dp[L][R] = res;
            }
        }

        return dp[0][arr.length - 1];
    }

    public static void main(String[] args) {
        String str = "aabdbbsdjjsdddad";
        System.out.println(method2(str));
        System.out.println(Code01_StrangePrinter.strangePrinter1(str));
    }

}
