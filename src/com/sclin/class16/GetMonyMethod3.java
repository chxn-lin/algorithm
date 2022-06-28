package com.sclin.class16;


import java.util.HashMap;
import java.util.Map;

import static class21.Code04_CoinsWaySameValueSamePapper.*;

public class GetMonyMethod3 {

//    arr是货币数组，其中的值都是正数。再给定一个正数aim，
//    每个值都认为是一张货币，
//    认为值相同的货币没有任何不同，
//    返回组成aim的方法数（表示给的是有限几张）

    public static int method1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }
        int[] newArr = new int[map.entrySet().size()];
        int[] numArr = new int[map.entrySet().size()];
        int index = 0;
        for (Map.Entry<Integer, Integer> en : map.entrySet()) {
            newArr[index] = en.getKey();
            numArr[index] = en.getValue();
            index++;
        }

        return process(newArr, numArr, 0, aim);
    }

    private static int process(int[] arr, int[] numArr, int index, int rest) {
        if (arr.length == index) {
            return rest == 0 ? 1 : 0;
        }
        int result = 0;

        for (int i = 0; i * arr[index] <= rest && i <= numArr[index]; i++) {
            result += process(arr, numArr, index + 1, rest - (i * arr[index]));
        }

        return result;
    }

    public static int method2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }
        int[] newArr = new int[map.entrySet().size()];
        int[] numArr = new int[map.entrySet().size()];
        int index = 0;
        for (Map.Entry<Integer, Integer> en : map.entrySet()) {
            newArr[index] = en.getKey();
            numArr[index] = en.getValue();
            index++;
        }
        arr = newArr;

        int[][] dp = new int[arr.length + 1][aim + 1];

        dp[arr.length][0] = 1;
        for (index = arr.length - 1; index >= 0; index--) {
            for (int rest = 0; rest < aim + 1; rest++) {
                int result = 0;
                for (int zhang = 0; zhang * arr[index] <= rest && zhang <= numArr[index]; zhang++) {
                    result += dp[index + 1][rest - (zhang * arr[index])];
                }
                dp[index][rest] = result;
            }
        }
        return dp[0][aim];
    }

    public static int method3(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }
        int[] newArr = new int[map.entrySet().size()];
        int[] numArr = new int[map.entrySet().size()];
        int index = 0;
        for (Map.Entry<Integer, Integer> en : map.entrySet()) {
            newArr[index] = en.getKey();
            numArr[index] = en.getValue();
            index++;
        }
        arr = newArr;

        int[][] dp = new int[arr.length + 1][aim + 1];

        dp[arr.length][0] = 1;
        for (index = arr.length - 1; index >= 0; index--) {
            for (int rest = 0; rest < aim + 1; rest++) {
                int result = dp[index + 1][rest];

                if (rest - arr[index] >= 0) {
                    result += dp[index][rest - arr[index]];
                }
                if (rest - arr[index] * (numArr[index] + 1) >= 0) {
                    result -= dp[index + 1][rest - arr[index] * (numArr[index] + 1)];
                }

                dp[index][rest] = result;
            }
        }
        return dp[0][aim];
    }

    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue = 20;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = method3(arr, aim);
            int ans3 = dp2(arr, aim);
            if (ans1 != ans3) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans3);
                break;
            }
        }
        System.out.println("测试结束");
    }

}
