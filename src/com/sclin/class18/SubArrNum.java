package com.sclin.class18;

import java.util.LinkedList;

import static class24.Code02_AllLessNumSubArray.*;

public class SubArrNum {

    /*
    给定一个整型数组arr，和一个整数num，
    某个arr中的子数组sub（位置不能调换），如果想达标，必须瞒住：
    sub中的最大值-sub中的最小值 <= num，
    返回arr中达标子数组的数量
     */
    public static int method(int[] arr, int num){
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int result = 0;
        LinkedList<Integer> qMax = new LinkedList<>();// 最大值
        LinkedList<Integer> qMin = new LinkedList<>();// 最小值
        int max = 0;
        int min = 0;
        int R = 0;
        // 表示以i为开始的子数组，存在多少个符合的
        for (int i = 0; i < arr.length; i++) {
            while (R < arr.length) {
                while (!qMax.isEmpty() && arr[qMax.peekLast()] <= arr[R]) {
                    qMax.pollLast();
                }
                qMax.add(R);
                while (!qMin.isEmpty() && arr[qMin.peekLast()] >= arr[R]) {
                    qMin.pollLast();
                }
                qMin.add(R);
                max = qMax.peekFirst();
                min = qMin.peekFirst();
                if (arr[max] - arr[min] > num) {
                    break;
                } else {
                    R++;
                }
            }
            result += R - i;
            if (qMax.peekFirst() == i) {
                qMax.pollFirst();
            }
            if (qMin.peekFirst() == i) {
                qMin.pollFirst();
            }
        }
        return result;
    }

    public static void main(String[] args) {

//        int[] arr = {1,2,51,3,5,3,2};
//        int sum = 4;
//        int ans1 = method(arr, sum);
//        int ans2 = num(arr, sum);
//
//        System.out.println(ans1);
//        System.out.println(ans2);

//
        int maxLen = 100;
        int maxValue = 200;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxLen, maxValue);
            int sum = (int) (Math.random() * (maxValue + 1));
            int ans1 = method(arr, sum);
            int ans2 = num(arr, sum);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(sum);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");

    }

}
