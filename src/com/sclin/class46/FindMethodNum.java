package com.sclin.class46;

import static class47.Code02_RestoreWays.generateRandomArray;
import static class47.Code02_RestoreWays.ways3;

public class FindMethodNum {

    public static int method1(int[] arr){
        int res = 0;
        if (arr[arr.length - 1] != 0) {
            res = process1(arr, arr.length - 1, arr[arr.length - 1], 2);
        } else {
            for (int i = 1; i <= 200; i++) {
                res += process1(arr, arr.length - 1, i, 2);
            }
        }
        return res;
    }

    // s==0，代表arr[i] < arr[i+1] 右大
    // s==1，代表arr[i] == arr[i+1] 右=当前
    // s==2，代表arr[i] > arr[i+1] 右小
    private static int process1(int[] arr, int index, int value, int s) {
        if (index == 0) {
            return (s == 1 || s== 0) && (arr[index] == 0 || arr[index] == value) ? 1 : 0;
        }
        if (arr[index] != 0 && arr[index] != value) {
            return 0;
        }
        int res = 0;
        if (s == 0 || s == 1) {
            for (int i = 1; i < 201; i++) {
                res += process1(arr, index - 1, i, i < value ? 0 : i == value ? 1 : 2);
            }
        } else if (s == 2) {
            res+= process1(arr, index - 1, value, 1);
            for (int i = value + 1; i < 201; i++) {
                res+= process1(arr, index - 1, i, 2);
            }
        }
        return res;
    }

    public static int method2(int[] arr){
        int[][][] dp = new int[arr.length][201][3];

        for (int i = 0; i < 201; i++) {
            for (int j = 0; j < 2; j++) {
                dp[0][i][j] = (arr[0] == 0 || arr[0] == i) ? 1 : 0;
            }
        }

        for (int index = 1; index < arr.length; index++) {
            for (int value = 1; value < 201; value++) {
                for (int s = 0; s < 3; s++) {
                    if (arr[index] != 0 && arr[index] != value) {
                        continue;
                    }
                    int res = 0;
                    if (s == 0 || s == 1) {
                        for (int i = 1; i < 201; i++) {
                            res += dp[index - 1][i][i < value ? 0 : i == value ? 1 : 2];
                        }
                    } else if (s == 2) {
                        res+= dp[index - 1][value][1];
                        for (int i = value + 1; i < 201; i++) {
                            res+= dp[index - 1][i][2];
                        }
                    }
                    dp[index][value][s] = res;
                }
            }
        }

        int res = 0;
        if (arr[arr.length - 1] != 0) {
            res = dp[arr.length - 1][arr[arr.length - 1]][2];
        } else {
            for (int i = 1; i <= 200; i++) {
                res += dp[arr.length - 1][i][2];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int len = 4;
        int testTime = 100;
        System.out.println("功能测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * len) + 2;
            int[] arr = generateRandomArray(N);
            int ans2 = method2(arr);
            int ans3 = ways3(arr);
            if (ans2 != ans3) {
                System.out.println("Oops!");
            }
        }
        System.out.println("功能测试结束");
        System.out.println("===========");
//        int N = 100000;
//        int[] arr = generateRandomArray(N);
//        long begin = System.currentTimeMillis();
//        ways3(arr);
//        long end = System.currentTimeMillis();
//        System.out.println("run time : " + (end - begin) + " ms");
    }

}
