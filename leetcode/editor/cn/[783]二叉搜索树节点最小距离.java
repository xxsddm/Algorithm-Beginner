//给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。 
//
// 注意：本题与 530：https://leetcode-cn.com/problems/minimum-absolute-difference-in-
//bst/ 相同 
//
// 
//
// 
// 
// 示例 1： 
//
// 
//输入：root = [4,2,6,1,3]
//输出：1
// 
//
// 示例 2： 
//
// 
//输入：root = [1,0,48,null,null,12,49]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [2, 100] 内 
// 0 <= Node.val <= 10⁵ 
// 差值是一个正数，其数值等于两值之差的绝对值 
// 
// 
// 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉搜索树 二叉树 👍 196 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
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

    public int minDiffInBST(TreeNode root) {        // 递归
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

    public int minDiffInBST(TreeNode root) {        // 迭代
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
