package com.sclin.class11;

import static class14.Code01_Light.*;

public class Light {

    /**
     * 给定一个字符串str，只由 ‘X’ 和 ‘.’ 两种字符构成。
     * 'X' 表示墙，不能放灯，也不需要点灯
     * '.' 表示居民点，可以放灯，需要点灯
     * 如果灯放i位置，可以让i - 1、i 和 i + 1 三个位置被点亮
     * 返回如果点亮str中所有需要点亮的位置，至少需要几盏灯
     */

    public static int method1(String str){
        char[] arr = str.toCharArray();
        int result = 0;
        int index = 0;
        while (index < arr.length) {
            char s = arr[index];
            if (s == 'X') {
                index++;
            } else {
                result++;
                if (index + 1 == arr.length) {
                    break;
                } else {
                    if (arr[index + 1] == 'X') {
                        index += 2;
                    } else {
                        index += 3;
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int len = 20;
        int testTime = 100000;
        for (int i = 0; i < testTime; i++) {
            String test = randomString(len);
            int ans1 = minLight2(test);
            int ans2 = method1(test);
            if (ans1 != ans2) {
                System.out.println("oops!");
            }
        }
        System.out.println("finish!");
    }

}
