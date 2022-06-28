package com.sclin.class01;

import java.util.HashMap;
import java.util.Map;

public class KM1 {

    // 1、有两种数，都出现了奇数次，其他数出现了偶数次，求这两个数
    // 2、一个数组中有一种数出现K次，其他数都出现了M次，M>1,K<M，找到这个数

    public static void main(String[] args) {
//        int[] arr = {3, 4, 5, 3, 4, 3, 5, 5, 5, 4};
//        findTwo(arr);

        for (int i = 0; i < 100000; i++) {
            int k, m;
            k = getRandom(10);

            do {
                m = getRandom(11);
            } while (k >= m);

            k = 3;
            m = 4;

            System.out.println("k,m的值分别为k:" + k + ", m:" + m);

            int[] arr = randomArray(k, m);

//            int[] arr = {1, 1, 1, 3, 3, 3, 3};

            int hashKey = findByHashMap(arr, k, m);
            int findK = findK(arr, k, m);

            if (hashKey != findK) {
                System.out.println("数组为：");
                printArr(arr);
                System.out.println("error，key：" + findK + ", true key: " + hashKey);
            }
        }


    }

    public static int[] randomArray(int k, int m){
        int otherKeyNum = getRandom(10);
        int key = getRandomWithDeap(20);
        int[] otherKeyArr = new int[otherKeyNum];

        int[] randomArr = new int[k * 1 + otherKeyNum * m];

        // 生成其他数的key值
        for (int i = 0; i < otherKeyArr.length ; i++) {
            int innerKey = 0;
            do {
                innerKey = getRandomWithDeap(20);
            } while (innerKey == key);
            otherKeyArr[i] = innerKey;
        }
        // 先将key的值放入数组
        for (int i = 0; i < k; i++) {
            randomArr[i] = key;
        }
        // 循环，将其他key值，放入数据库
        for (int i = 0; i < otherKeyNum; i++) {
            int otherKeyValue = otherKeyArr[i];
            // 循环数量，放入数组
            for (int j = 0; j < m; j++) {
                randomArr[k * 1 + i * m + j] = otherKeyValue;
            }
        }
        return randomArr;

    }

    // 获得1 - range的整数
    public static int getRandom(int range) {
        return  ((int)(Math.random() * range) + 1);
    }

    // 获得1 - range的整数
    public static int getRandomWithDeap(int range) {
        return  (int)((Math.random() * range) - (Math.random() * range));
    }

    public static void printArr(int[] arr) {
        System.out.print("{");
        for (int num : arr) {
            System.out.print(num + ", ");
        }
        System.out.print("}");
    }

    private static void findTwo(int[] arr) {
        int eor = 0;
        int a, b;// 这两个数就是要找的数
        for(int aa : arr) {
            eor ^= aa;// 全部异或完，得到a^b
        }
        // 找到最右侧的1(a，b在这一位上面是不一样的)
        int rightOne = eor & (-eor);
        int onlyOne = 0;
        for (int aa : arr) {
            // 将其中一类找出来，^运算
            if ((aa & rightOne) != 0) {
                onlyOne ^= aa;
            }
        }
        System.out.println((eor ^ onlyOne) + " " + onlyOne);
    }

    private static int findK(int[] arr, int k, int m) {
        int [] countArr = new int[32];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < countArr.length; j++) {
                countArr[j] += ((arr[i] >> j) & 1);
            }
        }
        int ret = 0;
        for (int i = 0; i < countArr.length; i++) {
            if ((countArr[i] % m) > 0)
                ret |= (1 << i);
        }

        return ret;
    }

    private static int findByHashMap(int[] arr, int k, int m) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int a : arr) {
            map.put(a, map.containsKey(a) ? map.get(a) + 1 : 1);
        }
        for (Integer aa : map.keySet()) {
            if (map.get(aa) == k)
                return aa;
        }
        return -1;
    }

}
