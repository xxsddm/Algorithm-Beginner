//给定一个二叉树，判断其是否是一个有效的二叉搜索树。 
//
// 假设一个二叉搜索树具有如下特征： 
//
// 
// 节点的左子树只包含小于当前节点的数。 
// 节点的右子树只包含大于当前节点的数。 
// 所有左子树和右子树自身必须也是二叉搜索树。 
// 
//
// 示例 1: 
//
// 输入:
//    2
//   / \
//  1   3
//输出: true
// 
//
// 示例 2: 
//
// 输入:
//    5
//   / \
//  1   4
//     / \
//    3   6
//输出: false
//解释: 输入为: [5,1,4,null,null,3,6]。
//     根节点的值为 5 ，但是其右子节点值为 4 。
// 
// Related Topics 树 深度优先搜索 二叉搜索树 二叉树 👍 1175 👎 0


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
    public boolean isValidBST(TreeNode root) {      // 迭代
        if (root == null) {
            return true;
        }
        long maxnum = Long.MIN_VALUE;
        Stack<TreeNode> container = new Stack<>();
        container.add(root);
        while (!container.isEmpty()) {
            TreeNode node = container.pop();
            if (node != null) {
                if (node.right != null) {
                    container.add(node.right);
                }
                container.add(node);
                container.add(null);
                if (node.left != null) {
                    container.add(node.left);
                }
            }
            else {
                node = container.pop();
                if (node.val <= maxnum) {
                    return false;
                }
                maxnum = node.val;
            }
        }
        return true;
    }
}

//leetcode submit region end(Prohibit modification and deletion)


class Solution {
    long maxnum = Long.MIN_VALUE;

    public boolean isValidBST(TreeNode root) {      // 递归, 利用中序遍历性质, 前面访问的节点一定在后面访问节点的left侧
        return dfs(root);
    }

    private boolean dfs(TreeNode node) {
        if (node == null) {
            return true;
        }
        if (!dfs(node.left)) {
            return false;
        }
        if (node.val <= maxnum) {
            return false;
        }
        maxnum = node.val;
        if (!dfs(node.right)) {
            return false;
        }
        return true;
    }
}
