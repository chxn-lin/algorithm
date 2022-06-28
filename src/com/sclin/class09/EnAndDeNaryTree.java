package com.sclin.class09;

import java.util.ArrayList;
import java.util.List;

public class EnAndDeNaryTree {

    // 提交时不要提交这个类
    public static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };

    // 提交时不要提交这个类
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 只提交这个类即可
    class Codec {
        // Encodes an n-ary tree to a binary tree.
        public TreeNode encode(Node root) {
            if (root == null) {
                return null;
            }
            TreeNode head = new TreeNode(root.val);
            List<Node> childrens = root.children;
            head.left = en(childrens);
            return head;
        }

        public TreeNode en(List<Node> childrens) {
            if (childrens == null || childrens.size() == 0) {
                return null;
            }
            TreeNode cur = null;
            TreeNode head = null;
            for (Node node : childrens) {
                TreeNode t = new TreeNode(node.val);
                if (head == null) {
                    head = t;
                } else {
                    cur.right = t;
                }
                cur = t;
                cur.left = en(node.children);
            }
            return head;
        }

        // Decodes your binary tree to an n-ary tree.
        public Node decode(TreeNode root) {
            if (root == null) {
                return null;
            }
            return new Node(root.val, de(root.left));
        }

        public List<Node> de(TreeNode root) {
            if (root == null) {
                return null;
            }
            List<Node> children = new ArrayList<>();
            while (root.right != null) {
                children.add(new Node(root.val, de(root.left)));
                root = root.right;
            }

            return children;
        }

    }
    
}
