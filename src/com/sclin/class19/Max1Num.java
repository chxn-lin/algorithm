package com.sclin.class19;

import static class25.Code03_LargestRectangleInHistogram.largestRectangleArea2;
import static class25.Code04_MaximalRectangle.maximalRectangle;

public class Max1Num {

//    给定一个二维数组matrix，其中的值不是0就是1，
//    返回全部由1组成的最大子矩阵，内部有多少个1

    public static int method1(char[][] arr1){
        int[][] arr = toIntArr(arr1);
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int result = 0;
        int length = arr[0].length;
        int[] newArr = new int[length];
        for (int col = 0; col < arr.length; col++) {
            if (col == 0) {
                for (int i = 0; i < length; i++) {
                    newArr[i] = arr[col][i];
                }
            } else {
                for (int i = 0; i < length; i++) {
                    newArr[i] = arr[col][i] == 0 ? 0 : newArr[i] + 1;
                }
            }
            // 处理数据
            result = Math.max(result, LagestM2.method1(newArr));
        }
        return result;
    }

    public static void main(String[] args) {
        char[][] arr = {
                {'1','1','0','1','0','1','1'},
                {'1','1','0','1','1','1','1'},
                {'1','0','1','1','0','1','1'},
                {'1','1','1','1','1','1','1'},
//                {1,1}
        };
        System.out.println("my:" + method1(arr));
        System.out.println("true:" + maximalRectangle(arr));
    }

    public static int[][] toIntArr(char[][] arr){
        int[][] result = new int[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                result[i][j] = arr[i][j] == '1' ? 1 : 0;
            }
        }
        return result;
    }

}
