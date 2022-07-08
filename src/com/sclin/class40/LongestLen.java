package com.sclin.class40;

import static class40.Code03_LongestLessSumSubArrayLength.generateRandomArray;
import static class40.Code03_LongestLessSumSubArrayLength.maxLengthAwesome;

public class LongestLen {

    // 返回，小于等于k的子数组最长长度
    public static int method1(int[] arr, int k){
        int res = 0;
        int[] sumArr = new int[arr.length];
        sumArr[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sumArr[i] = sumArr[i - 1] + arr[i];
        }
        // 必须以i位置结尾，最长长度是多少
        for (int i = arr.length - 1; i >= 0; i--) {
            int cur = sumArr[i];
            if (cur <= k) {
                res = Math.max(res, i + 1);

                break;
            } else {
                for (int j = 0; j < i; j++) {
                    if (sumArr[i] - sumArr[j] <= k) {
                        res = Math.max(res, i - j);
                        break;
                    }
                }
            }
        }
        return res;
    }

    public static int method2(int[] arr, int k){
        int res = 0;
        int[] minSum = new int[arr.length];
        int[] minSumEnds = new int[arr.length];
        minSum[arr.length - 1] = arr[arr.length - 1];
        minSumEnds[arr.length - 1] = arr.length - 1;
        for (int i = arr.length - 2; i >= 0; i--) {
            int index = i;
            int sum = arr[i];
            if (minSum[i + 1] <= 0) {
                sum += minSum[i + 1];
                index = minSumEnds[i + 1];
            }
            minSum[i] = sum;
            minSumEnds[i] = index;
        }
        int end = 0;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            while (end < arr.length && sum + minSum[end] <= k) {
                sum += minSum[end];
                end = minSumEnds[end] + 1;
            }
            res = Math.max(res, end - i);
            if (end > i) {
                sum -= arr[i];
            } else {
                end = i + 1;
            }
        }

        return res;
    }


    public static void main(String[] args) {
//        int[] arr = {1,4,-2,4,-1,2};
//        int k = 2;
//        System.out.println(method2(arr, k));


        System.out.println("test begin");
        for (int i = 0; i < 10000000; i++) {
            int[] arr = generateRandomArray(10, 20);
            int k = (int) (Math.random() * 20) - 5;
            if (maxLengthAwesome(arr, k) != method2(arr, k)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }

}
