//给定一个二叉树，找出其最小深度。 
//
// 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。 
//
// 说明：叶子节点是指没有子节点的节点。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [3,9,20,null,null,15,7]
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：root = [2,null,3,null,4,null,5,null,6]
//输出：5
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数的范围在 [0, 105] 内 
// -1000 <= Node.val <= 1000 
// 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 
// 👍 560 👎 0


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
    public int minDepth(TreeNode root) {    // DFS
        if (root == null) {
            return 0;
        }
        if (root.left == null) {
            if (root.right == null) {
                return 1;
            }
            return 1 + minDepth(root.right);
        }
        if (root.right == null) {
            return 1 + minDepth(root.left);
        }
        return 1 + Math.min(minDepth(root.left), minDepth(root.right));
    }
}

//leetcode submit region end(Prohibit modification and deletion)


class Solution {
    public int minDepth(TreeNode root) {    // BFS
        if (root == null) {
            return 0;
        }
        LinkedList<TreeNode> nodes = new LinkedList<>();
        int count = 0, num;
        nodes.add(root);
        while (!nodes.isEmpty()) {
            count++;
            num = nodes.size();
            for (int i = 0; i < num; i++) {
                TreeNode node = nodes.poll();
                if (node.left != null) {
                    nodes.add(node.left);
                }
                else if (node.right == null) {
                    return count;
                }
                if (node.right != null) {
                    nodes.add(node.right);
                }
            }
        }
        return count;
    }
}
