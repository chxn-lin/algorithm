package com.sclin.class21;

public class KMP {

//    给定两个字符串s1,s2，问s1内是否有哪个字符串子串（连续的），是和s2一样的。
    public static int getIndexStupid(String s1, String s2){
        if ("".equals(s1) || "".equals(s2) || s1.length() < s2.length()) {
            return -1;
        }
        char[] char1 = s1.toCharArray();
        char[] char2 = s2.toCharArray();
        int result = -1;
        for (int index = 0; index < char1.length; index++) {
            int temp = 0;
            while (temp < char2.length && index + temp < s1.length()) {
                if (char1[index + temp] == char2[temp]) {
                    temp++;
                } else {
                    break;
                }
            }
            if (temp == char2.length) {
                result = index;
                break;
            }
        }

        return result;
    }


    public static int getIndex(String s1, String s2){
        if ("".equals(s1) || "".equals(s2) || s1.length() < s2.length()) {
            return -1;
        }
        char[] char1 = s1.toCharArray();
        char[] char2 = s2.toCharArray();

        int[] next = getNextArray(char2);
        int x = 0;
        int y = 0;
        while (x < char1.length && y < char2.length) {
            if (char1[x] == char2[y]) {
                x++;
                y++;
            } else if (next[y] != -1) {
                y = next[y];
            } else {
                x++;
            }
        }
        return y == char2.length ? x - y : -1;
    }

    private static int[] getNextArray(char[] chars) {
        if (chars.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[chars.length];
        next[0] = -1;
        next[1] = 0;
        int cn = 0;
        int i = 2;
        while (i != chars.length) {
            if (chars[i - 1] == chars[cn]) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }

        return next;
    }

    public static void main(String[] args) {
        String s1 = "bcdbcbcdbcdaadaaabcdbcdaa";
        String s2 = "bcdbcdaa";
        System.out.println(getIndexStupid(s1, s2));
        System.out.println(getIndex(s1, s2));

    }

}
