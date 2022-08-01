package com.sclin.class46;

import static class46.Code03_DeleteAdjacentSameCharacter.randomString;
import static class46.Code03_DeleteAdjacentSameCharacter.restMin3;

public class DeleteChar {

    public static int method1(String str){
        if (str == null || str.length() < 0) {
            return 0;
        }
        if (str.length() < 2) {
            return str.length();
        }
        return process1(str.toCharArray(), 0, str.length() - 1, false);
    }

    private static int process1(char[] chars, int L, int R, boolean has) {
        if (L > R) {
            return 0;
        }
        if (L == R) {
            return has ? 0 : 1;
        }
        // 找到相同的数量
        int index = L;
        int K = has ? 1 : 0;
        while (index + 1 <= R && chars[index + 1] == chars[L]) {
            index++;
            K++;
        }
        index++;
        K++;

        int res = (K > 1 ? 0 : 1) + process1(chars, index, R, false);
        for (int i = index; i <= R; i++) {
            if (chars[L] == chars[i] && chars[i] != chars[i - 1]) {
                if (process1(chars, index, i - 1, false) == 0) {
                    res = Math.min(res, process1(chars, i, R, K != 0));
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
//        String str = "bab";
//        int ans2 = method1(str);
//        System.out.println(ans2);

        int maxLen = 16;
        int variety = 3;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * maxLen);
            String str = randomString(len, variety);
            int ans2 = method1(str);
            int ans3 = restMin3(str);
            if (ans2 != ans3) {
                System.out.println(str);
                System.out.println(ans2);
                System.out.println(ans3);
                System.out.println("出错了！");
                break;
            }
        }
        System.out.println("测试结束");
    }

}
