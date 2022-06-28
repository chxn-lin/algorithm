package com.sclin.class18;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static class24.Code04_MinCoinsOnePaper.*;

public class MinCoins {

    // 返回最小张数
    public static int method1(int[] arr, int aim) {
        if (aim == 0) {
            return 0;
        }
        if (arr == null || arr.length < 1 || aim < 0) {
            return Integer.MAX_VALUE;
        }

        return process1(arr, 0, aim);
    }

    private static int process1(int[] arr, int index, int aim) {
        if (index == arr.length) {
            return aim == 0 ? 0 : Integer.MAX_VALUE;
        }
        int p1 = process1(arr, index + 1, aim);
        int p2 = process1(arr, index + 1, aim - arr[index]);
        if (p2 != Integer.MAX_VALUE) {
            p2++;
        }

        return Math.min(p1, p2);
    }

    // 返回最小张数
    public static int method1_1(int[] arr, int aim) {
        if (aim == 0) {
            return 0;
        }
        if (arr == null || arr.length < 1 || aim < 0) {
            return Integer.MAX_VALUE;
        }

        int[][] dp = new int[arr.length + 1][aim + 1];
        for (int rest = 1; rest < aim + 1; rest++) {
            dp[arr.length][rest] = Integer.MAX_VALUE;
        }
        for (int index = arr.length - 1; index >= 0; index--) {
            for (int rest = 0; rest < aim + 1; rest++) {
                int p1 = dp[index + 1][rest];
                int p2 = Integer.MAX_VALUE;
                if (rest - arr[index] >= 0) {
                    p2 = dp[index + 1][rest - arr[index]];
                }
                if (p2 != Integer.MAX_VALUE) {
                    p2++;
                }
                dp[index][rest] = Math.min(p1, p2);
            }
        }

//        ;
        return dp[0][aim];
    }

    // 返回最小张数
    public static int method2(int[] arr, int aim) {
        if (aim == 0) {
            return 0;
        }
        if (arr == null || arr.length < 1 || aim < 0) {
            return Integer.MAX_VALUE;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }
        int[] monyArr = new int[map.keySet().size()];
        int[] numArr = new int[monyArr.length];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            monyArr[index] = entry.getKey();
            numArr[index] = entry.getValue();
            index++;
        }


        return process2(monyArr, numArr, 0, aim);
    }

    private static int process2(int[] arr, int[] numArr, int index, int rest) {
        if (index == arr.length) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        }
        int result = Integer.MAX_VALUE;
        for (int num = 0; num <= numArr[index] && rest - arr[index] * num >= 0; num++) {
            int p1 = process2(arr, numArr, index + 1, rest - arr[index] * num);
            if (p1 != Integer.MAX_VALUE) {
                result = Math.min(result, p1 + num);
            }
        }

        return result;
    }

    // 返回最小张数
    public static int method3(int[] oldArr, int aim) {
        if (aim == 0) {
            return 0;
        }
        if (oldArr == null || oldArr.length < 1 || aim < 0) {
            return Integer.MAX_VALUE;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : oldArr) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }
        int[] arr = new int[map.keySet().size()];
        int[] numArr = new int[arr.length];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            arr[index] = entry.getKey();
            numArr[index] = entry.getValue();
            index++;
        }

        int[][] dp = new int[arr.length + 1][aim + 1];

        for (int rest = 1; rest < aim + 1; rest++) {
            dp[arr.length][rest] = Integer.MAX_VALUE;
        }
        for (index = arr.length - 1; index >= 0; index--) {
            for (int rest = 0; rest < aim + 1; rest++) {
                int result = Integer.MAX_VALUE;
                for (int num = 0; num <= numArr[index] && rest - arr[index] * num >= 0; num++) {
                    int p1 = dp[index + 1][rest - arr[index] * num];
                    if (p1 != Integer.MAX_VALUE) {
                        result = Math.min(result, p1 + num);
                    }
                }
                dp[index][rest] = result;
            }
        }
//        ;

        return dp[0][aim];
    }

    // 返回最小张数
    public static int method4(int[] oldArr, int aim) {
        if (aim == 0) {
            return 0;
        }
        if (oldArr == null || oldArr.length < 1 || aim < 0) {
            return Integer.MAX_VALUE;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : oldArr) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }
        int[] arr = new int[map.keySet().size()];
        int[] numArr = new int[arr.length];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            arr[index] = entry.getKey();
            numArr[index] = entry.getValue();
            index++;
        }

        int[][] dp = new int[arr.length + 1][aim + 1];

        for (int rest = 1; rest < aim + 1; rest++) {
            dp[arr.length][rest] = Integer.MAX_VALUE;
        }
        LinkedList<Integer> qMin = null;// 窗口内最小值
        for (index = arr.length - 1; index >= 0; index--) {
            // 面值起始的循环 0~3， 1~4
            for (int i = 0; i < Math.min(arr[index], aim + 1); i++) {
                qMin = new LinkedList<>();
                for (int rest = i; rest <= aim; rest += arr[index]) {
                    int cur = dp[index + 1][rest];
                    dp[index][rest] = cur;
                    while (!qMin.isEmpty() && (dp[index+1][qMin.peekLast()]== Integer.MAX_VALUE
                            || dp[index][qMin.peekLast()] + (rest - qMin.peekLast())/arr[index] >= cur
                    )) {
                        qMin.pollLast();
                    }
                    qMin.add(rest);
                    // 去掉之前的
                    if ((rest - qMin.peekFirst())/arr[index] - 1 == numArr[index]) {
                        qMin.pollFirst();
                    }
                    dp[index][rest] = dp[index + 1][qMin.peekFirst()] + (rest - qMin.peekFirst())/arr[index];
                }
            }
        }
//        ;

        return dp[0][aim];
    }

    // 为了测试
    public static void main(String[] args) {
//        int[] arr = {3,5,3,8,1,3};
//        int aim = 10;
//        System.out.println("true:"+dp3(arr, aim));
//        System.out.println();
//        System.out.println("=============");
//        System.out.println();
//        System.out.println(method4(arr, aim));

        int maxLen = 300;
        int maxValue = 30;
        int testTime = 1000000;
        System.out.println("功能测试开始");
//        for (int i = 0; i < testTime; i++) {
//            int N = (int) (Math.random() * maxLen);
//            int[] arr = randomArray(N, maxValue);
//            int aim = 10;
//            int ans1 = method4(arr, aim);
//            int ans4 = method1_1(arr, aim);
//            if (ans1 != ans4) {
//                System.out.println("Oops!");
//                printArray(arr);
//                System.out.println("aim :" + aim);
//                System.out.println("my  :" + ans1);
//                System.out.println("true:" + ans4);
//                break;
//            }
//        }
        System.out.println("功能测试结束");

        System.out.println("==========性能测试");
        System.out.println("method1_1");
        long l = System.currentTimeMillis();
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * maxLen);
            int[] arr = randomArray(N, maxValue);
            int aim = (int) (Math.random() * maxValue);
            method1_1(arr, aim);
        }
        long end = System.currentTimeMillis();
        System.out.println("method1_1时间：" + (end - l));

        System.out.println("method3===============");
        l = System.currentTimeMillis();
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * maxLen);
            int[] arr = randomArray(N, maxValue);
            int aim = (int) (Math.random() * maxValue);
            method3(arr, aim);
        }
        end = System.currentTimeMillis();
        System.out.println("method3时间：" + (end - l));

        System.out.println("method4===============");
        l = System.currentTimeMillis();
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * maxLen);
            int[] arr = randomArray(N, maxValue);
            int aim = (int) (Math.random() * maxValue);
            method4(arr, aim);
        }
        end = System.currentTimeMillis();
        System.out.println("method4时间：" + (end - l));
    }

    public static void printArr(int[][] dp) {
        System.out.println("{");

        for (int[] ints : dp) {
            System.out.print("{");
            for (int anInt : ints) {
                if (anInt != Integer.MAX_VALUE) {
                    System.out.print(anInt + ", ");
                } else {
                    System.out.print("-, ");
                }
            }
            System.out.print("},");
            System.out.println();
        }
        System.out.println("}");
    }

}
