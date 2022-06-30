package com.sclin.class30;

public class Morris {

    private static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void morris(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            System.out.print(cur.value + " ");
            if (cur.left == null) {
                cur = cur.right;
            } else {
                mostRight = cur.left;
                while (mostRight.right != cur && mostRight.right != null) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                } else if (mostRight.right == cur) {
                    mostRight.right = null;
                    cur = cur.right;
                }

            }
        }
    }

    public static void morrisPre(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            if (cur.left == null) {
                System.out.print(cur.value + " ");
                cur = cur.right;
            } else {

                mostRight = cur.left;
                while (mostRight.right != cur && mostRight.right != null) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    System.out.print(cur.value + " ");
                    mostRight.right = cur;
                    cur = cur.left;
                } else if (mostRight.right == cur) {
                    mostRight.right = null;
                    cur = cur.right;
                }

            }
        }
    }

    public static void morrisMid(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            if (cur.left == null) {
                System.out.print(cur.value + " ");
                cur = cur.right;
            } else {

                mostRight = cur.left;
                while (mostRight.right != cur && mostRight.right != null) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                } else if (mostRight.right == cur) {
                    System.out.print(cur.value + " ");
                    mostRight.right = null;
                    cur = cur.right;
                }

            }
        }
    }

    public static void morrisEnd(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            if (cur.left == null) {
                cur = cur.right;
            } else {
                mostRight = cur.left;
                while (mostRight.right != cur && mostRight.right != null) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                } else if (mostRight.right == cur) {
                    mostRight.right = null;
                    printEdge(cur.left);
                    cur = cur.right;
                }
            }
        }
        printEdge(head);
    }

    private static void printEdge(Node node){
        node = reverse(node);
        Node cur = node;
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.right;
        }
        reverse(node);
    }

    private static Node reverse(Node node){
        Node pre = null;
        Node next = null;
        while (node != null) {
            next = node.right;
            node.right = pre;
            pre = node;
            node = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        Node node = new Node(1);
        node.left = new Node(2);
        node.right = new Node(3);
        node.left.left = new Node(4);
        node.left.right = new Node(5);
        node.right.left = new Node(6);
        node.right.right = new Node(7);

        morris(node);
        System.out.println();
        morrisPre(node);
        System.out.println();
        morrisMid(node);
        System.out.println();
        morrisEnd(node);
        System.out.println();
    }

}
