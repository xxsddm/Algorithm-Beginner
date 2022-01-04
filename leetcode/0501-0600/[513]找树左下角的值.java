//给定一个二叉树的 根节点 root，请找出该二叉树的 最底层 最左边 节点的值。 
//
// 假设二叉树中至少有一个节点。 
//
// 
//
// 示例 1: 
//
// 
//
// 
//输入: root = [2,1,3]
//输出: 1
// 
//
// 示例 2: 
//
// 
//
// 
//输入: [1,2,3,4,null,5,6,null,null,7]
//输出: 7
// 
//
// 
//
// 提示: 
//
// 
// 二叉树的节点个数的范围是 [1,104] 
// -231 <= Node.val <= 231 - 1 
// 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 
// 👍 193 👎 0


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

class Solution {        // 递归
    int val = 0;
    int depth = -1;
    public int findBottomLeftValue(TreeNode root) {
        dfs(root, 0);
        return val;
    }

    private void dfs(TreeNode node, int height) {
        if (node.left == null && node.right == null) {
            if (height > depth) {
                val = node.val;
                depth = height;
            }
            return;
        }
        if (node.left != null) {
            dfs(node.left, height + 1);
        }
        if (node.right != null) {
            dfs(node.right, height + 1);
        }
    }
}

//leetcode submit region end(Prohibit modification and deletion)


class Solution {        //  迭代
    public int findBottomLeftValue(TreeNode root) {
        LinkedList<TreeNode> nodes = new LinkedList<>();
        TreeNode node;
        int ans = 0;
        nodes.add(root);
        while (!nodes.isEmpty()) {
            int num = nodes.size();
            for (int i = 0; i < num; i++) {
                node = nodes.poll();
                if (i == 0) {
                    ans = node.val;
                }
                if (node.left != null) {
                    nodes.add(node.left);
                }
                if (node.right != null) {
                    nodes.add(node.right);
                }
            }
        }
        return ans;
    }
}
