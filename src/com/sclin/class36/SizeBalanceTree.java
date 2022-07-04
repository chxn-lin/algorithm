package com.sclin.class36;

import class36.Code01_SizeBalancedTreeMap.SBTNode;
import com.sclin.class35.AVLTreeMap;

import static class36.Code01_SizeBalancedTreeMap.printAll;

public class SizeBalanceTree<K extends Comparable<K>, V> {

    SBTNode<K, V> root;

    private SBTNode<K, V> rightRotate(SBTNode<K, V> cur) {
        SBTNode<K, V> left = cur.l;
        cur.l = left.r;
        left.r = cur;
        cur = left;
        cur.size = cur.r.size;
        cur.r.size = (cur.r.l != null ? cur.r.l.size : 0) + (cur.r.r != null ? cur.r.r.size : 0) + 1;
        return cur;
    }

    private SBTNode<K, V> leftRotate(SBTNode<K, V> cur) {
        SBTNode<K, V> right = cur.r;
        cur.r = right.l;
        right.l = cur;
        cur = right;
        cur.size = cur.l.size;
        cur.l.size = (cur.l.l != null ? cur.l.l.size : 0) + (cur.l.r != null ? cur.l.r.size : 0) + 1;
        return cur;
    }

    private SBTNode<K, V> maintain(SBTNode<K, V> cur) {
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

    private SBTNode<K, V> findLastIndex(K key) {
        SBTNode<K, V> cur = this.root;
        SBTNode<K, V> pre = null;
        while (cur != null) {
            pre = cur;
            if (key.compareTo(cur.key) < 0) {
                cur = cur.l;
            } else if (key.compareTo(cur.key) > 0) {
                cur = cur.r;
            } else {
                break;
            }
        }
        return pre;
    }

    // 现在，以cur为头的树上，新增，加(key, value)这样的记录
    // 加完之后，会对cur做检查，该调整调整
    // 返回，调整完之后，整棵树的新头部
    private SBTNode<K, V> add(SBTNode<K, V> cur, K key, V value) {
        if (cur == null) {
            return new SBTNode<>(key, value);
        }
        cur.size++;
        if (key.compareTo(cur.key) > 0) {
            cur.r = add(cur.r, key, value);
        } else {
            cur.l = add(cur.l, key, value);
        }
        return maintain(cur);
    }

    // 在cur这棵树上，删掉key所代表的节点
    // 返回cur这棵树的新头部
    private SBTNode<K, V> delete(SBTNode<K, V> cur, K key) {
        cur.size--;
        if (key.compareTo(cur.key) < 0) {
            cur.l = delete(cur.l, key);
        } else if (key.compareTo(cur.key) > 0) {
            cur.r = delete(cur.r, key);
        } else {
            if (cur.l == null && cur.r == null) {
                cur = null;
            } else if (cur.l != null && cur.r != null) {
                // 取得右树上最左的节点，代替cur
                SBTNode<K, V> des = cur.r;
                while (des.l != null) {
                    des = des.l;
                }
                // 这边和老师写的有点不一致
                delete(cur.r, des.key);
                des.l = cur.l;
                des.r = cur.r;
                des.size = cur.size;
                cur = des;
            } else if (cur.l != null) {
                cur = cur.l;
            } else if (cur.r != null) {
                cur = cur.r;
            }
        }
        return cur;
    }

    private SBTNode<K, V> getIndex(SBTNode<K, V> cur, int kth) {
        if (kth == (cur.l != null ? cur.l.size : 0) + 1) {
            return cur;
        } else if (kth <= (cur.l != null ? cur.l.size : 0)) {
            return getIndex(cur.l, kth);
        } else {
            return getIndex(cur.r, kth - (cur.l != null ? cur.l.size : 0) - 1);
        }
    }

    public boolean containsKey(K key) {
        if (key == null) {
            return false;
        }
        SBTNode<K, V> lastIndex = findLastIndex(key);
        if (lastIndex != null && lastIndex.key.compareTo(key) == 0) {
            return true;
        }
        return false;
    }

    public void put(K key, V value) {
        if (key == null) {
            return;
        }
        SBTNode<K, V> lastIndex = findLastIndex(key);
        if (lastIndex != null && lastIndex.key.compareTo(key) == 0) {
            lastIndex.value = value;
        } else {
            this.root = add(this.root, key, value);
        }
    }

    public void remove(K key) {
        if (key == null) {
            return;
        }
        if (containsKey(key)) {
            this.root = delete(this.root, key);
        }
    }

    public int size(){
        return root != null ? root.size : 0;
    }

    public K getIndexKey(int index) {
        if (index < 0 || index >= this.size()) {
            throw new RuntimeException("invalid parameter.");
        }
        return getIndex(root, index + 1).key;
    }

    public V getIndexValue(int index) {
        if (index < 0 || index >= this.size()) {
            throw new RuntimeException("invalid parameter.");
        }
        return getIndex(root, index + 1).value;
    }

    public K firstKey() {
        if (root == null) {
            return null;
        }
        SBTNode<K, V> cur = root;
        while (cur.l != null) {
            cur = cur.l;
        }
        return cur.key;
    }

    public K lastKey() {
        if (root == null) {
            return null;
        }
        SBTNode<K, V> cur = root;
        while (cur.r != null) {
            cur = cur.r;
        }
        return cur.key;
    }

    public K floorKey(K key) {
        if (key == null) {
            throw new RuntimeException("invalid parameter.");
        }
        SBTNode<K, V> lastNoBigNode = findLastNoBigIndex(key);
        return lastNoBigNode == null ? null : lastNoBigNode.key;
    }

    public K ceilingKey(K key) {
        if (key == null) {
            throw new RuntimeException("invalid parameter.");
        }
        SBTNode<K, V> lastNoSmallNode = findLastNoSmallIndex(key);
        return lastNoSmallNode == null ? null : lastNoSmallNode.key;
    }

    private SBTNode<K, V> findLastNoSmallIndex(K key) {
        SBTNode<K, V> ans = null;
        SBTNode<K, V> cur = root;
        while (cur != null) {
            if (key.compareTo(cur.key) == 0) {
                ans = cur;
                break;
            } else if (key.compareTo(cur.key) < 0) {
                ans = cur;
                cur = cur.l;
            } else {
                cur = cur.r;
            }
        }
        return ans;
    }

    private SBTNode<K, V> findLastNoBigIndex(K key) {
        SBTNode<K, V> ans = null;
        SBTNode<K, V> cur = root;
        while (cur != null) {
            if (key.compareTo(cur.key) == 0) {
                ans = cur;
                break;
            } else if (key.compareTo(cur.key) < 0) {
                cur = cur.l;
            } else {
                ans = cur;
                cur = cur.r;
            }
        }
        return ans;
    }

    public V get(K key) {
        if (key == null) {
            return null;
        }
        SBTNode<K, V> lastIndex = findLastIndex(key);
        if (lastIndex != null && lastIndex.key.compareTo(key) == 0) {
            return lastIndex.value;
        }
        return null;
    }

    public static void main(String[] args) {
        SizeBalanceTree<Integer, String> root = new SizeBalanceTree<>();
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
//        System.out.println(root);
//        System.out.println(root.firstKey());
//        System.out.println(root.lastKey());
//        System.out.println(root.getIndexKey(1));
//        System.out.println(root.getIndexValue(1));
        System.out.println(root.floorKey(3));
        System.out.println(root.floorKey(18));
        System.out.println(root.ceilingKey(3));
        System.out.println(root.ceilingKey(18));
    }

}
