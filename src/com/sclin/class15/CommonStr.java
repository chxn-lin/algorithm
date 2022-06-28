package com.sclin.class15;

public class CommonStr {

    public static void main(String[] args) {
        String str1 = "abcde";
        String str2 = "ace";
        System.out.println(new CommonStr().longestCommonSubsequence(str1, str2));
    }

    /*
    求最长公共子序列
    例：str1 = "a12bc345def"
    str2 = "MNP123QRS45Z"
    最长公共子序列为 12345，返回5*/
    public int longestCommonSubsequence(String text1, String text2) {
        return method2(text1, text2);
    }

    private int method1(String str1, String str2) {
        char[] arr1 = str1.toCharArray();
        char[] arr2 = str2.toCharArray();
        return process1(arr1, arr2, arr1.length - 1, arr2.length - 1);
    }

    // 暴力递归，arr1从0~i,arr2从0~j,最长公共子序列的长度
    private int process1(char[] arr1, char[] arr2, int i, int j){
        if (i == 0 && j == 0) {
            return arr1[i] == arr2[j] ? 1 : 0;
        }
        int result = 0;
        if (i == 0) {
            if (arr1[i] == arr2[j]) {
                result = 1;
            } else {
                result = process1(arr1, arr2, i, j - 1);
            }
        } else if (j == 0) {
            if (arr1[i] == arr2[j]) {
                result = 1;
            } else {
                result = process1(arr1, arr2, i - 1, j);
            }
        } else {
            int p1 = process1(arr1, arr2, i - 1, j - 1);
            int p2 = process1(arr1, arr2, i, j - 1);
            int p3 = process1(arr1, arr2, i - 1, j);
            if (arr1[i] == arr2[j]) {
                p1 += 1;
            }
            result = Math.max(p1, Math.max(p2, p3));
        }

        return result;
    }

    private int method2(String str1, String str2) {
        char[] arr1 = str1.toCharArray();
        char[] arr2 = str2.toCharArray();
        int[][] dp = new int[arr1.length][arr2.length];

        dp[0][0] = arr1[0] == arr2[0] ? 1 : 0;
        for (int j = 1; j < arr2.length; j++) {
            dp[0][j] = arr1[0] == arr2[j] ? 1 : dp[0][j - 1];
        }
        for (int i = 1; i < arr1.length; i++) {
            dp[i][0] = arr1[i] == arr2[0] ? 1 : dp[i - 1][0];
        }

        for (int i = 1; i < arr1.length; i++) {
            for (int j = 1; j < arr2.length; j++) {
                int p1 = dp[i - 1][j - 1];
                int p2 = dp[i][j - 1];
                int p3 = dp[i - 1][j];
                if (arr1[i] == arr2[j]) {
                    p1 += 1;
                }
                dp[i][j] = Math.max(p1, Math.max(p2, p3));
            }
        }
        return dp[arr1.length - 1][arr2.length - 1];
    }
}
