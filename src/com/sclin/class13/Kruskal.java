package com.sclin.class13;

import class16.*;

import java.util.*;

public class Kruskal {

    public static class UnionFind {
        HashMap<Node, Node> parent;
        HashMap<Node, Integer> sizeMap;

        public UnionFind(Collection<Node> nodeList) {
            this.parent = new HashMap<>();
            this.sizeMap = new HashMap<>();

            for (Node node : nodeList) {
                parent.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public void union(Node nodeA, Node nodeB) {
            Node node1 = find(nodeA);
            Node node2 = find(nodeB);
            if (node1 != node2) {
                int size1 = sizeMap.get(node1);
                int size2 = sizeMap.get(node2);
                if (size1 < size2) {
                    Node temp = node1;
                    node1 = node2;
                    node2 = temp;
                }
                parent.put(node2, node1);
                sizeMap.put(node1, size1 + size2);
                sizeMap.remove(node2);
            }
        }

        public boolean isSame(Node nodeA, Node nodeB) {
            return find(nodeA) == find(nodeB);
        }

        public Node find(Node node) {
            List<Node> list = new ArrayList<>();
            while (node != parent.get(node)) {
                list.add(node);
                node = parent.get(node);
            }
            // 优化对应这条路径上的节点
            for (Node node1 : list) {
                parent.put(node1, node);
            }
            return node;
        }

    }

    public static Set<Edge> kruskalMST(Graph graph) {
        // 小根堆
        Queue<Edge> queue = new PriorityQueue(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.weight - o2.weight;
            }
        });
        Set<Edge> result = new HashSet();
        for (Edge edge : graph.edges) {
            queue.add(edge);
        }
        UnionFind union = new UnionFind(graph.nodes.values());
        while (!queue.isEmpty()) {
            Edge edge = queue.poll();
            if (!union.isSame(edge.from, edge.to)) {
                result.add(edge);
                union.union(edge.from, edge.to);
            }
        }

        return result;
    }

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
        Set<Edge> s = kruskalMST(graph);
        s.forEach(edge -> System.out.println(edge.weight + " "));
    }

}
