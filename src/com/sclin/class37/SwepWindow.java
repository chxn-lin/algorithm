package com.sclin.class37;

import class37.Code02_SlidingWindowMedian;

public class SwepWindow {

    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        int k = 4;
        System.out.println("true:");
        System.out.println();
        printArr(Code02_SlidingWindowMedian.medianSlidingWindow(nums, k));
        System.out.println();
        System.out.println("my  :");
        printArr(medianSlidingWindow(nums, k));
    }

    public static void printArr(double[] arr){
        System.out.print("[");
        for (double v : arr) {
            System.out.print(v + ", ");
        }
        System.out.print("]");
    }

    public static double[] medianSlidingWindow(int[] nums, int k) {
        double[] res = new double[nums.length - k + 1];
        SizeBalanceTree tree = new SizeBalanceTree();
        // 将k个数字加入到tree中，开始查中位数
        for (int i = 0; i < k; i++) {
            tree.put(nums[i]);
        }
        int index = 0;
        double mid = 0;
        if (k%2 == 1) {
            mid = tree.getIndexValue(k/2);
        } else {
            mid = ((double)tree.getIndexValue(k/2) + tree.getIndexValue((k-1)/2))/2;
        }
        res[index++] = mid;
        for (int i = k; i < nums.length; i++) {
            tree.put(nums[i]);
            tree.delete(nums[i - k]);
            if (k%2 == 1) {
                mid = tree.getIndexValue(k/2);
            } else {
                mid = (tree.getIndexValue(k/2) + tree.getIndexValue((k-1)/2))/2;
            }
            res[index++] = mid;
        }
        return res;
    }

    private static class SizeBalanceTree {

        private SBTNode root;

        private SBTNode rightRotate(SBTNode cur) {
            SBTNode left = cur.l;
            int sameNum = cur.num - (cur.l != null ? cur.l.num : 0) - (cur.r != null ? cur.r.num : 0);
            cur.l = left.r;
            left.r = cur;
            left.num = cur.num;
            left.size = cur.size;
            cur.num = sameNum + (cur.l != null ? cur.l.num : 0) + (cur.r != null ? cur.r.num : 0);
            cur.size = 1 + (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0);
            return left;
        }

        private SBTNode leftRotate(SBTNode cur) {
            SBTNode right = cur.r;
            int sameNum = cur.num - (cur.l != null ? cur.l.num : 0) - (cur.r != null ? cur.r.num : 0);
            cur.r = right.l;
            right.l = cur;
            right.num = cur.num;
            right.size = cur.size;
            cur.num = sameNum + (cur.l != null ? cur.l.num : 0) + (cur.r != null ? cur.r.num : 0);
            cur.size = 1 + (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0);
            return right;
        }

        private SBTNode maintate(SBTNode cur) {
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

        private SBTNode findLastIndex(int key) {
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

        private boolean constant(int key) {
            SBTNode lastIndex = findLastIndex(key);
            return lastIndex != null && lastIndex.key == key;
        }

        public void put(int key) {
            root = add(root, key, constant(key));
        }

        public void delete(int key){
            SBTNode lastIndex = findLastIndex(key);
            if (lastIndex != null && lastIndex.key == key) {
                boolean needDownSize = false;
                if (lastIndex.num > 1) {
                } else {
                    needDownSize = true;
                }
                root = delete(root, key, needDownSize);
            }
        }

        public int getIndexValue(int index){
            if (index >= root.num || index < 0) {
                throw new RuntimeException("操作错误！");
            }
            return getIndexValue(root, index);
        }

        private int getIndexValue(SBTNode cur, int index){
            int leftNum = cur.l != null ? cur.l.num : 0;
            int rightNum = cur.r != null ? cur.r.num : 0;
            int res = 0;
            if (leftNum >= index + 1) {
                res = getIndexValue(cur.l, index);
            } else if (cur.num - rightNum - 1 < index) {
                res = getIndexValue(cur.r, index - (cur.num - rightNum));
            } else {
                res = cur.key;
            }
            return res;
        }

        private SBTNode delete(SBTNode cur, int key, boolean needDownSize){
            cur.num--;
            if (needDownSize) {
                cur.size--;
            }
            if (key < cur.key) {
                cur.l = delete(cur.l, key, needDownSize);
            } else if (key > cur.key) {
                cur.r = delete(cur.r, key, needDownSize);
            } else if (cur.key == key) {
                if (cur.size == 0) {
                    // 删除节点
                    if (cur.l == null && cur.r == null) {
                        cur = null;
                    } else if (cur.l != null && cur.r != null) {
                        // 删除右树上的最左节点
                        SBTNode des = cur.r;
                        while (des.l != null) {
                            des = des.l;
                        }
                        delete(cur.r, des.key, true);
                        des.l = cur.l;
                        des.r = cur.r;
                        des.size = cur.size;
                        des.num = cur.num;
                        cur = des;
                    } else if (cur.l != null) {
                        cur = cur.l;
                    } else if (cur.r != null) {
                        cur = cur.r;
                    }
                }
            }
            return cur;
        }

        private SBTNode add(SBTNode cur, int key, boolean constant) {
            if (cur == null) {
                return new SBTNode(key);
            }
            cur.num++;
            if (!constant) {
                cur.size++;
            }
            if (key == cur.key) {
                return cur;
            } else if (key < cur.key) {
                cur.l = add(cur.l, key, constant);
            } else if (key > cur.key) {
                cur.r = add(cur.r, key, constant);
            }
            return maintate(cur);
        }

        private static class SBTNode {
            SBTNode l;
            SBTNode r;
            int size;// 平衡因子
            int key;
            int num;// 有多少个数字经过了这边

            public SBTNode(int key) {
                this.key = key;
                this.size = 1;
                this.num = 1;
            }
        }
    }
}
