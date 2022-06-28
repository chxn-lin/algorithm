package com.sclin.class11;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class GetMoreMoney {

    /** 正数数组costs，正数数组profits，正数K，正数M
    costs[i] 表示i号项目的花费，
    profits[i] 表示i号项目在扣除花费之后还能挣到的钱（利润）
    K表示你只能串行的最多做K个项目
            M表示你初始的资金
     */
    public static int method1(int[] costs, int[] profits, int k, int m){
        if (costs == null || costs.length < 1) {
            return 0;
        }
        PriorityQueue<MyObject> costheap = new PriorityQueue<>(new MyComp1());
        PriorityQueue<MyObject> proHeap = new PriorityQueue<>(new MyComp2());
        for (int i = 0; i < costs.length; i++) {
            costheap.add(new MyObject(costs[i], profits[i]));
        }
        int index = 0;
        int result = m;
        while (index < k && !costheap.isEmpty()) {
            while (costheap.peek().cost < m) {
                proHeap.add(costheap.poll());
            }
            if (proHeap.isEmpty()) {
                break;
            } else {
                result += proHeap.poll().profit;
            }
            index++;
        }

        return result;
    }

    private static class MyObject{
        int cost;
        int profit;
        public MyObject(int cost, int profit) {
            this.cost = cost;
            this.profit = profit;
        }
    }

    private static class MyComp1 implements Comparator<MyObject> {
        @Override
        public int compare(MyObject o1, MyObject o2) {
            return o1.cost - o2.cost;
        }
    }
    private static class MyComp2 implements Comparator<MyObject> {
        @Override
        public int compare(MyObject o1, MyObject o2) {
            return o2.profit - o1.profit;
        }
    }

}
