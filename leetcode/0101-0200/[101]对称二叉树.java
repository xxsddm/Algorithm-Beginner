//给定一个二叉树，检查它是否是镜像对称的。 
//
// 
//
// 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。 
//
//     1
//   / \
//  2   2
// / \ / \
//3  4 4  3
// 
//
// 
//
// 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的: 
//
//     1
//   / \
//  2   2
//   \   \
//   3    3
// 
//
// 
//
// 进阶： 
//
// 你可以运用递归和迭代两种方法解决这个问题吗？ 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 
// 👍 1487 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.Stack;

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
    public boolean isSymmetric(TreeNode root) {     // DFS
        return equal(root, root);
    }

    private boolean equal(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        if (node1 == null || node2 == null) {
            return false;
        }
        return node1.val == node2.val
                && equal(node1.left, node2.right)
                && equal(node1.right, node2.left);
    }
}

//leetcode submit region end(Prohibit modification and deletion)


class Solution {
    public boolean isSymmetric(TreeNode root) {     // BFS
        Stack<TreeNode> nodes = new Stack<>();
        TreeNode node1, node2;
        nodes.add(root);
        nodes.add(root);
        while (!nodes.isEmpty()) {
            node1 = nodes.pop();
            node2 = nodes.pop();
            if (node1 == null && node2 == null) {
                continue;
            }
            if (node1 == null || node2 == null || node1.val != node2.val) {
                return false;
            }
            nodes.add(node2.right);
            nodes.add(node1.left);
            nodes.add(node2.left);
            nodes.add(node1.right);
        }
        return true;
    }
}
