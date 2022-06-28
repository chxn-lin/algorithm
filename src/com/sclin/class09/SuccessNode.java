package com.sclin.class09;


import class11.Code06_SuccessorNode.Node;

public class SuccessNode {

    /**
     * 给你二叉树中的某一个节点，返回该节点的后继节点
     * @return
     */
    public static Node getSucNode(Node node){
        if (node == null) {
            return null;
        }
        Node retNode = null;

        if (node.right != null) {
            // 有右节点，那么需要往右树上面找第一个
            retNode = getMostLeft(node.right);
        } else {
            // 没有右节点，那么找第一个 父节点的左节点为当前节点的
            while (node.parent != null && node.parent.right == node) {
                node = node.parent;
            }
            retNode = node != null ? node.parent : null;
        }
        return retNode;
    }

    public static Node getMostLeft(Node node){
        if (node != null) {
            while (node.left != null) {
                node = node.left;
            }
        }
        return node;
    }

}
