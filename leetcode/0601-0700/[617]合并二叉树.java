//给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。 
//
// 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点
//。 
//
// 示例 1: 
//
// 
//输入: 
//	Tree 1                     Tree 2                  
//          1                         2                             
//         / \                       / \                            
//        3   2                     1   3                        
//       /                           \   \                      
//      5                             4   7                  
//输出: 
//合并后的树:
//	     3
//	    / \
//	   4   5
//	  / \   \ 
//	 5   4   7
// 
//
// 注意: 合并必须从两个树的根节点开始。 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 751 👎 0


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
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {        // DFS
//        if (root1 == null && root2 == null) {
//            return null;
//        }
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        return new TreeNode(root1.val + root2.val,
                mergeTrees(root1.left, root2.left),
                mergeTrees(root1.right, root2.right));
    }
}

//leetcode submit region end(Prohibit modification and deletion)


class Solution {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {        // BFS
        if (root1 == null && root2 == null) {
            return null;
        }
        LinkedList<TreeNode> container = new LinkedList<>();
        LinkedList<TreeNode> container1 = new LinkedList<>();
        LinkedList<TreeNode> container2 = new LinkedList<>();
        TreeNode node, node1, node2, nodeleft, noderight, ans = new TreeNode();
        ans.val = (root1 != null ? root1.val : 0) + (root2 != null ? root2.val : 0);
        container.add(ans);     // 不放入空值
        container1.add(root1);      // 两个子树放入空值用于占位
        container2.add(root2);
        while (!container1.isEmpty() || !container2.isEmpty()) {
            node1 = container1.poll();
            node2 = container2.poll();
            if (node1 == null && node2 == null) {
                continue;
            }
            if (node1 != null) {
                container1.add(node1.left);
                container1.add(node1.right);
            }
            else {
                container1.add(null);
                container1.add(null);
            }
            if (node2 != null) {
                container2.add(node2.left);
                container2.add(node2.right);
            }
            else {
                container2.add(null);
                container2.add(null);
            }

            node = container.poll();
            nodeleft = new TreeNode();      // 构造左节点
            if (node1 != null && node1.left != null) {
                if (node2 != null && node2.left != null){
                    nodeleft.val = node1.left.val + node2.left.val;
                }
                else {
                    nodeleft.val = node1.left.val;
                }
                container.add(nodeleft);
            }
            else if (node2 != null && node2.left != null) {
                nodeleft.val = node2.left.val;
                container.add(nodeleft);
            }
            else {
                nodeleft = null;
            }

            noderight = new TreeNode();     // 构造右节点
            if (node1 != null && node1.right != null) {
                if (node2 != null && node2.right != null){
                    noderight.val = node1.right.val + node2.right.val;
                }
                else {
                    noderight.val = node1.right.val;
                }
                container.add(noderight);
            }
            else if (node2 != null && node2.right != null) {
                noderight.val = node2.right.val;
                container.add(noderight);
            }
            else {
                noderight = null;
            }

            node.left = nodeleft;
            node.right = noderight;
        }
        return ans;
    }
}
