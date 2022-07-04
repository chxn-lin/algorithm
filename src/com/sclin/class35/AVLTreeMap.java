package com.sclin.class35;

import class35.Code01_AVLTreeMap;
import class35.Code01_AVLTreeMap.AVLNode;

public class AVLTreeMap<K extends Comparable<K>, V> {

    private AVLNode<K, V> root;
    private int size;

    public AVLTreeMap() {
        root = null;
        size = 0;
    }

    private AVLNode<K, V> rightRotate(AVLNode<K, V> cur) {
        AVLNode<K, V> left = cur.l;
        cur.l = left.r;
        left.r = cur;
        cur = left;
        cur.r.h = Math.max((cur.r.l != null ? cur.r.l.h : 0), (cur.r.r != null ? cur.r.r.h : 0)) + 1;
        cur.h = Math.max((cur.l != null ? cur.l.h : 0), (cur.r != null ? cur.r.h : 0)) + 1;
        return cur;
    }

    private AVLNode<K, V> leftRotate(AVLNode<K, V> cur) {
        AVLNode<K, V> right = cur.r;
        cur.r = right.l;
        right.l = cur;
        cur = right;
        cur.l.h = Math.max((cur.l.l != null ? cur.l.l.h : 0), (cur.l.r != null ? cur.l.r.h : 0)) + 1;
        cur.h = Math.max((cur.l != null ? cur.l.h : 0), (cur.r != null ? cur.r.h : 0)) + 1;
        return cur;
    }

    private AVLNode<K, V> maintain(AVLNode<K, V> cur) {
        if (cur == null) {
            return null;
        }
        int leftHigh = cur.l != null ? cur.l.h : 0;
        int rightHigh = cur.r != null ? cur.r.h : 0;
        if (Math.abs(leftHigh - rightHigh) > 1) {
            // 违规，下面判断是哪种违规
            if (leftHigh > rightHigh) {
                int leftleftHigh = cur.l.l != null ? cur.l.l.h : 0;
                int leftrightHigh = cur.l.r != null ? cur.l.r.h : 0;
                if (leftleftHigh >= leftrightHigh) {
                    // LL
                    cur = rightRotate(cur);
                } else {
                    cur.l = leftRotate(cur.l);
                    cur = rightRotate(cur);
                }
            } else {
                int rightrightHigh = cur.r.r != null ? cur.r.r.h : 0;
                int rightleftHigh = cur.r.l != null ? cur.r.l.h : 0;
                if (rightrightHigh >= rightleftHigh) {
                    cur = leftRotate(cur);
                } else {
                    cur.r = rightRotate(cur.r);
                    cur = leftRotate(cur);
                }
            }
        }
        return cur;
    }

    private AVLNode<K, V> add(AVLNode<K, V> cur, K key, V value) {
        if (cur == null) {
            return new AVLNode<K, V>(key, value);
        } else {
            if (key.compareTo(cur.k) > 0) {
                cur.r = add(cur.r, key, value);
            } else {
                cur.l = add(cur.l, key, value);
            }
            cur.h = Math.max((cur.r != null ? cur.r.h : 0), (cur.l != null ? cur.l.h : 0)) + 1;
            return maintain(cur);
        }
    }

    private AVLNode<K, V> delete(AVLNode<K, V> cur, K key) {
        if (key.compareTo(cur.k) > 0) {
            cur.r = delete(cur.r, key);
        } else if (key.compareTo(cur.k) < 0) {
            cur.l = delete(cur.l, key);
        } else {
            if (cur.l == null && cur.r == null) {
                cur = null;
            } else if (cur.l != null && cur.r != null) {
                // 找到右树上最左的，替换掉当前的
                AVLNode<K, V> des = cur.r;
                while (des.l != null) {
                    des = des.l;
                }
                delete(cur.r, des.k);
                des.l = cur.l;
                des.r = cur.r;
                cur = des;
            } else if (cur.l != null) {
                cur = cur.l;
            } else if (cur.r != null) {
                cur = cur.r;
            }
        }
        if (cur != null) {
            cur.h = Math.max((cur.r != null ? cur.r.h : 0), (cur.l != null ? cur.l.h : 0)) + 1;
        }
        return maintain(cur);
    }

    private AVLNode<K, V> findLastIndex(K key) {
        AVLNode<K, V> pre = root;
        AVLNode<K, V> cur = root;
        while (cur != null) {
            if (cur.k == key) {
                break;
            } else if (cur.k.compareTo(key) > 0) {
                cur = cur.l;
            } else {
                cur = cur.r;
            }
        }
        return pre;
    }

    public int size() {
        return size;
    }

    public boolean containsKey(K key) {
        AVLNode<K, V> node = findLastIndex(key);
        return node != null && node.k == key ? true : false;
    }

    public void put(K key, V value) {
        if (key == null) {
            return;
        }
        AVLNode<K, V> node = findLastIndex(key);
        if (node != null && node.k == key) {
            node.v = value;
        } else {
            size++;
            root = add(root, key, value);
        }
    }

    public void remove(K key) {
        if (key == null) {
            return;
        }
        if (containsKey(key)) {
            size--;
            root = delete(root, key);
        }
    }

    public V get(K key) {
        if (key == null) {
            return null;
        }
        AVLNode<K, V> node = findLastIndex(key);
        if (node != null && node.k == key) {
            return node.v;
        }
        return null;
    }

    public static void main(String[] args) {
        AVLTreeMap<Integer, String> root = new AVLTreeMap<>();
        root.put(7, "7");
        root.put(5, "T");
        root.put(13, "13");
        root.put(4, "T1");
        root.put(6, "T2");
        root.put(10, "10");
        root.put(17, "17");
        root.put(11, "11");
        root.put(8, "8");
        root.put(9, "9");

        root.remove(7);
        System.out.println(root);
    }

}
