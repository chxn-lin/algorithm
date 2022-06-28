package com.sclin.class18;

import java.util.LinkedList;

import static class24.Code01_SlidingWindowMaxArray.*;

public class SwepWindow {

    public static int[] method(int[] arr, int w){
        if (arr == null || arr.length < w || w < 1) {
            return null;
        }
        int[] result = new int[arr.length - w + 1];
        int index = 0;// 结果数组的下标
        // 存放下标
        LinkedList<Integer> qMax = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            while (!qMax.isEmpty() && arr[qMax.peekLast()] <= arr[i]) {
                qMax.pollLast();
            }
            qMax.add(i);
            if (i + 1 >= w) {
                if (qMax.peekFirst() == i - w) {
                    qMax.pollFirst();
                }
                result[index++] = arr[qMax.peekFirst()];
            }
        }

        return result;
    }

    public static void main(String[] args) {
//        int[] arr = {1,5,3,2,6};
//        int w = 3;
//        int[] ans1 = method(arr, w);
//        for (int i : ans1) {
//            System.out.print(i + ", ");
//        }

        int testTime = 100000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int w = (int) (Math.random() * (arr.length + 1));
            int[] ans1 = method(arr, w);
            int[] ans2 = right(arr, w);
            if (!isEqual(ans1, ans2)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }

}
