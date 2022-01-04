//给你二叉搜索树的根节点 root ，同时给定最小边界low 和最大边界 high。通过修剪二叉搜索树，使得所有节点的值在[low, high]中。修剪树不应
//该改变保留在树中的元素的相对结构（即，如果没有被移除，原有的父代子代关系都应当保留）。 可以证明，存在唯一的答案。 
//
// 所以结果应当返回修剪好的二叉搜索树的新的根节点。注意，根节点可能会根据给定的边界发生改变。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,0,2], low = 1, high = 2
//输出：[1,null,2]
// 
//
// 示例 2： 
//
// 
//输入：root = [3,0,4,null,2,null,null,1], low = 1, high = 3
//输出：[3,2,null,1]
// 
//
// 示例 3： 
//
// 
//输入：root = [1], low = 1, high = 2
//输出：[1]
// 
//
// 示例 4： 
//
// 
//输入：root = [1,null,2], low = 1, high = 3
//输出：[1,null,2]
// 
//
// 示例 5： 
//
// 
//输入：root = [1,null,2], low = 2, high = 4
//输出：[2]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数在范围 [1, 10⁴] 内 
// 0 <= Node.val <= 10⁴ 
// 树中每个节点的值都是唯一的 
// 题目数据保证输入是一棵有效的二叉搜索树 
// 0 <= low <= high <= 10⁴ 
// 
// Related Topics 树 深度优先搜索 二叉搜索树 二叉树 👍 414 👎 0


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
    public TreeNode trimBST(TreeNode root, int low, int high) {     // 递归
        if (root == null) {
            return null;
        }
        if (root.val > high) {      // 向左寻找
            return trimBST(root.left, low, high);
        }
        if (root.val < low) {       // 向右寻找
            return trimBST(root.right, low, high);
        }
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        return root;
    }
}

//leetcode submit region end(Prohibit modification and deletion)


class Solution {
    public TreeNode trimBST(TreeNode root, int low, int high) {     // 迭代
        if (root == null) {
            return null;
        }
        while (root != null) {      // 修改root
            if (root.val < low) {
                root = root.right;
            }
            else if (root.val > high) {
                root = root.left;
            }
            else {
                break;
            }
        }

        TreeNode temp = root;       // 修改root.left, 防止左侧越界(root.val<=high故右侧不可能越界)
        while (temp != null) {
            while (temp.left != null) {
                if (temp.left.val < low) {
                    temp.left = temp.left.right;
                }
                else if (temp.left.val > high) {
                    temp.left = temp.left.left;
                }
                else {
                    break;
                }
            }
            temp = temp.left;
        }

        temp = root;
        while (temp != null) {      // 修改root.right, 防止右侧越界(root.val>=low故左侧不可能越界)
            while (temp.right != null) {
                if (temp.right.val < low) {
                    temp.right = temp.right.right;
                }
                else if (temp.right.val > high) {
                    temp.right = temp.right.left;
                }
                else {
                    break;
                }
            }
            temp = temp.right;
        }

        return root;
    }
}
