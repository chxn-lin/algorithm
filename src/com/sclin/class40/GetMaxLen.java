package com.sclin.class40;

import static class40.Code01_LongestSumSubArrayLengthInPositiveArray.*;

public class GetMaxLen {

//    给定一个正整数组成的无序数组arr，给定一个正整数K，
//    找到arr的所有子数组里，哪个子数组的累加和等于K，并且是长度最大的，
//    返回其长度。

    public static int method1(int[] arr, int k) {
        int res = 0;
        int[] sumArr = new int[arr.length];
        sumArr[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sumArr[i] = sumArr[i - 1] + arr[i];
        }
        // 必须以i位置结尾的，存在累加和等于K的长度
        for (int i = sumArr.length - 1; i >= 0; i--) {
            if (sumArr[i] == k) {
                res = Math.max(i + 1, res);
                break;
            }
            for (int j = 0; j <= i; j++) {
                if (sumArr[i] - sumArr[j] == k) {
                    res = Math.max(res, i - j);
                    break;
                }
            }
        }
        return res;
    }

    public static int method2(int[] arr, int k) {
        int res = 0;
        int curSum = arr[0];
        int L = 0;
        int R = 0;
        while (R < arr.length) {
            if (curSum == k) {
                res = Math.max(res, R - L + 1);
                R++;
                if (R == arr.length) {
                    break;
                }
                curSum += arr[R];
            } else if (curSum > k) {
                curSum -= arr[L++];
            } else {
                R++;
                if (R == arr.length) {
                    break;
                }
                curSum += arr[R];
            }
        }

        return res;
    }

    public static void main(String[] args) {
//        int[] arr = {40, 24, 85, 61, 7};
//        int K = 29;
//        int ans1 = method2(arr, K);

        int len = 5;
        int value = 100;
        int testTime = 500000;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generatePositiveArray(len, value);
            int K = (int) (Math.random() * value) + 1;
//            printArray(arr);
//            System.out.println(K);
            int ans1 = method2(arr, K);
            int ans2 = right(arr, K);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println("K : " + K);
                System.out.println("my : " + ans1);
                System.out.println("true : " + ans2);
                break;
            }
        }
        System.out.println("test end");
    }


}
