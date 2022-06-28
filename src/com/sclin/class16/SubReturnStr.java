package com.sclin.class16;

public class SubReturnStr {

    public static void main(String[] args) {
        String str = "bbbab";
        System.out.println(new SubReturnStr().longestPalindromeSubseq(str));
    }

    public int longestPalindromeSubseq(String s) {
        return lpsl2(s);
    }

    // 给定一个字符串str，返回这个字符串的最长回文（对称）子序列长度
    public static int lpsl1(String s) {
        if (s == null || "".equals(s)) {
            return 0;
        }
        char[] chars = s.toCharArray();
        return process1(chars, 0, chars.length - 1);
    }

    private static int process1(char[] arr, int L, int R) {
        if (L == R) {
            return 1;
        }
        if (L > R) {
            return 0;
        }
        int result = 0;

        int p1 = process1(arr, L + 1, R);
        int p2 = process1(arr, L, R - 1);
        int p3 = arr[L] == arr[R] ? 2 + process1(arr, L + 1, R - 1) : 0;
        result = Math.max(p1, Math.max(p2, p3));

        return result;
    }

    // 给定一个字符串str，返回这个字符串的最长回文（对称）子序列长度
    public static int lpsl2(String s) {
        if (s == null || "".equals(s)) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        char[] arr = s.toCharArray();
        int size = s.length();
        int[][] dp = new int[size][size];

        for (int i = 0; i < size; i++) {
            dp[i][i] = 1;
        }

        for (int L = size - 2; L >= 0; L--) {
            for (int R = L + 1; R < size; R++) {
                int p1 = dp[L + 1][R];
                int p2 = dp[L][R - 1];
                int p3 = 0;
                if (arr[L] == arr[R]) {
                    p3 = 2 + dp[L + 1][R - 1];
                }
                dp[L][R] = Math.max(p1, Math.max(p2, p3));
            }
        }
        return dp[0][size - 1];
    }

}
