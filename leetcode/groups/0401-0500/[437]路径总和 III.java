//给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。 
//
// 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
//输出：3
//解释：和等于 8 的路径有 3 条，如图所示。
// 
//
// 示例 2： 
//
// 
//输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
//输出：3
// 
//
// 
//
// 提示: 
//
// 
// 二叉树的节点个数的范围是 [0,1000] 
// -10⁹ <= Node.val <= 10⁹ 
// -1000 <= targetSum <= 1000 
// 
// Related Topics 树 深度优先搜索 二叉树 👍 1002 👎 0


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
    int count = 0;
    LinkedList<TreeNode> stack = new LinkedList<>();

    public int pathSum(TreeNode root, int targetSum) {  // DFS(考虑以所有节点开始的路径)(显式调用栈)
        if (root == null) {
            return count;
        }
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollLast();
            dfs(node, targetSum);
            if (node.right != null) {
                stack.add(node.right);
            }
            if (node.left != null) {
                stack.add(node.left);
            }
        }
        return count;
    }

    private void dfs(TreeNode node, int target) {
        if (node == null) {
            return;
        }
        int nextTarget = target - node.val;
        if (nextTarget == 0) {
            count++;
        }
        dfs(node.left, nextTarget);
        dfs(node.right, nextTarget);
    }
}

//leetcode submit region end(Prohibit modification and deletion)

class Solution {
    public int pathSum(TreeNode root, int targetSum) {  // DFS(考虑以所有节点开始的路径)(递归隐式调用栈)
        if (root == null) {
            return 0;
        }
        // 当前节点为起点的路径数量+左节点为起点的路径数量+右节点为起点的路径数量
        return dfs(root, targetSum)
                + pathSum(root.left, targetSum)
                + pathSum(root.right, targetSum);
    }

    private int dfs(TreeNode node, int target) {
        if (node == null) {
            return 0;
        }
        int nextTarget = target - node.val;
        return (nextTarget == 0 ? 1 : 0)
                + dfs(node.left, nextTarget)
                + dfs(node.right, nextTarget);
    }
}
