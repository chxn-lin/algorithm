package com.sclin.class13;

import class16.Code02_DFS;
import class16.Node;

import java.util.*;

public class DFS {

    public static void dfs(Node node) {
        if (node == null) {
            return ;
        }
        Set<Node> set = new HashSet<>();
        Stack<Node> stack = new Stack();
        stack.add(node);
        set.add(node);
        System.out.print(node.value + " ");
        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            for (Node nd : pop.nexts) {
                if (!set.contains(nd)) {
                    stack.push(pop);
                    stack.push(nd);
                    set.add(nd);
                    System.out.print(nd.value + " ");
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Node a1 = new Node(1);
        Node a2 = new Node(2);
        Node a3 = new Node(3);
        Node a4 = new Node(4);
        Node a5 = new Node(5);
        Node a6 = new Node(6);

        a1.nexts.add(a2);
        a1.nexts.add(a3);
        a1.nexts.add(a4);
        a4.nexts.add(a5);
        a5.nexts.add(a6);
        a6.nexts.add(a3);
        a2.nexts.add(a6);

        dfs(a1);
//        System.out.println("========");
//        Code02_DFS.dfs(a1);
    }

}
