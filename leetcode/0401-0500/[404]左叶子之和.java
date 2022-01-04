//计算给定二叉树的所有左叶子之和。 
//
// 示例： 
//
// 
//    3
//   / \
//  9  20
//    /  \
//   15   7
//
//在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24 
//
// 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 
// 👍 335 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.LinkedList;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class Solution {
    public int sumOfLeftLeaves(TreeNode root) {     // 递归
        if (root == null || root.left == null && root.right == null) {
            return 0;
        }
        if (root.left != null && root.left.left == null && root.left.right == null) {
            return root.left.val + sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
        }
        return sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
    }
}

//leetcode submit region end(Prohibit modification and deletion)


class Solution {
    public int sumOfLeftLeaves(TreeNode root) {     // 迭代
        if (root == null) {
            return 0;
        }
        int count = 0;
        TreeNode node;
        LinkedList<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);
        while (!nodes.isEmpty()) {
            int num = nodes.size();
            for (int i = 0; i < num; i++) {
                node = nodes.poll();
                if (node.left != null) {
                    if (node.left.left == null && node.left.right == null) {
                        count += node.left.val;
                    }
                    else{
                        nodes.add(node.left);
                    }
                }
                if (node.right != null) {
                    nodes.add(node.right);
                }
            }
        }
        return count;
    }
}

