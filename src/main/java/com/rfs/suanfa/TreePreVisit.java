package com.rfs.suanfa;

import lombok.Data;

import java.util.ArrayList;
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
        private int val;

        private TreeNode(int v) {
            this.val = v;
        }

        @Override
        public String toString() {
            return "val:" + this.val;
        }
    }

    private static TreeNode createBinTree(int[] array) {
        List<TreeNode> nodeList = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            nodeList.add(new TreeNode(array[i]));
        }
        for (int i = 0; i < array.length / 2 - 1; i++) {
            TreeNode treeNode = nodeList.get(i);
            treeNode.left = nodeList.get(i * 2 + 1);
            treeNode.right = nodeList.get(i * 2 + 1);
        }
        int lastPNodeIndex = array.length / 2 - 1;
        TreeNode lastTreeNode = nodeList.get(lastPNodeIndex);
        if (array.length % 2 != 0) {
            lastTreeNode.right = nodeList.get(lastPNodeIndex * 2 + 2);
        }
        return nodeList.get(0);
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        TreeNode binTree = createBinTree(array);
        System.out.println(binTree.toString());
    }
}
