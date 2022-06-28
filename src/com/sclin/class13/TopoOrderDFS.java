package com.sclin.class13;

import java.util.*;

public class TopoOrderDFS {

    // https://www.lintcode.com/problem/127/
    // 不要提交这个类
    public static class DirectedGraphNode {
        public int label;
        public ArrayList<DirectedGraphNode> neighbors;

        public DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    public static class Record{
        long num;
        DirectedGraphNode node;
        public Record(long num, DirectedGraphNode node) {
            this.num = num;
            this.node = node;
        }
    }

    // 深度优先遍历实现拓扑结构
    public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        Map<DirectedGraphNode, Long> cacheMap = new HashMap<>();

        Queue<Record> queue = new PriorityQueue(new Comparator<Record>() {
            @Override
            public int compare(Record o1, Record o2) {
                return o2.node == o1.node ? 0 : (o2.num - o1.num) > 0 ? 1 : -1;
            }
        });
        for (DirectedGraphNode node : graph) {
            queue.add(new Record(process(node, cacheMap), node));
        }
        ArrayList<DirectedGraphNode> result = new ArrayList();
        while (!queue.isEmpty()) {
            result.add(queue.poll().node);
        }
        return result;
    }

    public static long process(DirectedGraphNode node, Map<DirectedGraphNode, Long> map){
        if (map.containsKey(node)) {
            return map.get(node);
        }
        long sum = 1;
        // 没有就计算
        if (node.neighbors == null || node.neighbors.size() == 0) {
        } else {
            for (DirectedGraphNode neighbor : node.neighbors) {
                sum += process(neighbor, map);
            }
        }
        map.put(node, sum);
        return sum;
    }

    public static void main(String[] args) {

        long a = Integer.MAX_VALUE + Integer.MAX_VALUE + Integer.MAX_VALUE;
        long b = Integer.MAX_VALUE + 1;
        System.out.println(a - b);
        System.out.println((int)(a - b));

    }
    
}
