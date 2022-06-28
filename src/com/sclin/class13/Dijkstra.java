package com.sclin.class13;

import class16.Edge;
import class16.Graph;
import class16.Node;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Dijkstra {

    public static HashMap<Node, Integer> dijkstra1(Node from) {
        HashMap<Node, Integer> map = new HashMap<>();
        map.put(from, 0);
        Set<Node> set = new HashSet<>();// 已经使用的节点
        Node minNode = getMinNode(map, set);
        while (minNode != null) {
            for (Edge edge : minNode.edges) {
                map.put(edge.to, map.get(edge.to) == null ?
                        map.get(minNode) + edge.weight : Math.min(map.get(minNode) + edge.weight, map.get(edge.to)));
            }
            set.add(minNode);
            minNode = getMinNode(map, set);
        }

        return map;
    }

    private static Node getMinNode(HashMap<Node, Integer> map, Set<Node> set) {
        Node result = null;
        int distance = Integer.MAX_VALUE;
        for (Node node : map.keySet()) {
            if (!set.contains(node) && map.get(node) < distance) {
                result = node;
                distance = map.get(node);
            }
        }
        return result;
    }

    public static class MyHeap {

        private Node[] nodes;
        private int size;
        private HashMap<Node, Integer> indexMap;
        private HashMap<Node, Integer> distanceMap;

//        public MyHeap(Node[] nodes) {
//            size = nodes.length;
//            this.nodes = new Node[nodes.length];
//            indexMap = new HashMap<>();
//            distanceMap = new HashMap<>();
//
//            for (int i = size - 1; i >= 0; i--) {
//                this.nodes[i] = nodes[i];
//                heapify(i, size);
//            }
//        }

        public MyHeap(int size) {
            this.size = 0;
            this.nodes = new Node[size];
            this.distanceMap = new HashMap<>();
            this.indexMap = new HashMap<>();
        }

        public boolean isEmpty(){
            return size == 0;
        }

        public void addOrUpdate(Node node, int distance){
            // 新增
            if (!indexMap.containsKey(node)) {
                nodes[size] = node;
                indexMap.put(node, size);
                distanceMap.put(node, distance);
                heapInsert(size++);
            } else {
                if (distance != distanceMap.get(node)) {
                    heapInsert(indexMap.get(node));
                    heapify(indexMap.get(node), size);
                }
            }
        }

        // 弹出一个最小的
        public Node pop(){
            if (isEmpty()) {
                return null;
            }
            sw(0, size - 1);
            Node retNode = nodes[size - 1];
            heapify(0,size--);
            indexMap.remove(retNode);
            return retNode;
        }

        // 向下调整
        private void heapify(int curIndex, int length) {
            int sonIndex = curIndex * 2 + 1;
            int smallOne = sonIndex;
            while (sonIndex < length && distanceMap.get(nodes[curIndex]) < distanceMap.get(nodes[sonIndex])) {
                if (sonIndex + 1 < length) {
                    smallOne = distanceMap.get(nodes[sonIndex]) < distanceMap.get(nodes[sonIndex + 1]) ? sonIndex : sonIndex + 1;
                }
                if (distanceMap.get(nodes[smallOne]) < distanceMap.get(nodes[curIndex])) {
                    sw(smallOne, curIndex);
                    curIndex = smallOne;
                    sonIndex = curIndex * 2 + 1;
                }
            }
        }

        // 向上调整
        private void heapInsert(int index) {
            int fatherIndex = (index - 1) / 2;
            while (distanceMap.get(nodes[index]) < distanceMap.get(nodes[fatherIndex])) {
                sw(fatherIndex, index);
                index = fatherIndex;
                fatherIndex = (index - 1) / 2;
            }
        }

        private void sw(int smallOne, int curIndex) {
            indexMap.put(nodes[smallOne], curIndex);
            indexMap.put(nodes[curIndex], smallOne);
            Node temp = nodes[smallOne];
            nodes[smallOne] = nodes[curIndex];
            nodes[curIndex] = temp;
        }

    }

    public static HashMap<Node, Integer> dijkstra2(Node from) {
        HashMap<Node, Integer> map = new HashMap<>();
        map.put(from, 0);
        Set<Node> set = new HashSet<>();// 已经使用的节点
        MyHeap heap = new MyHeap(100);
        heap.addOrUpdate(from, 0);
        Node minNode = heap.pop();
        while (minNode != null) {
            if (!set.contains(minNode)) {
                for (Edge edge : minNode.edges) {
                    int dis = map.get(edge.to) == null ?
                            map.get(minNode) + edge.weight : Math.min(map.get(minNode) + edge.weight, map.get(edge.to));
                    map.put(edge.to, dis);
                    heap.addOrUpdate(edge.to, dis);
                }
                set.add(minNode);
            }
            minNode = heap.pop();
        }

        return map;
    }

    /*
    5 : 29
    1 : 0
    4 : 11
    2 : 45
    3 : 23
     */
    public static void main(String[] args) {
        int[][] arr = {
                {45, 1, 2},
                {11, 1, 4},
                {23, 1, 3},
                {81, 2, 3},
                {79, 3, 4},
                {25, 3, 5},
                {18, 4, 5},
        };
        Graph graph = MyGraphGenerator.createGraph(arr);
        HashMap<Node, Integer> s = dijkstra2(graph.nodes.get(1));
        for (Node node : s.keySet()) {
            System.out.println(node.value + " : " + s.get(node));
        }
    }

}
