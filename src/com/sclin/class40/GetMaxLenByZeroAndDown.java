package com.sclin.class40;

import java.util.HashMap;
import java.util.Map;

import static class40.Code02_LongestSumSubArrayLength.*;

public class GetMaxLenByZeroAndDown {

    // 可能存在负数
    public static int method1(int[] arr, int k) {
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int curSum = 0;
        map.put(0, -1);
        for (int i = 0; i < arr.length; i++) {
            curSum += arr[i];
            if (map.containsKey(curSum - k)) {
                res = Math.max(res, i - map.get(curSum - k));
            }
            if (!map.containsKey(curSum)) {
                map.put(curSum, i);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int len = 10;
        int value = 10;
        int testTime = 500000;

//        int[] arr = {2,3,-6,5,-6};
//        int K = 2;
//        int ans1 = method1(arr, K);
//        System.out.println(ans1);


        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(len, value);
            int K = (int) (Math.random() * value) - (int) (Math.random() * value);
            int ans1 = method1(arr, K);
            int ans2 = right(arr, K);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println("K : " + K);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("test end");

    }

}
