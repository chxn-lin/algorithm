package com.sclin.class22;

import static class28.Code01_Manacher.getRandomString;
import static class28.Code01_Manacher.manacher;

public class Manacher {

    public static int stupidMethod(String str1){
        if (str1 == null || "".equals(str1)) {
            return 0;
        }
        int res = 0;
        String str = makeAppendStr(str1);
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int L = i - 1;
            int R = i + 1;
            while (L >= 0 && R < chars.length && chars[L] == chars[R]) {
                L--;
                R++;
            }
            res = Math.max(res, R - L - 1);
        }
        return res / 2;
    }

    public static int smartMethod(String str1){
        if (str1 == null || "".equals(str1)) {
            return 0;
        }
        int res = 0;
        String str = makeAppendStr(str1);
        char[] chars = str.toCharArray();
        int[] sizeArr = new int[chars.length];
        int C = 0;// 当前的中点位置
        int mostR = -1;// 目前位置，扩到最右+1的位置

        for (int i = 0; i < chars.length; i++) {
            int curMax = 0;
            if (i >= mostR) {
                int L = i - 1;
                int R = i + 1;
                while (L >= 0 && R < chars.length && chars[L] == chars[R]) {
                    L--;
                    R++;
                }
                curMax = R - L - 1;
                mostR = R;
                C = i;
            } else {
                // R将i覆盖住了，那么表示可以使用优化
                int i1 = C - (i - C);// 对应下标
                int num = sizeArr[i1];// 对应位置位中间的数量
                int iMostR = i + num/2;
                if (iMostR + 1 > mostR) {
                    int L = i - 1;
                    int R = i + 1;
                    while (L >= 0 && R < chars.length && chars[L] == chars[R]) {
                        L--;
                        R++;
                    }
                    curMax = R - L - 1;
                } else if (iMostR + 1 < mostR){
                    curMax = num;
                } else {
                    while (mostR < chars.length && i - (mostR - i) >= 0
                        && chars[mostR] == chars[i - (mostR - i)]) {
                        mostR++;
                        C = i;
                        num = num + 2;
                    }
                    curMax = num;
                }
            }
            sizeArr[i] = curMax;
            res = Math.max(res, curMax);
        }
        return res / 2;
    }

    public static String makeAppendStr(String str){
        char[] chars = str.toCharArray();
        StringBuilder builder = new StringBuilder();
        if (chars.length != 0) {
            builder.append("#");
        }
        for (char c : chars) {
            builder.append(c + "#");
        }
        return builder.toString();
    }

    public static void main(String[] args) {
//        String str = "eeec";
//        System.out.println("true:" + stupidMethod(str));
//        System.out.println(smartMethod(str));
        int possibilities = 5;
        int strSize = 20;
        int testTimes = 5000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String str = getRandomString(possibilities, strSize);
            if (manacher(str) != smartMethod(str)) {
                System.out.println(str);
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }

}
