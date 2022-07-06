package com.sclin.class37;

import class37.Code03_AddRemoveGetIndexGreat;

import java.util.ArrayList;

public class MakeListCode {

    public static void main(String[] args) {
//         功能测试
        int test = 50000;
        int max = 20;
        boolean pass = true;
        ArrayList<Integer> list = new ArrayList<>();
        MyList<Integer> sbtList = new MyList<>();

//        sbtList.add(0, 16);
//        sbtList.add(0, 18);
//        sbtList.add(2, 1);
//        sbtList.remove(1);
//        System.out.println(sbtList.get(0));
//        System.out.println(sbtList.get(1));

        for (int i = 0; i < test; i++) {
            if (list.size() != sbtList.size()) {
                pass = false;
                System.out.println("========数量不一致！");
                break;
            }
            if (list.size() > 1 && Math.random() < 0.5) {
                int removeIndex = (int) (Math.random() * list.size());
                System.out.println("del index " + removeIndex);
                list.remove(removeIndex);
                sbtList.remove(removeIndex);
            } else {
                int randomIndex = (int) (Math.random() * (list.size() + 1));
                int randomValue = (int) (Math.random() * (max + 1));
                System.out.println("add index " + randomIndex + " : " + randomValue);
                list.add(randomIndex, randomValue);
                sbtList.add(randomIndex, randomValue);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).equals(sbtList.get(i))) {
                pass = false;
                break;
            }
        }
        System.out.println("功能测试是否通过 : " + pass);
    }

    public static class MyList<V> {

        private SBTNode<V> root;

        public void add(int index, V num) {
            if ((root == null && index != 0)
                    || (root != null && root.size < index)) {
                throw new RuntimeException("插入位置错误！");
            }
            SBTNode newNode = new SBTNode(num);
            this.root = add(root, index, newNode);
        }

        public V get(int index) {
            SBTNode<V> cur = this.root;
            if (index > cur.size - 1 || index < 0) {
                throw new RuntimeException("越界！");
            }
            return get(cur, index);
        }

        private V get(SBTNode<V> cur, int index) {
            int leftSize = cur.l != null ? cur.l.size : 0;
            if (index == leftSize) {
                return cur.value;
            } else if (index < leftSize) {
                return get(cur.l, index);
            } else {
                return get(cur.r, index - leftSize - 1);
            }
        }

        public void remove(int index) {
            SBTNode<V> cur = this.root;
            if (index > cur.size - 1 || index < 0) {
                throw new RuntimeException("越界！");
            }
            this.root = remove(cur, index);
        }

        private SBTNode<V> remove(SBTNode<V> root, int index) {
            root.size--;
            int rootIndex = root.l != null ? root.l.size : 0;
            if (index != rootIndex) {
                if (index < rootIndex) {
                    root.l = remove(root.l, index);
                } else {
                    root.r = remove(root.r, index - rootIndex - 1);
                }
                return root;
            }
            if (root.l == null && root.r == null) {
                return null;
            }
            if (root.l == null) {
                return root.r;
            }
            if (root.r == null) {
                return root.l;
            }
            SBTNode<V> pre = null;
            SBTNode<V> suc = root.r;
            suc.size--;
            while (suc.l != null) {
                pre = suc;
                suc = suc.l;
                suc.size--;
            }
            if (pre != null) {
                pre.l = suc.r;
                suc.r = root.r;
            }
            suc.l = root.l;
            suc.size = suc.l.size + (suc.r == null ? 0 : suc.r.size) + 1;
            return suc;
        }

        public int size() {
            return root == null ? 0 : root.size;
        }

        public SBTNode add(SBTNode cur, int index, SBTNode newNode) {
            if (cur == null) {
                return newNode;
            }
            cur.size++;
            int leftSize = cur.l != null ? cur.l.size : 0;
            if (index <= leftSize) {
                cur.l = add(cur.l, index, newNode);
            } else {
                cur.r = add(cur.r, index - leftSize - 1, newNode);
            }
            return maintain(cur);
        }

        private SBTNode rightRotate(SBTNode cur) {
            SBTNode left = cur.l;
            cur.l = left.r;
            left.r = cur;
            left.size = cur.size;
            cur.size = 1 + (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0);
            return left;
        }

        private SBTNode leftRotate(SBTNode cur) {
            SBTNode right = cur.r;
            cur.r = right.l;
            right.l = cur;
            right.size = cur.size;
            cur.size = 1 + (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0);
            return right;
        }

        private SBTNode maintain(SBTNode cur) {
            int leftSize = cur.l != null ? cur.l.size : 0;
            int rightSize = cur.r != null ? cur.r.size : 0;
            int leftleftSize = cur.l != null && cur.l.l != null ? cur.l.l.size : 0;
            int leftrightSize = cur.l != null && cur.l.r != null ? cur.l.r.size : 0;
            int rightrightSize = cur.r != null && cur.r.r != null ? cur.r.r.size : 0;
            int rightleftSize = cur.r != null && cur.r.l != null ? cur.r.l.size : 0;
            if (leftleftSize > rightSize) {
                // LL型
                cur = rightRotate(cur);
                cur.l = maintain(cur.l);
                cur = maintain(cur);
            } else if (leftrightSize > rightSize) {
                // LR型
                cur.l = leftRotate(cur.l);
                cur = rightRotate(cur);
                cur.l = maintain(cur.l);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            } else if (rightrightSize > leftSize) {
                // RR型
                cur = leftRotate(cur);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            } else if (rightleftSize > leftSize) {
                // RL型
                cur.r = rightRotate(cur.r);
                cur = leftRotate(cur);
                cur.l = maintain(cur.l);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            }
            return cur;
        }


        private static class SBTNode<V> {
            SBTNode<V> l;
            SBTNode<V> r;
            V value;
            int size;

            public SBTNode(V value) {
                this.value = value;
                this.size = 1;
            }
        }
    }

}
