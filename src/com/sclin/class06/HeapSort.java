package com.sclin.class06;

import java.util.PriorityQueue;

import static com.sclin.class01.KM1.printArr;

public class HeapSort {

    public static void main(String[] args) {
        int[] arr = {15, 1, 3, 5, 32, 18};
        int k = 3;

        int[] sortArr = sort(arr, k);

        printArr(sortArr);
    }

    // 已知一个几乎有序的数组，
    // 几乎有序是指，如果把数组排好序，每个元素移动的举例一定不超过k，
    // 并且k相对于数组长度来说是比较小的。
    private static int[] sort(int[] arr, int k) {
        if (arr.length < 1)
            return arr;
        int[] help = new int[arr.length];
        k = k > arr.length ? arr.length - 1 : k;
        PriorityQueue<Integer> heap = new PriorityQueue();
        for (int i = 0; i < k + 1; i++) {
            heap.add(arr[i]);
        }

        for (int i = 0; i < arr.length; i++) {
            help[i] = heap.poll();
            if (i + k + 1 < arr.length) {
                heap.add(arr[i + k + 1]);
            }
        }

        return help;
    }


}
