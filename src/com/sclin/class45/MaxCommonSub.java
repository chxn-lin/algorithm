package com.sclin.class45;

import static class45.Code03_LongestCommonSubstringConquerByHeight.*;

public class MaxCommonSub {

    public static int method1(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() < 1 || s2.length() < 1) {
            return 0;
        }
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        // s1必须以i位置结尾，s2必须以j位置结尾，最长的公共子串长度为多少
        int[][] dp = new int[chars1.length][chars2.length];
        int maxLen = 0;
        for (int i = 0; i < chars1.length; i++) {
            dp[i][0] = chars1[i] == chars2[0] ? 1 : 0;
            maxLen = Math.max(dp[i][0], maxLen);
        }
        for (int i = 1; i < chars2.length; i++) {
            dp[0][i] = chars2[i] == chars1[0] ? 1 : 0;
            maxLen = Math.max(dp[0][i], maxLen);
        }

        for (int i = 1; i < chars1.length; i++) {
            for (int j = 1; j < chars2.length; j++) {
                if (chars1[i] == chars2[j]) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    maxLen = Math.max(dp[i][j], maxLen);
                }
            }
        }

        return maxLen;
    }

    public static int method2(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() < 1 || s2.length() < 1) {
            return 0;
        }
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < chars1.length; i++) {
            max = Math.max(chars1[i], max);
            min = Math.min(chars1[i], min);
        }
        for (int i = 0; i < chars2.length; i++) {
            max = Math.max(chars2[i], max);
            min = Math.min(chars2[i], min);
        }
        int[] all = new int[chars1.length + chars2.length + 1];
        int index = 0;
        for (int i = 0; i < chars1.length; i++) {
            all[index++] = chars1[i] - min + 2;
        }
        all[index++] = 1;
        for (int i = 0; i < chars2.length; i++) {
            all[index++] = chars2[i] - min + 2;
        }
        DC3 dc3 = new DC3(all, max - min + 2);
        int[] sa = dc3.sa;
        int[] height = dc3.height;
        int res = 0;
        for (int i = 1; i < all.length; i++) {
            int sa1 = sa[i];
            int sa2 = sa[i - 1];
            if (Math.max(sa1, sa2) > chars1.length && Math.min(sa1, sa2) < chars1.length) {
                res = Math.max(res, height[i]);
            }
        }
        return res;
    }

    public static void main(String[] args) {
//        String s1 = "decabc";
//        String s2 = "e";
//        System.out.println(method1(s1, s2));

        int len = 30;
        int range = 5;
        int testTime = 100000;
        System.out.println("功能测试开始");
        for (int i = 0; i < testTime; i++) {
            int N1 = (int) (Math.random() * len);
            int N2 = (int) (Math.random() * len);
            String str1 = randomNumberString(N1, range);
            String str2 = randomNumberString(N2, range);
            int ans1 = method2(str1, str2);
            int ans2 = lcs2(str1, str2);
            if (ans1 != ans2) {
                System.out.println("s1:" + str1 + "; s2:" + str2);
                System.out.println(" my :" + ans1);
                System.out.println("true:" + ans2);
                System.out.println("Oops!");
            }
        }
        System.out.println("功能测试结束");
        System.out.println("==========");
//
//        System.out.println("性能测试开始");
//        len = 80000;
//        range = 26;
//        long start;
//        long end;
//
//        String str1 = randomNumberString(len, range);
//        String str2 = randomNumberString(len, range);
//
//        start = System.currentTimeMillis();
//        int ans1 = lcs1(str1, str2);
//        end = System.currentTimeMillis();
//        System.out.println("方法1结果 : " + ans1 + " , 运行时间 : " + (end - start) + " ms");
//
//        start = System.currentTimeMillis();
//        int ans2 = lcs2(str1, str2);
//        end = System.currentTimeMillis();
//        System.out.println("方法2结果 : " + ans2 + " , 运行时间 : " + (end - start) + " ms");
//
//        System.out.println("性能测试结束");

    }

}
