package com.sclin.class41;

import static class41.Code01_BestSplitForAll.bestSplit2;
import static class41.Code01_BestSplitForAll.randomArray;

public class BestSplitForOne {

    public static int method1(int[] arr){
        int[] sumArr = new int[arr.length + 1];
        sumArr[0] = 0;
        for (int i = 1; i < sumArr.length; i++) {
            sumArr[i] = sumArr[i - 1] + arr[i - 1];
        }
        int res = 0;

        int sum = sumArr[sumArr.length - 1];
        for (int i = 0; i < arr.length; i++) {
            int leftSum = sumArr[i];
            res = Math.max(res, Math.min(leftSum, sum - leftSum));
        }

        return res;
    }

    public static void main(String[] args) {
        int N = 20;
        int max = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N);
            int[] arr = randomArray(len, max);
            int ans1 = method1(arr);
            int ans2 = bestSplit2(arr);
            if (ans1 != ans2) {
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束");
    }

}
