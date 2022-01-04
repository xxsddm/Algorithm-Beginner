//给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。 
//
// 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。 
//
// 
//
// 示例 1： 
//
// 
//输入：p = [1,2,3], q = [1,2,3]
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：p = [1,2], q = [1,null,2]
//输出：false
// 
//
// 示例 3： 
//
// 
//输入：p = [1,2,1], q = [1,1,2]
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 两棵树上的节点数目都在范围 [0, 100] 内 
// -104 <= Node.val <= 104 
// 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 
// 👍 667 👎 0


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
    public boolean isSameTree(TreeNode p, TreeNode q) {     // 递归
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val == q.val) {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
        else {
            return false;
        }
    }
}

//leetcode submit region end(Prohibit modification and deletion)


class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {     // 迭代
        Stack<TreeNode> nodes = new Stack<>();
        TreeNode node1, node2;
        nodes.add(p);
        nodes.add(q);
        while (!nodes.isEmpty()) {
            node1 = nodes.pop();
            node2 = nodes.pop();
            if (node1 != null && node2 == null) {
                return false;
            }
            if (node1 == null && node2 != null) {
                return false;
            }
            if (node1 != null && node2 != null) {
                if (node1.val == node2.val) {
                    nodes.add(node1.left);
                    nodes.add(node2.left);
                    nodes.add(node1.right);
                    nodes.add(node2.right);
                }
                else {
                    return false;
                }
            }
        }
        return true;
    }
}

