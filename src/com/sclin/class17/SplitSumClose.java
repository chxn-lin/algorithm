package com.sclin.class17;

import java.util.Arrays;

import static class23.Code02_SplitSumClosedSizeHalf.*;

public class SplitSumClose {

    /*
    给定一个正数数组arr，请把arr中所有的数分成两个集合，
    如果arr长度为偶数，两个集合包含数的个数要一样多，
    如果arr长度为奇数，两个集合包含数的个数必须只差一个，
    请尽量让两个集合的累加和接近，返回：
    最接近的情况下，较小集合的累加和
     */
    public static int method1(int[] arr){
        if (arr == null || arr.length < 1) {
            return 0;
        }
        Arrays.sort(arr);
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        int p1 = process(arr, 0, sum/2, arr.length/2);
        if (arr.length%2 == 1) {
            p1 = Math.max(process(arr, 0, sum/2, arr.length/2 + 1), p1);
        }
        return p1;
    }

    private static int process(int[] arr, int index, int rest, int pickNum) {
        if (index == arr.length) {
            return pickNum == 0 ? 0 : -1;
        }
        // 选sum的位置
        int p1 = process(arr, index + 1, rest, pickNum);
        int p2 = -1;
        if (rest - arr[index] >= 0) {
            p2 = process(arr, index + 1, rest - arr[index], pickNum - 1);
            if (p2 != -1) {
                p2 += arr[index];
            } else {
                p2 = -1;
            }
        }
        return Math.max(p1, p2);
    }

    public static int method2(int[] arr){
        if (arr == null || arr.length < 1) {
            return 0;
        }
        Arrays.sort(arr);
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }

        int[][][] dp = new int[arr.length + 1][sum/2 + 1][(arr.length + 1)/2 + 1];

        for (int rest = 0; rest < sum/2 + 1; rest++) {
            for (int pickNum = 0; pickNum < (arr.length + 1)/2 + 1; pickNum++) {
                dp[arr.length][rest][pickNum] = -1;
            }
        }
        for (int i = 0; i < sum/2 + 1; i++) {
            dp[arr.length][i][0] = 0;
        }
        for (int index = arr.length - 1; index >= 0; index--) {
            for (int rest = 0; rest < sum/2 + 1; rest++) {
                for (int pickNum = 0; pickNum < (arr.length + 1)/2 + 1; pickNum++) {

                    // 选sum的位置
                    int p1 = dp[index + 1][rest][pickNum];
                    int p2 = -1;
                    if (rest - arr[index] >= 0 && pickNum - 1 >= 0) {
                        p2 = dp[index + 1][rest - arr[index]][pickNum - 1];
                        if (p2 != -1) {
                            p2 += arr[index];
                        } else {
                            p2 = -1;
                        }
                    }
                    dp[index][rest][pickNum] = Math.max(p1, p2);
                }
            }

        }


        int p1 = dp[0][sum/2][arr.length/2];
        if (arr.length%2 == 1) {
            p1 = Math.max(dp[0][sum/2][arr.length/2 + 1], p1);
        }
        return p1;
    }

    // for test
    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue = 50;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * maxLen);
            int[] arr = randomArray(len, maxValue);
            int ans1 = method2(arr);
            int ans3 = dp2(arr);
            if (ans1 != ans3) {
                printArray(arr);
                System.out.println("my:" + ans1);
                System.out.println("true:" + ans3);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }

}
