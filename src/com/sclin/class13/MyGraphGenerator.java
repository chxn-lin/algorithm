package com.sclin.class13;

import class16.Edge;
import class16.Graph;
import class16.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class MyGraphGenerator {

    public static Graph createGraph(int[][] matrix) {
        if (matrix == null) {
            return null;
        }
        HashSet<Edge> edges = new HashSet<>();
        HashMap<Integer, Node> nodes = new HashMap<>();
        for (int i = 0; i < matrix.length; i++) {
            int weight = matrix[i][0];
            int fromValue = matrix[i][1];
            int toValue = matrix[i][2];
            // 如果没有，那么将节点建出来
            if (nodes.get(fromValue) == null) {
                nodes.put(fromValue, new Node(fromValue));
            }
            if (nodes.get(toValue) == null) {
                nodes.put(toValue, new Node(toValue));
            }
            Node fromNode = nodes.get(fromValue);
            Node toNode = nodes.get(toValue);
            Edge edge = new Edge(weight, fromNode, toNode);
            edges.add(edge);
            fromNode.nexts.add(toNode);
            fromNode.out++;
            fromNode.edges.add(edge);
            toNode.in++;
        }

        Graph graph = new Graph();
        graph.nodes = nodes;
        graph.edges = edges;

        return graph;
    }

}
