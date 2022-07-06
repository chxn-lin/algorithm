package com.sclin.class37;

import static com.sclin.class04.SumBetweenLowAndUp.myCountRangeSum;

public class SubArrNum {

    //    给定一个数组arr，和两个整数a和b（a < b）,
//    求arr中有多少个子数组，累加和再 [a, b] 这个范围上，
//    返回达标的子数组数量。
    public static int getSubArrNum(int[] arr, int a, int b) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        long[] sumArr = new long[arr.length];
        sumArr[0] = arr[0];
        for (int i = 1; i < sumArr.length; i++) {
            sumArr[i] = arr[i] + sumArr[i - 1];
        }
        int res = 0;
        SizeBalanceTree tree = new SizeBalanceTree();
        tree.put(0);
        for (int i = 0; i < sumArr.length; i++) {
            int num1 = tree.getLessKeyNum(sumArr[i] - b);
            int num2 = tree.getLessKeyNum(sumArr[i] - a + 1);
            res += num2 - num1;
            tree.put(sumArr[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1,4,3,4,-2,3,24,-12,41,32};
        int a = 10;
        int b = 20;
        System.out.println("true:" + myCountRangeSum(arr, a, b));
        System.out.println("test:" + getSubArrNum(arr, a, b));
    }

    private static class SBTNode {
        int size;
        int all;
        long key;
        SBTNode l;
        SBTNode r;

        public SBTNode(long key) {
            this.size = 1;
            this.all = 1;
            this.key = key;
        }
    }

    private static class SizeBalanceTree {
        private SBTNode root;

        private SBTNode leftRotate(SBTNode cur) {
            SBTNode right = cur.r;
            int sameNum = cur.all - right.all - (cur.l != null ? cur.l.all : 0);
            cur.r = right.l;
            right.l = cur;
            right.all = cur.all;
            right.size = cur.size;
            cur.all = sameNum + (cur.r != null ? cur.r.all : 0) + (cur.l != null ? cur.l.all : 0);
            cur.size = 1 + (cur.r != null ? cur.r.size : 0) + (cur.l != null ? cur.l.size : 0);
            cur = right;
            return cur;
        }

        private SBTNode rightRotate(SBTNode cur) {
            SBTNode left = cur.l;
            int sameNum = cur.all - left.all - (cur.r != null ? cur.r.all : 0);
            cur.l = left.r;
            left.r = cur;
            left.all = cur.all;
            left.size = cur.size;
            cur.all = sameNum + (cur.r != null ? cur.r.all : 0) + (cur.l != null ? cur.l.all : 0);
            cur.size = 1 + (cur.r != null ? cur.r.size : 0) + (cur.l != null ? cur.l.size : 0);
            cur = left;
            return cur;
        }

        private SBTNode maintate(SBTNode cur){
            int leftSize = cur.l != null ? cur.l.size : 0;
            int leftleftSize = cur.l != null && cur.l.l != null ? cur.l.l.size : 0;
            int leftrightSize = cur.l != null && cur.l.r != null ? cur.l.r.size : 0;
            int rightSize = cur.r != null ? cur.r.size : 0;
            int rightrightSize = cur.r != null && cur.r.r != null ? cur.r.r.size : 0;
            int rightleftSize = cur.r != null && cur.r.l != null ? cur.r.l.size : 0;
            if (leftleftSize > rightSize) {
                // LL
                cur = rightRotate(cur);
                cur.r = maintate(cur.r);
                cur = maintate(cur);
            } else if (leftrightSize > rightSize) {
                // LR
                cur.l = leftRotate(cur.l);
                cur = rightRotate(cur);
                cur.l = maintate(cur.l);
                cur.r = maintate(cur.r);
                cur = maintate(cur);
            } else if (rightrightSize > leftSize) {
                // RR
                cur = leftRotate(cur);
                cur.l = maintate(cur.l);
                cur = maintate(cur);
            } else if (rightleftSize > leftSize) {
                // RL
                cur.r = rightRotate(cur.r);
                cur = leftRotate(cur);
                cur.l = maintate(cur.l);
                cur.r = maintate(cur.r);
                cur = maintate(cur);
            }
            return cur;
        }

        private SBTNode findLastIndex(long key){
            SBTNode cur = this.root;
            SBTNode pre = null;
            while (cur != null) {
                pre = cur;
                if (key < cur.key) {
                    cur = cur.l;
                } else if (key > cur.key) {
                    cur = cur.r;
                } else {
                    break;
                }
            }
            return pre;
        }

        private boolean constantKey(long key){
            SBTNode lastIndex = findLastIndex(key);
            return lastIndex != null && lastIndex.key == key;
        }

        public void put(long key){
            root = add(root, key, constantKey(key));
        }

        public int getLessKeyNum(long key){
            return getLessKeyNum(root, key);
        }

        public int getLessKeyNum(SBTNode cur, long key){
            if (cur == null) {
                return 0;
            }
            int res = 0;
            if (cur.key == key) {
                res = cur.l != null ? cur.l.all : 0;
            } else if (key > cur.key) {
                res = cur.all - (cur.r != null ? cur.r.all : 0);
                res += getLessKeyNum(cur.r, key);
            } else {
                res = getLessKeyNum(cur.l, key);
            }
            return res;
        }

        private SBTNode add(SBTNode cur, long key, boolean constant) {
            if (cur == null) {
                return new SBTNode(key);
            }
            cur.all++;
            if (cur.key == key) {
                return cur;
            } else {
                if (!constant) {
                    cur.size++;
                }
                if (key < cur.key) {
                    cur.l = add(cur.l, key, constant);
                } else if (key > cur.key) {
                    cur.r = add(cur.r, key, constant);
                }
                return maintate(cur);
            }
        }

    }

}
