package com.sclin.class21;

public class CheckIsTurnStr {

//    给你两个字符串，判断是否互为为旋转串？
    public static boolean check(String str1, String str2){
        if ("".equals(str1) || "".equals(str2) || str1.length() != str2.length()) {
            return false;
        }
        str1 = str1 + str1;
        return str1.contains(str2);
    }

    public static boolean check2(String str1, String str2){
        if ("".equals(str1) || "".equals(str2) || str1.length() != str2.length()) {
            return false;
        }
        str1 = str1 + str1;
        return kmp(str1.toCharArray(), str2.toCharArray());
    }

    public static boolean kmp(char[] arr1, char[] arr2){
        int[] next = getNext(arr2);
        int x = 0;
        int y = 0;
        while (x != arr1.length && y != arr2.length) {
            if (arr1[x] == arr2[y]) {
                x++;
                y++;
            } else if (next[y] != -1) {
                y = next[y];
            } else {
                x++;
            }
        }
        return y == arr2.length ? true : false;
    }

    private static int[] getNext(char[] arr2) {
        int[] res = new int[arr2.length];
        res[0] = -1;
        res[1] = 0;
        int cn = 0;
        int i = 2;
        while (i != res.length) {
            if (arr2[i] == arr2[cn]) {
                res[i++] = ++cn;
            } else if (res[cn] != -1) {
                cn = res[cn];
            } else {
                i++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String str1 = "abcdabc";
        String str2 = "abbcdab";
        System.out.println(check(str1, str2));
        System.out.println(check2(str1, str2));
    }

}
