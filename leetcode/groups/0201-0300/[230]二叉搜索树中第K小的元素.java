//给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [3,1,4,null,2], k = 1
//输出：1
// 
//
// 示例 2： 
//
// 
//输入：root = [5,3,6,2,4,null,null,1], k = 3
//输出：3
// 
//
// 
//
// 
//
// 提示： 
//
// 
// 树中的节点数为 n 。 
// 1 <= k <= n <= 10⁴ 
// 0 <= Node.val <= 10⁴ 
// 
//
// 
//
// 进阶：如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化算法？ 
// Related Topics 树 深度优先搜索 二叉搜索树 二叉树 👍 447 👎 0


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
    public int kthSmallest(TreeNode root, int k) {  // 迭代(BFS)
        LinkedList<TreeNode> stack = new LinkedList<>();
        int count = 0, ans;
        while (root != null) {
            stack.addLast(root);
            root = root.left;
        }
        while (true) {
            TreeNode node = stack.pollLast();
            ans = node.val;
            count++;
            if (count == k) {
                break;
            }
            // 中序遍历, 当前节点遍历后考虑right子节点和该子节点的left区域
            node = node.right;
            while (node != null) {
                stack.addLast(node);
                node = node.left;
            }
        }
        return ans;
    }
}

//leetcode submit region end(Prohibit modification and deletion)

class Solution {
    int ans = -1, k, count = -1;    // count记录从最小节点开始的已访问节点数量

    public int kthSmallest(TreeNode root, int k) {  // 递归(DFS)(无需保存节点)
        this.k = k;
        dfs(root);
        return ans;
    }

    private void dfs(TreeNode node) {
        if (ans != -1 || node == null) {
            return;
        }
        dfs(node.left);
        if (ans != -1) {
            return;
        }
        // 直到第一次node.left为null时开始计数
        if (count == -1 && node.left == null) {
            count++;
        }
        if (count >= 0) {
            count++;
        }
        if (count == k) {
            ans = node.val;
            return;
        }
        dfs(node.right);
    }
}

class Solution {
    LinkedList<Integer> container = new LinkedList<>();
    int ans = -1, k;    // 先把结果ans设置为不可能的取值

    public int kthSmallest(TreeNode root, int k) {  // 递归(DFS)
        this.k = k;
        dfs(root);
        return ans;
    }

    private void dfs(TreeNode node) {
        if (ans != -1 || node == null) {
            return;
        }
        dfs(node.left);
        if (ans != -1) {
            return;
        }
        container.add(node.val);
        if (container.size() == k) {    // 加入后考虑是否已经含k个值
            ans = container.peekLast();
            return;
        }
        dfs(node.right);
    }
}
