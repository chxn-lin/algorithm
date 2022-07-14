package com.sclin.class41;

import static class41.Code04_SplitArrayLargestSum.*;

public class FinishPrint {

    // 画家画画
    public static int method1(int[] arr, int num) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int[] sumArr = new int[arr.length + 1];
        for (int i = 1; i < arr.length + 1; i++) {
            sumArr[i] = sumArr[i - 1] + arr[i - 1];
        }
        return process(arr, sumArr, 0, num);
    }

    private static int process(int[] arr, int[] sumArr, int index, int peo) {
        if (peo < 0) {
            return -1;
        }
        if (index == arr.length) {
            return 0;
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i <= arr.length - index; i++) {
            int cur = sumArr[index + i] - sumArr[index];
            int p1 = process(arr, sumArr, index + i, peo - 1);
            if (p1 != -1) {
                cur = Math.max(cur, p1);
                res = Math.min(res, cur);
            }
        }
        return res;
    }

    public static int method2(int[] arr, int num) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int[] sumArr = new int[arr.length + 1];
        for (int i = 1; i < arr.length + 1; i++) {
            sumArr[i] = sumArr[i - 1] + arr[i - 1];
        }
        int[][] dp = new int[arr.length + 1][num + 1];

        for (int index = 0; index < arr.length; index++) {
            dp[index][0] = -1;
        }

        for (int peo = 1; peo <= num; peo++) {
            for (int index = arr.length - 1; index >= 0; index--) {
                int res = Integer.MAX_VALUE;
                for (int i = 0; i <= arr.length - index; i++) {
                    int cur = sumArr[index + i] - sumArr[index];
                    int p1 = dp[index + i][peo - 1];
                    if (p1 != -1) {
                        cur = Math.max(cur, p1);
                        res = Math.min(res, cur);
                    }
                }
                dp[index][peo] = res;
            }
        }

        return dp[0][num];
    }

    public static int method3(int[] nums, int K) {
        int N = nums.length;
        int[] sum = new int[N + 1];
        for (int i = 0; i < N; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
        int[][] dp = new int[N][K + 1];
        int[][] best = new int[N][K + 1];
        for (int j = 1; j <= K; j++) {
            dp[0][j] = nums[0];
            best[0][j] = -1;
        }
        for (int i = 1; i < N; i++) {
            dp[i][1] = sum(sum, 0, i);
            best[i][1] = -1;
        }
        // 每一行从上往下
        // 每一列从左往右
        // 根本不去凑优化位置对儿！

        for (int j = 2; j <= K; j++) {
            for (int i = N - 1; i >= 1; i--) {
                int ans = Integer.MAX_VALUE;
                int choose = -1;
                int up = i == N - 1 ? i : best[i + 1][j];
                int down = best[i][j - 1];
                // 枚举是完全不优化的！
                for (int leftEnd = down; leftEnd <= up; leftEnd++) {
                    int leftCost = leftEnd == -1 ? 0 : dp[leftEnd][j - 1];
                    int rightCost = leftEnd == i ? 0 : sum(sum, leftEnd + 1, i);
                    int cur = Math.max(leftCost, rightCost);
                    if (cur < ans) {
                        ans = cur;
                        choose = leftEnd;
                    }
                }
                dp[i][j] = ans;
                best[i][j] = choose;
            }
        }
        return dp[N - 1][K];
    }

    public static int method4(int[] arr, int num){
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        int res = 0;
        int left = 0;
        while (left <= sum) {
            int mid = (left + sum) >> 1;
            int cur = getParts(arr, mid);
            if (cur <= num) {
                sum = mid - 1;
                res = mid;
            } else {
                left = mid + 1;
            }
        }

        return res;
    }

    private static int getParts(int[] arr, int aim) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > aim) {
                return Integer.MAX_VALUE;
            }
        }
        int part = 1;
        int curSum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (curSum + arr[i] <= aim) {
                curSum += arr[i];
            } else {
                part++;
                curSum = arr[i];
            }
        }
        return part;
    }


    public static void main(String[] args) {
//        int[] arr = {1,2,3,4};
//        int M = 1;
//        System.out.println(method4(arr, M));

        int N = 10;
        int maxValue = 100;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N) + 1;
            int M = (int) (Math.random() * N) + 1;
            int[] arr = randomArray(len, maxValue);
            int ans2 = method4(arr, M);
            int ans3 = splitArray3(arr, M);
            if (ans2 != ans3) {
                System.out.print("arr : ");
                printArray(arr);
                System.out.println("M : " + M);
                System.out.println("my : " + ans2);
                System.out.println("ans3 : " + ans3);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }

}
