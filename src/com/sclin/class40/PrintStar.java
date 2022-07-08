package com.sclin.class40;

import static class40.Code08_PrintStar.printStar;

public class PrintStar {

    // 打印*
    public static void print(int n){
        char[][] arr = new char[n][n];
        int a = 0;
        int x = n - 1;
        while (a <= x) {
            set(arr, a, x);
            a += 2;
            x -= 2;
        }
        pri(arr);
    }

    private static void set(char[][] arr, int a, int x) {
        for (int i = a; i <= x; i++) {
            arr[a][i] = '*';
        }
        for (int i = a; i <= x; i++) {
            arr[i][x] = '*';
        }
        for (int i = x; i > a; i--) {
            arr[x][i] = '*';
        }
        for (int i = x; i > a + 1; i--) {
            arr[i][a + 1] = '*';
        }
    }

    public static void pri(char[][] arr){
        for (char[] chars : arr) {
            for (char aChar : chars) {
                System.out.print((aChar == '*' ? aChar + " " : "  "));
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int n = 6;
        print(n);
        System.out.println();
        printStar(n);
    }

}
