//给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。 
//
// 高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-10,-3,0,5,9]
//输出：[0,-3,9,-10,null,5]
//解释：[0,-10,5,null,-3,null,9] 也将被视为正确答案：
//
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,3]
//输出：[3,1]
//解释：[1,3] 和 [3,1] 都是高度平衡二叉搜索树。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10⁴ 
// -10⁴ <= nums[i] <= 10⁴ 
// nums 按 严格递增 顺序排列 
// 
// Related Topics 树 二叉搜索树 数组 分治 二叉树 👍 816 👎 0


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
    int[] nums;

    public TreeNode sortedArrayToBST(int[] nums) {      // 递归(DFS)
        this.nums = nums;
        return dfs(0, nums.length - 1);
    }

    private TreeNode dfs(int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = start + end >> 1;
        return new TreeNode(
                nums[mid],
                dfs(start, mid - 1),
                dfs(mid + 1, end));
    }
}

//leetcode submit region end(Prohibit modification and deletion)


class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {      // 迭代(BFS)
        if (nums.length == 1) {
            return new TreeNode(nums[0]);
        }
        // 利用队列, 先进先出BFS逐层构建
        LinkedList<TreeNode> nodeQ = new LinkedList<>();    // 存放中间结点
        LinkedList<Integer> leftQ = new LinkedList<>();     // 存放相应区间首端索引
        LinkedList<Integer> rightQ = new LinkedList<>();    // 存放相应区间末端索引
        TreeNode root = new TreeNode(), node = root;
        int left = 0, right = nums.length - 1, mid;
        nodeQ.add(node);
        leftQ.add(left);
        rightQ.add(right);
        while (!nodeQ.isEmpty()) {
            node = nodeQ.poll();
            left = leftQ.poll();
            right = rightQ.poll();
            mid = left + right >> 1;
            node.val = nums[mid];
            if (left <= mid - 1) {
                node.left = new TreeNode();
                nodeQ.add(node.left);
                leftQ.add(left);
                rightQ.add(mid - 1);
            }
            if (mid + 1 <= right) {
                node.right = new TreeNode();
                nodeQ.add(node.right);
                leftQ.add(mid + 1);
                rightQ.add(right);
            }
        }
        return root;
    }
}
