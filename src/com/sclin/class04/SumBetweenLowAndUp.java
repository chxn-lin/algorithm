package com.sclin.class04;

public class SumBetweenLowAndUp {

    // https://leetcode.com/problems/count-of-range-sum/

    public static void main(String[] args) {

        int[] arr = {-2147483647,0,-2147483647,2147483647};
        int low = -564;
        int upper = 3864;
        System.out.println(myCountRangeSum(arr, low, upper));

    }

    public static int myCountRangeSum(int[] arr, int low, int upper) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        // 处理成累加和数组
        long[] sumArr = new long[arr.length];
        sumArr[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sumArr[i] = sumArr[i - 1] + arr[i];
        }

        return process(sumArr, 0, sumArr.length - 1, low, upper);
    }

    private static int process(long[] arr, int L, int R, int low, int upper) {
        if (L == R) {
            return arr[R] >= low && arr[R] <= upper ? 1 : 0;
        }
        int M = L + ((R - L) >> 1);
        return process(arr, L, M, low, upper)
                + process(arr, M + 1, R, low, upper)
                + merge(arr, L, M, R, low, upper);
    }

    private static int merge(long[] arr, int L, int M, int R, int low, int upper) {
        int ret = 0;
        // 窗口
        int winL = L;
        int winR = winL;
        long myLow = 0;
        long myUpper = 0;
        // 以arr[i]结尾，前面有多少个数在这个范围
        for (int i = M + 1; i <= R; i++) {
            myLow = arr[i] - upper;
            myUpper = arr[i] - low;

            while (winL < M+1 && arr[winL] < myLow) {
                winL++;
            }
            while (winR < M+1 && arr[winR] <= myUpper) {
                winR++;
            }
            ret += winR - winL;
        }

        // 排序数组
        int lIndex = L;
        int rIndex = M + 1;
        long[] tempArr = new long[R - L + 1];
        int tempIndex = 0;
        while (lIndex <= M && rIndex <= R) {
            tempArr[tempIndex++] = arr[lIndex] < arr[rIndex] ? arr[lIndex++] : arr[rIndex++];
        }

        while (lIndex <= M) {
            tempArr[tempIndex++] = arr[lIndex++];
        }
        while (rIndex <= R) {
            tempArr[tempIndex++] = arr[rIndex++];
        }

        for (int i = 0; i < tempArr.length; i++) {
            arr[L + i] = tempArr[i];
        }

        return ret;
    }

}
