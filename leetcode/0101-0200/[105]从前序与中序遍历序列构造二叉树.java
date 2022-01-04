//给定一棵树的前序遍历 preorder 与中序遍历 inorder。请构造二叉树并返回其根节点。 
//
// 
//
// 示例 1: 
//
// 
//Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
//Output: [3,9,20,null,null,15,7]
// 
//
// 示例 2: 
//
// 
//Input: preorder = [-1], inorder = [-1]
//Output: [-1]
// 
//
// 
//
// 提示: 
//
// 
// 1 <= preorder.length <= 3000 
// inorder.length == preorder.length 
// -3000 <= preorder[i], inorder[i] <= 3000 
// preorder 和 inorder 均无重复元素 
// inorder 均出现在 preorder 
// preorder 保证为二叉树的前序遍历序列 
// inorder 保证为二叉树的中序遍历序列 
// 
// Related Topics 树 数组 哈希表 分治 二叉树 👍 1163 👎 0


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
    public TreeNode buildTree(int[] preorder, int[] inorder) {      // 递归
        ArrayList<Integer> pre = new ArrayList<>(), ino = new ArrayList<>();
        for (int i = 0; i < preorder.length; i++) {
            pre.add(preorder[i]);
            ino.add(inorder[i]);
        }
        return dfs(pre, ino);
    }

    private TreeNode dfs(List<Integer> preorder, List<Integer> inorder) {
        if (preorder.isEmpty()) {
            return null;
        }
        TreeNode root = new TreeNode(preorder.get(0));
        int leftlength = inorder.indexOf(root.val);     // 可以考虑hash, 找起来更快
        root.left = dfs(preorder.subList(1, leftlength + 1), inorder.subList(0, leftlength));
        root.right = dfs(preorder.subList(leftlength + 1, preorder.size()), inorder.subList(leftlength + 1, inorder.size()));
        return root;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
