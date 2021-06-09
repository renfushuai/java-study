package com.rfs.javastudy.suanfa;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * @author: rfs
 * @create: 2021/6/8
 * @description: 树先序遍历
 **/
public class TreePreVisit {
    @Data
    static class TreeNode {
        private TreeNode left;
        private TreeNode right;
        private Integer val;

        private TreeNode(Integer v) {
            this.val = v;
        }

        @Override
        public String toString() {
            return "val:" + this.val;
        }
    }

    private static List<TreeNode> nodeList = null;
    private Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};

    private void createBinTree() {
        nodeList = new LinkedList<>();
        for (int i = 0; i < array.length; i++) {
            nodeList.add(new TreeNode(array[i]));
        }

    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(6);
        treeNode.setLeft(new TreeNode(2));
        treeNode.right = new TreeNode(8);

    }
}
