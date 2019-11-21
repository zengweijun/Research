package com.nius.BinaryTree;

import com.nius.BinaryTree.printer.BinaryTrees;
import com.nius.BinaryTree.tree.BST;
import com.nius.BinaryTree.tree.BinaryTree;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode-cn.com/problems/invert-binary-tree/
public class _226_翻转二叉树 {
//       4
//      /   \
//     2     7
//    / \   / \
//   1   3 6   9

//        4
//      /   \
//     7     2
//    / \   / \
//   9   6 3   1

    // 递归版
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return root;
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    // 非递归版
    public TreeNode invertTree1(TreeNode root) {
        if (root == null) return root;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        return root;
    }
}
