package com.sclin.class32;

import class32.Code01_IndexTree;

public class IndexTree {

    private int[] sum;
    private int size;

    public IndexTree(int length) {
        size = length;
        sum = new int[size + 1];
    }

    public void add(int index, int val){
        while (index <= size) {
            sum[index] += val;
            index += index & (-index);
        }
    }

    public int sum(int index){
        int res = 0;
        while (index != 0) {
            res += sum[index];
            index -= index & (-index);
        }
        return res;
    }

    public static void main(String[] args) {
        int N = 100;
        int V = 100;
        int testTime = 2000000;
        IndexTree tree = new IndexTree(N);
        Code01_IndexTree.Right test = new Code01_IndexTree.Right(N);
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int index = (int) (Math.random() * N) + 1;
            if (Math.random() <= 0.5) {
                int add = (int) (Math.random() * V);
                tree.add(index, add);
                test.add(index, add);
            } else {
                if (tree.sum(index) != test.sum(index)) {
                    System.out.println("Oops!");
                }
            }
        }
        System.out.println("test finish");
    }

}
