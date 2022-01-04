//给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。 
//
// 
//
// 示例： 
//
// 输入：
//
//   1
//    \
//     3
//    /
//   2
//
//输出：
//1
//
//解释：
//最小绝对差为 1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。
// 
//
// 
//
// 提示： 
//
// 
// 树中至少有 2 个节点。 
// 本题与 783 https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes/ 
//相同 
// 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉搜索树 二叉树 👍 265 👎 0


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
    int before = Integer.MAX_VALUE;
    int mindiff = Integer.MAX_VALUE;

    public int getMinimumDifference(TreeNode root) {        // 递归
        dfs(root);
        return mindiff;
    }

    private void dfs(TreeNode node) {
        if (node.left != null) {
            dfs(node.left);
        }
        mindiff = Math.min(Math.abs(node.val - before), mindiff);
        before = node.val;
        if (node.right != null) {
            dfs(node.right);
        }
    }
}

//leetcode submit region end(Prohibit modification and deletion)


class Solution {
    int before = Integer.MAX_VALUE;
    int mindiff = Integer.MAX_VALUE;

    public int getMinimumDifference(TreeNode root) {        // 迭代
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
                mindiff = Math.min(Math.abs(node.val - before), mindiff);
                before = node.val;
            }
        }
        return mindiff;
    }
}
