package com.sclin.class36;

import class36.Code02_SkipListMap;
import class36.Code02_SkipListMap.SkipListNode;

public class SkipListMap <K extends Comparable<K>, V>{

    private static final double PROBABILITY = 0.5; // < 0.5 继续做，>=0.5 停
    private SkipListNode<K, V> head;
    private int size;
    private int maxLevel;

    public SkipListMap() {
        this.head = new SkipListNode<>(null, null);
        this.head.nextNodes.add(null);
        this.size = 0;
        this.maxLevel = 0;
    }

    // 从最高层开始，一路找下去，
    // 最终，找到第0层的<key的最右的节点
    private SkipListNode<K, V> mostRightLessNodeInTree(K key) {
        if (key == null) {
            return null;
        }
        int level = maxLevel;
        SkipListNode<K, V> cur = this.head;
        while (level >= 0) {
            cur = mostRightLessNodeInLevel(key, cur, level--);
        }
        return cur;
    }

    // 在level层里，如何往右移动
    // 现在来到的节点是cur，来到了cur的level层，在level层上，找到<key最后一个节点并返回
    private SkipListNode<K, V> mostRightLessNodeInLevel(K key,
                                                        SkipListNode<K, V> cur,
                                                        int level) {
        SkipListNode<K, V> next = cur.nextNodes.get(level);
        while (next != null && next.isKeyLess(key)) {
            cur = next;
            next = cur.nextNodes.get(level);
        }
        return cur;
    }

    public boolean containsKey(K key) {
        if (key == null) {
            return false;
        }
        SkipListNode<K, V> less = mostRightLessNodeInTree(key);
        SkipListNode<K, V> next = less.nextNodes.get(0);
        return next != null && next.isKeyEqual(key);
    }

    public void put(K key, V value) {
        if (key == null) {
            return ;
        }
        SkipListNode<K, V> less = mostRightLessNodeInTree(key);
        SkipListNode<K, V> find = less.nextNodes.get(0);
        if (find != null && find.isKeyEqual(key)) {
            find.val = value;
        } else {
            size++;
            int level = 0;
            while (Math.random() < PROBABILITY) {
                level++;
            }
            while (level > maxLevel) {
                head.nextNodes.add(null);
                maxLevel++;
            }
            SkipListNode<K, V> newNode = new SkipListNode<>(key, value);
            for (int i = 0; i <= level; i++) {
                newNode.nextNodes.add(null);
            }
            SkipListNode<K, V> pre = this.head;
            while (level >= 0) {
                pre = mostRightLessNodeInLevel(key, pre, level);
                if (level <= maxLevel) {
                    newNode.nextNodes.set(level, pre.nextNodes.get(level));
                    pre.nextNodes.set(level, newNode);
                }
                level--;
            }
        }
    }

    public V get(K key) {
        if (key == null) {
            return null;
        }
        SkipListNode<K, V> less = mostRightLessNodeInTree(key);
        SkipListNode<K, V> find = less.nextNodes.get(0);
        if (find != null && find.isKeyEqual(key)) {
            return find.val;
        }
        return null;
    }

    public void remove(K key) {
        if (containsKey(key)) {
            size--;
            int level = maxLevel;
            SkipListNode<K, V> pre = this.head;
            while (level >= 0) {
                pre = mostRightLessNodeInLevel(key, pre, level);
                SkipListNode<K, V> next = pre.nextNodes.get(level);
                if (next != null && next.isKeyEqual(key)) {
                    pre.nextNodes.set(level, next.nextNodes.get(level));
                }
                if (level != 0 && pre == head && pre.nextNodes.get(level) == null) {
                    head.nextNodes.remove(level);
                    maxLevel--;
                }
                level--;
            }
        }
    }

    public K firstKey() {
        return head.nextNodes.get(0) != null ? head.nextNodes.get(0).key : null;
    }

    public K lastKey() {
        int level = maxLevel;
        SkipListNode<K, V> cur = head;
        while (level >= 0) {
            SkipListNode<K, V> next = cur.nextNodes.get(level);
            while (next != null) {
                cur = next;
                next = cur.nextNodes.get(level);
            }
            level--;
        }
        return cur.key;
    }

    public K ceilingKey(K key) {
        if (key == null) {
            return null;
        }
        SkipListNode<K, V> less = mostRightLessNodeInTree(key);
        SkipListNode<K, V> next = less.nextNodes.get(0);
        return next != null ? next.key : null;
    }

    public K floorKey(K key) {
        if (key == null) {
            return null;
        }
        SkipListNode<K, V> less = mostRightLessNodeInTree(key);
        SkipListNode<K, V> next = less.nextNodes.get(0);
        return next != null && next.isKeyEqual(key) ? next.key : less.key;
    }

    public int size() {
        return size;
    }

    public static void printAll(SkipListMap<String, String> obj) {
        for (int i = obj.maxLevel; i >= 0; i--) {
            System.out.print("Level " + i + " : ");
            SkipListNode<String, String> cur = obj.head;
            while (cur.nextNodes.get(i) != null) {
                SkipListNode<String, String> next = cur.nextNodes.get(i);
                System.out.print("(" + next.key + " , " + next.val + ") ");
                cur = next;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        SkipListMap<String, String> test = new SkipListMap<>();
        printAll(test);
        System.out.println("======================");
        test.put("A", "10");
        printAll(test);
        System.out.println("======================");
        test.remove("A");
        printAll(test);
        System.out.println("======================");
        test.put("E", "E");
        test.put("B", "B");
        test.put("A", "A");
        test.put("F", "F");
        test.put("C", "C");
        test.put("D", "D");
        printAll(test);
        System.out.println("======================");
        System.out.println(test.containsKey("B"));
        System.out.println(test.containsKey("Z"));
        System.out.println(test.firstKey());
        System.out.println(test.lastKey());
        System.out.println(test.floorKey("D"));
        System.out.println(test.ceilingKey("D"));
        System.out.println("======================");
        test.remove("D");
        printAll(test);
        System.out.println("======================");
        System.out.println(test.floorKey("D"));
        System.out.println(test.ceilingKey("D"));


    }

}
