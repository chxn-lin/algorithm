package com.sclin.class10;

import class13.Code04_MaxHappy;

import static class13.Code04_MaxHappy.genarateBoss;
import static class13.Code04_MaxHappy.maxHappy1;

public class FindMaxSum {

    /*多叉树，可以选择节点，但是需要符合以下要求
           1）直接上下级不能选 （意思是选择了父节点，那么他的所有子节点不能选，但是子子节点可以）
    求选择节点的累加和最大值*/

    public static int myMaxHappy1(Code04_MaxHappy.Employee boss) {
        Info in = process(boss);
        return Math.max(in.no, in.yes);
    }

    public static Info process(Code04_MaxHappy.Employee boss){
        if (boss == null) {
            return new Info(0, 0);
        }
        int yes = boss.happy;
        int no = 0;
        for (Code04_MaxHappy.Employee em : boss.nexts) {
            Info sub = process(em);
            yes += sub.no;
            no += Math.max(sub.no, sub.yes);
        }

        return new Info(yes, no);
    }

    private static class Info{
        int yes;
        int no;
        public Info(int yes, int no){
            this.yes = yes;
            this.no = no;
        }
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxNexts = 7;
        int maxHappy = 100;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            Code04_MaxHappy.Employee boss = genarateBoss(maxLevel, maxNexts, maxHappy);
            if (maxHappy1(boss) != myMaxHappy1(boss)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
