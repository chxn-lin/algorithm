package com.sclin.class22;

import static com.sclin.class22.Manacher.makeAppendStr;

public class LessAppendStrNum {

    public static int app(String str1){
        if (str1 == null || str1.length() < 2) {
            return 0;
        }
        String str = makeAppendStr(str1);
        char[] chars = str.toCharArray();
        int[] sizeArr = new int[chars.length];
        int C = 0;// 当前的中点位置
        int mostR = -1;// 目前位置，扩到最右+1的位置
        int tempSize = 0;

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
            tempSize = sizeArr[i];
            if (mostR == chars.length) {
                break;
            }
        }
        return str1.length() - tempSize / 2;
    }

    public static void main(String[] args) {
        String str = "abc12321";
        System.out.println(app(str));
    }

}
