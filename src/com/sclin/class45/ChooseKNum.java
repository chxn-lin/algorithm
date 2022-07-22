package com.sclin.class45;

import class44.DC3;

import static class45.Code02_CreateMaximumNumber.maxNumber2;
import static com.sclin.class01.KM1.printArr;

public class ChooseKNum {

    public static int[] maxNumber1(int[] nums1, int[] nums2, int k) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        if (k < 0 || k > len1 + len2) {
            return null;
        }
        int[][] dp1 = genDp(nums1, k);
        int[][] dp2 = genDp(nums2, k);
        int[] ans = new int[k];
        // dp1取i个数字
        for (int i = Math.max(0, k - len2); i <= Math.min(k, len1); i++) {
            int[] p1 = getMaxNum(nums1, dp1, i);
            int[] p2 = getMaxNum(nums2, dp2, k - i);
            int[] merge = mergeArr(p1, p2);
            ans = firstIsBig(merge, ans) ? merge : ans;
        }
        return ans;
    }

    private static boolean firstIsBig(int[] merge, int[] ans) {
        boolean res = true;
        for (int i = 0; i < merge.length; i++) {
            if (merge[i] < ans[i]) {
                res = false;
                break;
            }
        }
        return res;
    }

    private static int[] mergeArr(int[] p1, int[] p2) {
        int[] ans = new int[p1.length + p2.length];

        // 开始dc3的转换
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < p1.length; i++) {
            max = Math.max(max, p1[i]);
            min = Math.min(min, p1[i]);
        }
        for (int i = 0; i < p2.length; i++) {
            max = Math.max(max, p2[i]);
            min = Math.min(min, p2[i]);
        }
        int[] all = new int[p1.length + p2.length + 1];
        int index = 0;
        for (int i = 0; i < p1.length; i++) {
            all[index++] = p1[i] - min + 2;
        }
        all[index++] = 1;
        for (int i = 0; i < p2.length; i++) {
            all[index++] = p2[i] - min + 2;
        }
        DC3 dc3 = new DC3(all, max - min + 2);
        int[] sa = dc3.rank;
        int p1Index = 0;
        int p2Index = 0;
        for (int i = 0; i < ans.length; i++) {
            if (p1Index == p1.length) {
                ans[i] = p2[p2Index++];
            } else if (p2Index == p2.length) {
                ans[i] = p1[p1Index++];
            } else {
                if (sa[p1Index] > sa[p2Index + p1.length + 1]) {
                    ans[i] = p1[p1Index++];
                } else {
                    ans[i] = p2[p2Index++];
                }
            }
        }
        return ans;
    }

    private static int[] getMaxNum(int[] arr, int[][] dp, int k) {
        int[] res = new int[k];
        for (int i = 0; i < res.length;) {
            res[i] = arr[dp[i][k]];
            i = dp[i][k] + 1;
            k--;
        }
        return res;
    }

    private static int[][] genDp(int[] arr, int k) {
        int[][] dp = new int[arr.length][k + 1];

        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = -1;
        }
        for (int i = 1; i < k + 1; i++) {
            for (int j = dp.length - 1; j >= 0; j--) {
                if (dp.length - j == i) {
                    dp[j][i] = j;
                } else if (dp.length - j < i) {
                    dp[j][i] = -1;
                } else {
                    dp[j][i] = arr[dp[j + 1][i]] > arr[j] ? dp[j + 1][i] : j;
                }
            }
        }
        return dp;
    }


    public static void main(String[] args) {
        int[] nums1 = {9,9,3};
        int[] nums2 = {8,9,2};
        int k = 4;
        int[] ints2 = maxNumber2(nums1, nums2, k);
        printArr(ints2);
        int[] ints = maxNumber1(nums1, nums2, k);
        printArr(ints);
    }


}
