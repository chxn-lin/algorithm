package com.sclin.class06;

public class MyHeap {

    public static void main(String[] args) {
        int[] arr = {7, 16, 8, 22, 2};
        MyHeap heap = new MyHeap(arr);
        System.out.println("==========");
    }

    private int[] heap;
    private int heapSize;

    public MyHeap(int size) {
        heap = new int[size];
        heapSize = 0;
    }

    public MyHeap(int[] arr) {
        heap = arr;
        heapSize = arr.length;
        // 尾插，效率会比头插高，因为这个只有少部分的节点，才需要遍历整颗数，而头插叶子节点都需要遍历
        for (int i = heapSize - 1; i >= 0; i--) {
            heapify(heap, i, heapSize);
        }
        // 头插
//        for (int i = 0; i < arr.length; i++) {
//            heapInsert(arr, i);
//        }
    }

    public MyHeap() {
        heap = new int[100];
        heapSize = 0;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    // 插入数据
    public void push(int value) {
        heap[heapSize] = value;
        heapInsert(heap, heapSize++);
    }

    // 用户此时，让你返回最大值，并且在大根堆中，把最大值删掉
    // 剩下的数，依然保持大根堆组织
    // 弹出
    public int pop() {
        if (isEmpty())
            throw new RuntimeException("heap is null");
        swap(heap, --heapSize, 0);
        heapify(heap, 0, heapSize);
        return heap[heapSize];
    }

    private void heapInsert(int[] arr, int index) {
        int parentInd = (index - 1) / 2;
        while (arr[index] > arr[parentInd]) {
            swap(arr, parentInd, index);
            index = parentInd;
            parentInd = (index - 1) / 2;
        }
    }

    private void heapify(int[] arr, int index, int heapSize) {
        int sonInd = index * 2 + 1;// 第一个子孩子
        int biggerValueInd = sonInd;
        while (sonInd < heapSize && arr[index] < arr[sonInd]) {
            if (sonInd + 1 < heapSize) {
                biggerValueInd = arr[sonInd + 1] > arr[sonInd] ? sonInd + 1 : sonInd;
            } else {
                biggerValueInd = sonInd;
            }
            swap(arr, index, biggerValueInd);
            index = biggerValueInd;
            sonInd = index * 2 + 1;
        }
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
