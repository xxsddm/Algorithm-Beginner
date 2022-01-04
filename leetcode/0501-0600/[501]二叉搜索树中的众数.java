//给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。 
//
// 假定 BST 有如下定义： 
//
// 
// 结点左子树中所含结点的值小于等于当前结点的值 
// 结点右子树中所含结点的值大于等于当前结点的值 
// 左子树和右子树都是二叉搜索树 
// 
//
// 例如： 
//给定 BST [1,null,2,2], 
//
//    1
//    \
//     2
//    /
//   2
// 
//
// 返回[2]. 
//
// 提示：如果众数超过1个，不需考虑输出顺序 
//
// 进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内） 
// Related Topics 树 深度优先搜索 二叉搜索树 二叉树 👍 335 👎 0


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
    int mode;
    int num = 1;
    int count = 0;
    List<Integer> container = new ArrayList<>();

    public int[] findMode(TreeNode root) {      // 递归
        dfs(root);
        int[] ans = new int[container.size()];
        for (int i = 0; i < container.size(); i++) {
            ans[i] = container.get(i);
        }
        return ans;
    }

    private void dfs(TreeNode node) {
        if (node.left != null) {
            dfs(node.left);
        }

        if (node.val == mode) {
            count++;
            if (count == num) {
                container.add(node.val);
            }
            else if (count > num) {
                container.clear();
                container.add(node.val);
                num = count;
            }
        }
        else {
            mode = node.val;
            count = 1;
            if (count == num) {
                container.add(node.val);
            }
        }

        if (node.right != null) {
            dfs(node.right);
        }
    }
}

//leetcode submit region end(Prohibit modification and deletion)
