//在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“
//房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。 
//
// 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。 
//
// 示例 1: 
//
// 输入: [3,2,3,null,3,null,1]
//
//     3
//    / \
//   2   3
//    \   \ 
//     3   1
//
//输出: 7 
//解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7. 
//
// 示例 2: 
//
// 输入: [3,4,5,1,3,null,1]
//
//     3
//    / \
//   4   5
//  / \   \ 
// 1   3   1
//
//输出: 9
//解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
// 
// Related Topics 树 深度优先搜索 动态规划 二叉树 👍 952 👎 0


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
    public int rob(TreeNode root) {
        int[] temp = dfs(root);
        return Math.max(temp[0], temp[1]);
    }

    private int[] dfs(TreeNode node) {  // 返回 不偷/偷 node节点的最大收益(以该节点为入口)
        if (node == null) {
            return new int[] {0, 0};
        }
        int val1 = node.val, val2 = 0;
        int[] left = dfs(node.left), right = dfs(node.right);
        // 偷node节点
        val1 += left[0] + right[0];
        // 不偷node节点(则 可能 偷左右节点)
        val2 += Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return new int[] {val2, val1};
    }
}

//leetcode submit region end(Prohibit modification and deletion)


class Solution {
    HashMap<TreeNode, Integer> container = new HashMap<>();

    public int rob(TreeNode root) {     // 返回以该节点为入口的最大偷窃金额(入口点可以不偷)
        if (root == null) {
            return 0;
        }
        if (container.containsKey(root)) {
            return container.get(root);
        }

        // 偷root节点(则必须跳过左右子节点)
        int temp1 = root.val;
        if (root.left != null) {
            temp1 += rob(root.left.left) + rob(root.left.right);
        }
        if (root.right != null) {
            temp1 += rob(root.right.left) + rob(root.right.right);
        }

        // 不偷root节点
        int temp2 = rob(root.left) + rob(root.right);

        int ans = Math.max(temp1, temp2);
        container.put(root, ans);
        return ans;
    }
}
