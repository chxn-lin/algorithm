package com.sclin.class06;

import class07.Code02_EveryStepShowBoss;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EveryStepShowBoss {

    public static void main(String[] args) {
        int[] arr = {3, 51, 2, 51, 2, 5, 3, 2};
        boolean[] tran = {true, true, false, false, true, false, true, true};
        int k = 2;

        List<List<Integer>> retList = showBoss(arr, tran, k);
        System.out.println(retList);
    }

    // 主函数
    private static List<List<Integer>> showBoss(int[] arr, boolean[] tran, int k) {

        return null;
    }

    public static class Customer {
        public int id;
        public int buy;
        public int enterTime;

        public Customer(int v, int b, int o) {
            id = v;
            buy = b;
            enterTime = 0;
        }
    }

    public static class CandidateComparator implements Comparator<Code02_EveryStepShowBoss.Customer> {
        @Override
        public int compare(Code02_EveryStepShowBoss.Customer o1, Code02_EveryStepShowBoss.Customer o2) {
            return o1.buy != o2.buy ? (o2.buy - o1.buy) : (o1.enterTime - o2.enterTime);
        }
    }

    public static class DaddyComparator implements Comparator<Code02_EveryStepShowBoss.Customer> {
        @Override
        public int compare(Code02_EveryStepShowBoss.Customer o1, Code02_EveryStepShowBoss.Customer o2) {
            return o1.buy != o2.buy ? (o1.buy - o2.buy) : (o1.enterTime - o2.enterTime);
        }
    }

}
