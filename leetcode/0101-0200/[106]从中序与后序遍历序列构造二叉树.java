//根据一棵树的中序遍历与后序遍历构造二叉树。 
//
// 注意: 
//你可以假设树中没有重复的元素。 
//
// 例如，给出 
//
// 中序遍历 inorder = [9,3,15,20,7]
//后序遍历 postorder = [9,15,7,20,3] 
//
// 返回如下的二叉树： 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
// 
// Related Topics 树 数组 哈希表 分治 二叉树 👍 549 👎 0


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
    public TreeNode buildTree(int[] inorder, int[] postorder) {     // 递归
        ArrayList<Integer> ino = new ArrayList<>(), post = new ArrayList<>();
        for (int i = 0; i < postorder.length; i++) {
            post.add(postorder[i]);
            ino.add(inorder[i]);
        }
        return dfs(ino, post);
    }

    private TreeNode dfs(List<Integer> inorder, List<Integer> postorder) {
        if (postorder.isEmpty()) {
            return null;
        }
        TreeNode root = new TreeNode(postorder.get(postorder.size() - 1));
        int leftlength = inorder.indexOf(root.val);     // 可以考虑hash, 找起来更快
        root.left = dfs(inorder.subList(0, leftlength), postorder.subList(0, leftlength));
        root.right = dfs(inorder.subList(leftlength + 1, inorder.size()), postorder.subList(leftlength, postorder.size() - 1));
        return root;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
