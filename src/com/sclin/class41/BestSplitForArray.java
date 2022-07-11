package com.sclin.class41;

import static class41.Code02_BestSplitForEveryPosition.*;

public class BestSplitForArray {

    public static int[] method1(int[] arr){
        if (arr == null || arr.length == 0) {
            return new int[0];
        }
        int[] sumArr = new int[arr.length + 1];
        sumArr[0] = 0;
        for (int i = 1; i < sumArr.length; i++) {
            sumArr[i] = sumArr[i - 1] + arr[i - 1];
        }
        int[] res = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            int cur = 0;
            for (int j = 0; j <= i; j++) {
                int leftSum = sumArr[j];
                cur = Math.max(cur, Math.min(leftSum, sumArr[i + 1] - leftSum));

            }
            res[i] = cur;
        }
        return res;
    }

    public static int[] method2(int[] arr){
        if (arr == null || arr.length == 0) {
            return new int[0];
        }
        int[] sumArr = new int[arr.length + 1];
        sumArr[0] = 0;
        for (int i = 1; i < sumArr.length; i++) {
            sumArr[i] = sumArr[i - 1] + arr[i - 1];
        }
        int[] res = new int[arr.length];

        int ind = 0;// 当前指针
        for (int i = 0; i < arr.length; i++) {
            int curSum = 0;
            while (ind <= i
                    && (curSum = Math.min(sumArr[ind], sumArr[i + 1] - sumArr[ind]))
                    <= Math.min(sumArr[ind + 1], sumArr[i + 1] - sumArr[ind + 1])) {
                ind++;
            }

            res[i] = Math.min(curSum, sumArr[i + 1] - sumArr[ind]);
        }
        return res;
    }


    public static void main(String[] args) {
//        int[] arr = {1,3,4,1,3,7};
//        int[] arr1 = method2(arr);
//        int[] arr2 = bestSplit3(arr);
//        pintArr(arr1);
//        pintArr(arr2);

        int N = 20;
        int max = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N);
            int[] arr = randomArray(len, max);
            int[] ans2 = method2(arr);
            int[] ans3 = bestSplit3(arr);
            if (!isSameArray(ans2, ans3)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束");
    }

    private static void pintArr(int[] ints) {
        System.out.println();
        for (int anInt : ints) {
            System.out.print(anInt + " ");
        }
        System.out.println();
    }

}
