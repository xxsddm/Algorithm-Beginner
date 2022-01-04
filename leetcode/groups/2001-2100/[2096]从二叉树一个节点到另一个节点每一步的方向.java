//给你一棵 二叉树 的根节点 root ，这棵二叉树总共有 n 个节点。每个节点的值为 1 到 n 中的一个整数，且互不相同。给你一个整数 
//startValue ，表示起点节点 s 的值，和另一个不同的整数 destValue ，表示终点节点 t 的值。 
//
// 请找到从节点 s 到节点 t 的 最短路径 ，并以字符串的形式返回每一步的方向。每一步用 大写 字母 'L' ，'R' 和 'U' 分别表示一种方向： 
//
// 
// 'L' 表示从一个节点前往它的 左孩子 节点。 
// 'R' 表示从一个节点前往它的 右孩子 节点。 
// 'U' 表示从一个节点前往它的 父 节点。 
// 
//
// 请你返回从 s 到 t 最短路径 每一步的方向。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：root = [5,1,2,3,null,6,4], startValue = 3, destValue = 6
//输出："UURL"
//解释：最短路径为：3 → 1 → 5 → 2 → 6 。
// 
//
// 示例 2： 
//
// 
//
// 输入：root = [2,1], startValue = 2, destValue = 1
//输出："L"
//解释：最短路径为：2 → 1 。
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目为 n 。 
// 2 <= n <= 10⁵ 
// 1 <= Node.val <= n 
// 树中所有节点的值 互不相同 。 
// 1 <= startValue, destValue <= n 
// startValue != destValue 
// 
// Related Topics 树 深度优先搜索 字符串 二叉树 👍 21 👎 0


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
    public String getDirections(TreeNode root, int startValue, int destValue) {
        TreeNode lca = lca(root, startValue, destValue);
        LinkedList<Character> path1 = new LinkedList<>(), path2 = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        dfs(lca, startValue, path1);
        dfs(lca, destValue, path2);
        for (int i = 0, length = path1.size(); i < length; i++) {
            sb.append('U');
        }
        for (char c: path2) {
            sb.append(c);
        }
        return sb.toString();
    }

    boolean dfs(TreeNode node, int target, LinkedList<Character> path) {
        if (node == null) {
            return false;
        }
        if (node.val == target) {
            return true;
        }
        path.add('L');
        if (!dfs(node.left, target, path)) {
            path.pollLast();
        } else {
            return true;
        }
        path.add('R');
        if (!dfs(node.right, target, path)) {
            path.pollLast();
        } else {
            return true;
        }
        return false;
    }

    TreeNode lca(TreeNode node, int value1, int value2) {   // 可以不用找
        if (node == null) {
            return null;
        }
        if (node.val == value1 || node.val == value2) {
            return node;
        }
        TreeNode left = lca(node.left, value1, value2);
        if (left == null) {
            return lca(node.right, value1, value2);
        }
        if (lca(node.right, value1, value2) == null) {
            return left;
        }
        return node;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
