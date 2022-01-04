//给定一个二叉搜索树，请将它的每个节点的值替换成树中大于或者等于该节点值的所有节点值之和。 
//
// 提醒一下，二叉搜索树满足下列约束条件： 
//
// 
// 节点的左子树仅包含键 小于 节点键的节点。 
// 节点的右子树仅包含键 大于 节点键的节点。 
// 左右子树也必须是二叉搜索树。 
// 
//
// 注意：该题目与 538: https://leetcode-cn.com/problems/convert-bst-to-greater-tree/ 相同
// 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：[4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
//输出：[30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
// 
//
// 示例 2： 
//
// 
//输入：root = [0,null,1]
//输出：[1,null,1]
// 
//
// 示例 3： 
//
// 
//输入：root = [1,0,2]
//输出：[3,3,2]
// 
//
// 示例 4： 
//
// 
//输入：root = [3,2,4,1]
//输出：[7,9,4,10]
// 
//
// 
//
// 提示： 
//
// 
// 树中的节点数介于 1 和 100 之间。 
// 每个节点的值介于 0 和 100 之间。 
// 树中的所有值 互不相同 。 
// 给定的树为二叉搜索树。 
// 
// Related Topics 树 深度优先搜索 二叉搜索树 二叉树 👍 127 👎 0


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
    int cumsum = 0;

    public TreeNode bstToGst(TreeNode root) {       // 递归(DFS)(中序遍历的相反方向即可, 右中左)
        if (root == null) {
            return null;
        }
        root.right = bstToGst(root.right);
        cumsum += root.val;
        root.val = cumsum;      // 当前值改为右侧(包括自身)节点值的累和
        root.left = bstToGst(root.left);
        return root;
    }
}

//leetcode submit region end(Prohibit modification and deletion)


class Solution {
    int cumsum = 0;

    public TreeNode bstToGst(TreeNode root) {       // 迭代(BFS)
        if (root == null) {
            return null;
        }
        Stack<TreeNode> container = new Stack<>();
        TreeNode node;
        container.add(root);
        while (!container.isEmpty()) {
            node = container.pop();
            if (node != null) {
                if (node.left != null) {
                    container.add(node.left);
                }
                container.add(node);
                container.add(null);    // 标记父节点
                if (node.right != null) {
                    container.add(node.right);
                }
            }
            else {
                node = container.pop();
                cumsum += node.val;
                node.val = cumsum;
            }
        }
        return root;
    }
}
