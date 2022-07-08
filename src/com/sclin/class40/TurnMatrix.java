package com.sclin.class40;

public class TurnMatrix {

    public static void method(String[][] arr){
        int a = 0;
        int x = arr.length - 1;
        while (a < x) {
            turu(arr, a, x);
            a = a + 1;
            x = x - 1;
        }
    }

    private static void turu(String[][] arr, int a, int x) {
        String temp = "";
        // 组
        for (int i = 0; i < x - a; i++) {
            temp = arr[a][a + i];
            arr[a][a + i] = arr[x - i][a];
            arr[x - i][a] = arr[x][x - i];
            arr[x][x - i] = arr[a + i][x];
            arr[a + i][x] = temp;
        }
    }


    public static void main(String[] args) {
        String[][] arr = {
                {"a", "b", "c","d"},
                {"e", "f", "g","h"},
                {"i", "j", "k","l"},
                {"m", "n", "o","p"},
        };
        pri(arr);
        method(arr);
        pri(arr);
    }

    public static void pri(String[][] arr){
        System.out.println("{");
        for (String[] s : arr) {
            System.out.print("{");
            for (String s1 : s) {
                System.out.print(s1 + ",");
            }
            System.out.print("},");
            System.out.println();
        }
        System.out.println("}");
    }

}
