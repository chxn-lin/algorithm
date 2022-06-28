package com.sclin.class12;

import java.util.*;

// 并查集
public class Union<T> {

    private Map<Node<T>, Node<T>> parent;// 存储父亲节点
    private Map<Node<T>, Integer> sizeMap;// 存储所有代表节点有多少元素
    private Map<T, Node<T>> nodes;// 存储节点对应的封装后的节点

    public Union(T[] arr) {
        this.parent = new HashMap<>();
        this.sizeMap = new HashMap<>();
        this.nodes = new HashMap<>();

        for (T obj : arr) {
            Node<T> node = new Node<>(obj);
            parent.put(node, node);
            sizeMap.put(node, 1);
            nodes.put(obj, node);
        }
    }

    // 合并
    public void union(T a, T b){
        Node<T> nodeA = find(a);
        Node<T> nodeB = find(b);
        if (nodeA != nodeB) {
            int sizeA = sizeMap.get(nodeA);
            int sizeB = sizeMap.get(nodeB);
            if (sizeA < sizeB) {
                Node<T> temp = nodeA;
                nodeA = nodeB;
                nodeB = temp;
            }
            // b小，那么将b合并到a
            parent.put(nodeB, nodeA);
            sizeMap.put(nodeA, sizeA + sizeB);
            sizeMap.remove(nodeB);
        }
    }

    // 查询是否一致
    public boolean isSame(T a, T b) {
        return find(a) == find(b);
    }

    // 找到代表节点
    private Node<T> find(T a){
        Node<T> ret = parent.get(nodes.get(a));
        List<Node<T>> list = new ArrayList<>();
        list.add(ret);
        while (parent.get(ret) != ret) {
            ret = parent.get(ret);
            list.add(ret);
        }
        // 将沿途的所有节点，直接指向代表节点
        for (Node<T> node : list) {
            parent.put(node, ret);
        }

        return ret;
    }

    public int getSize(){
        return sizeMap.keySet().size();
    }

    private class Node<T>{
        T value;
        public Node(T value) {
            this.value = value;
        }
    }

}
