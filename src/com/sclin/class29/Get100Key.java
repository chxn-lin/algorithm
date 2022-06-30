package com.sclin.class29;

import java.util.Random;

public class Get100Key {

    // 案例模拟中奖，分别记录中奖 次数
    public static void main(String[] args) {
        int[] count = new int[1793];
        int times = 1000000;
        for (int i = 0; i < times; i++) {
            int[] pages = new int[100];
            for (int num = 0; num <= 1792; num++) {
                if (num < 100) {
                    pages[num] = num;
                } else {
                    if (getRandom(num + 1) < 100) {
                        int index = new Random().nextInt(100);
                        pages[index] = num;
                    }
                }
            }
            // 统计本次袋子内的数
            for (int page : pages) {
                count[page]++;
            }
        }
        for (int i = 0; i < count.length; i++) {
            System.out.println(i + " : " + count[i]);
        }
    }

    // 等概率返回 0 - num 的数字，不包含num
    private static int getRandom(int num) {
        return (int) (Math.random() * (num));
    }

}
