package com.sclin.class19;

import java.util.Stack;

import static class25.Code04_MaximalRectangle.maximalRectangle;
import static class25.Code05_CountSubmatricesWithAllOnes.numSubmat;

public class SubArrNum {

//    给定一个二维数组matrix，其中的值不是0就是1，
//    返回全部由1组成的子矩阵数量
    public static int method1(int[][] matrix){
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int result = 0;
        int length = matrix[0].length;
        int[][] arr = new int[matrix.length][length];
        for (int col = 0; col < matrix.length; col++) {
            int[] newArr = arr[col];
            if (col == 0) {
                for (int i = 0; i < length; i++) {
                    newArr[i] = matrix[col][i];
                }
            } else {
                for (int i = 0; i < length; i++) {
                    newArr[i] = matrix[col][i] == 0 ? 0 : (arr[col - 1][i] + 1);
                }
            }
        }
        // 加工出了高度数值
        // 通过高度，然后计算宽度，然后得出个数
        // 规定必须以这一行做底的，有多少个
        for (int index = 0; index < matrix.length; index++) {
            int[] indexArr = arr[index];
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < indexArr.length; i++) {
                while (!stack.isEmpty() && indexArr[i] < indexArr[stack.peek()]) {
                    int pop = stack.pop();
                    int L = stack.isEmpty() ? -1 : stack.peek();
                    int R = i;
                    int high = indexArr[pop] - Math.max(L == -1 ? 0 : indexArr[L], indexArr[R]);
                    int len = R - L -1;
                    result += high * ((len) * (len + 1) / 2);
                }
                if (!stack.isEmpty() && indexArr[i] == indexArr[stack.peek()]) {
                    // 相等，就当做比他大的跳过，后面会计算到。
                }
                stack.add(i);
            }
            while (!stack.isEmpty()) {
                int pop = stack.pop();
                int L = stack.isEmpty() ? -1 : stack.peek();
                int R = indexArr.length;
                int high = indexArr[pop] - (L == -1 ? 0 : indexArr[L]);
                int len = R - L -1;
                result += high * ((len) * (len + 1) / 2);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] arr = {
                {1,1,0,0,1,0,1,0,1},
                {1,0,0,1,1,0,0,1,1},
                {1,1,0,0,1,1,1,1,1},
                {0,1,1,0,1,0,1,0,1},
//                {1,0,1},
//                {1,1,0},
        };
        System.out.println("my:" + method1(arr));
        System.out.println("true:" + numSubmat(arr));
    }

}
