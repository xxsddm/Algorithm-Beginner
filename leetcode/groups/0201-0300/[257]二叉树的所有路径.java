//给定一个二叉树，返回所有从根节点到叶子节点的路径。 
//
// 说明: 叶子节点是指没有子节点的节点。 
//
// 示例: 
//
// 输入:
//
//   1
// /   \
//2     3
// \
//  5
//
//输出: ["1->2->5", "1->3"]
//
//解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3 
// Related Topics 树 深度优先搜索 字符串 二叉树 
// 👍 556 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
import java.util.List;

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
    List<String> container = new ArrayList<>();

    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return container;
        }
        concatenate(root, "");
        return container;
    }

    private void concatenate(TreeNode node, String str) {       // 把当前的节点加入到已有路径字符串中
        StringBuilder temp = new StringBuilder(str);
        if (temp.length() != 0) {
            temp.append("->");
        }
        temp.append(node.val);
        if (node.left == null && node.right == null) {
            container.add(temp.toString());
            return;
        }
        if (node.left != null) {
            concatenate(node.left, temp.toString());
        }
        if (node.right != null) {
            concatenate(node.right, temp.toString());
        }
    }
}

//leetcode submit region end(Prohibit modification and deletion)
