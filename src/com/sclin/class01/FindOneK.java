package com.sclin.class01;

public class FindOneK {

    // 一个数组中有一种数出现了奇数次，其他数都出现了偶数次，找到并打印这种数
    public static void main(String[] args) {
        int[] arr = {12, 3, 51, 235, 12, 51, 235, 51, 235, 51, 235, 12, 12};
        System.out.println(findK(arr));
    }

    static int findK(int[] arr) {
        int eor = 0;
        for (int a : arr) {
            eor ^= a;
        }
        return eor;
    }

}
