package com.sclin.class12;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二维数组matrix，里面的值不是1就是0，上下左右相邻的认为是一片岛，
 * 返回matrix中岛的数量。
 */
public class NumOfLands {

    public static void main(String[] args) {
        char[][] arr = {
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}
        };
        System.out.println(new NumOfLands().numIslands(arr));
    }

    public int numIslands(char[][] grid) {
        return getSize2(grid);
    }

    // 使用hashMap的方式实现
    public static int getSize1(char[][] arr) {
        if (arr == null) {
            return 0;
        }
        int rowSize = arr.length;
        int culSize = arr[0].length;
        Dot[][] dots = new Dot[rowSize][culSize];
        List<Dot> list = new ArrayList<>();

        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < culSize; j++) {
                if (arr[i][j] == '1') {
                    dots[i][j] = new Dot();
                    list.add(dots[i][j]);
                }
            }
        }
        Union<Dot> uni = new Union(list.toArray());
        for (int i = 1; i < culSize; i++) {
            if (arr[0][i] == '1' && arr[0][i - 1] == '1') {
                uni.union(dots[0][i], dots[0][i - 1]);
            }
        }
        for (int i = 1; i < rowSize; i++) {
            if (arr[i][0] == '1' && arr[i - 1][0] == '1') {
                uni.union(dots[i][0], dots[i - 1][0]);
            }
        }
        for (int i = 1; i < rowSize; i++) {
            for (int j = 1; j < culSize; j++) {
                if (arr[i][j] == '1' && arr[i - 1][j] == '1') {
                    uni.union(dots[i][j], dots[i - 1][j]);
                }
                if (arr[i][j] == '1' && arr[i][j - 1] == '1') {
                    uni.union(dots[i][j], dots[i][j - 1]);
                }
            }
        }
        return uni.getSize();
    }

    private static class Dot{}

    public static int getSize2(char[][] arr){
        if (arr == null) {
            return 0;
        }
        int rowSize = arr.length;
        int culSize = arr[0].length;
        UnionForLand uni = new UnionForLand(arr);
        for (int i = 1; i < culSize; i++) {
            if (arr[0][i] == '1' && arr[0][i - 1] == '1') {
                uni.union(0, i, 0, i - 1);
            }
        }
        for (int i = 1; i < rowSize; i++) {
            if (arr[i][0] == '1' && arr[i - 1][0] == '1') {
                uni.union(i, 0, i - 1, 0);
            }
        }
        for (int i = 1; i < rowSize; i++) {
            for (int j = 1; j < culSize; j++) {
                if (arr[i][j] == '1' && arr[i - 1][j] == '1') {
                    uni.union(i, j, i - 1, j);
                }
                if (arr[i][j] == '1' && arr[i][j - 1] == '1') {
                    uni.union(i, j, i, j - 1);
                }
            }
        }
        return uni.getSize();
    }

    // 使用感染实现
    public static int getSize3(char[][] arr) {
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == '1') {
                    result++;
                    process(arr, i, j);
                }
            }
        }
        return result;
    }

    private static void process(char[][] arr, int r, int c) {
        int rSize = arr.length;
        int cSize = arr[0].length;
        if (r >= rSize || c >= cSize || r < 0 || c < 0) {
            return;
        }
        if (arr[r][c] == '1') {
            arr[r][c] = '2';
        } else {
            return;
        }
        process(arr, r + 1, c);
        process(arr, r - 1, c);
        process(arr, r, c + 1);
        process(arr, r, c - 1);
    }
}
