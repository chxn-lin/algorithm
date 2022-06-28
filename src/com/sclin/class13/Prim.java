package com.sclin.class13;

import class16.Edge;
import class16.Graph;
import class16.Node;

import java.util.*;

public class Prim {

    public static Set<Edge> PrimMST(Graph graph) {
        // 用于装已经涉及到的节点，作用是判断是否会生成环
        Set<Node> nodeSet = new HashSet<>();
        // 小根堆，存放所有的边
        Queue<Edge> queue = new PriorityQueue(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.weight - o2.weight;
            }
        });
        Set<Edge> result = new HashSet<>();
        for (Node node : graph.nodes.values()) {
            nodeSet.add(node);
            for (Edge edge : node.edges) {
                queue.add(edge);
            }
            while (!queue.isEmpty()) {
                Edge edge = queue.poll();
                if (nodeSet.contains(edge.from) && nodeSet.contains(edge.to)) {
                    // 不能选，选择了会形成环，所以直接退出
                } else {
                    nodeSet.add(edge.from);
                    nodeSet.add(edge.to);
                    result.add(edge);
                    for (Edge edge1 : edge.to.edges) {
                        queue.add(edge1);
                    }
                }
            }
            break;
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
        Set<Edge> s = PrimMST(graph);
        s.forEach(edge -> System.out.println(edge.weight + " "));
    }

}
