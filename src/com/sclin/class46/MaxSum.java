package com.sclin.class46;

import java.util.LinkedList;

import static class46.Code04_MaxSumLengthNoMore.maxSum;
import static class46.Code04_MaxSumLengthNoMore.randomArray;

public class MaxSum {

    public static int method1(int[] arr, int M){
        if (arr == null || arr.length < 1 || M < 1) {
            return 0;
        }
        int[] sumArr = new int[arr.length];
        sumArr[0] = arr[0];
        for (int i = 1; i < sumArr.length; i++) {
            sumArr[i] = arr[i] + sumArr[i - 1];
        }
        // 利用滑动窗口
        // 先将数填充满
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < Math.min(M, arr.length); i++) {
            while (!queue.isEmpty() && sumArr[queue.peekLast()] <= sumArr[i]) {
                queue.pollLast();
            }
            queue.addLast(i);
        }
        int L = 0;
        int i = Math.min(M, arr.length);
        int res = sumArr[queue.getFirst()];
        for (; i < arr.length; i++, L++) {
            if (queue.peekFirst() == L) {
                queue.pollFirst();
            }
            while (!queue.isEmpty() && sumArr[queue.peekLast()] <= sumArr[i]) {
                queue.pollLast();
            }
            queue.addLast(i);
            res = Math.max(res, sumArr[queue.getFirst()] - sumArr[L]);
        }
        for (; L < arr.length - 1; L++) {
            if (queue.peekFirst() == L) {
                queue.pollFirst();
            }
            res = Math.max(res, sumArr[queue.peekFirst()] - sumArr[L]);
        }

        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1,5,-2,3,-2,20,-1};
        int M = 3;
        int ans1 = method1(arr, M);
        int ans2 = maxSum(arr, M);
        System.out.println(ans1);
        System.out.println(ans2);

//        int maxN = 50;
//        int maxValue = 100;
//        int testTime = 1000000;
//        System.out.println("测试开始");
//        for (int i = 0; i < testTime; i++) {
//            int N = (int) (Math.random() * maxN);
//            int M = (int) (Math.random() * maxN);
//            int[] arr = randomArray(N, maxValue);
//            int ans1 = method1(arr, M);
//            int ans2 = maxSum(arr, M);
//            if (ans1 != ans2) {
//                System.out.println(ans1);
//                System.out.println(ans2);
//                System.out.println("Oops!");
//            }
//        }
//        System.out.println("测试结束");
    }

}
