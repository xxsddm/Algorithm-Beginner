//给定一个二叉树，我们在树的节点上安装摄像头。 
//
// 节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。 
//
// 计算监控树的所有节点所需的最小摄像头数量。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：[0,0,null,0,0]
//输出：1
//解释：如图所示，一台摄像头足以监控所有节点。
// 
//
// 示例 2： 
//
// 
//
// 输入：[0,0,null,0,null,0,null,null,0]
//输出：2
//解释：需要至少两个摄像头来监视树的所有节点。 上图显示了摄像头放置的有效位置之一。
// 
//
// 
//提示： 
//
// 
// 给定树的节点数的范围是 [1, 1000]。 
// 每个节点的值都是 0。 
// 
// Related Topics 树 深度优先搜索 动态规划 二叉树 👍 318 👎 0


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
    int num = 0;

    public int minCameraCover(TreeNode root) {
        if (count(root) == 0) {     // 若根节点未覆盖, 需要加一个
            num++;
        }
        return num;
    }

    // 根据子节点情况, 判断本节点是哪一种状态
    // 0: 未被覆盖; 1: 被覆盖但未安装; 2: 在该点安装摄像头
    private int count(TreeNode node) {
        if (node == null) {     // 叶节点属于状态1, 不安装, 减少数量
            return 1;
        }
        int temp1 = count(node.left), temp2 = count(node.right);
        if (temp1 == 1 && temp2 == 1) {
            return 0;
        }
        if (temp1 == 0 || temp2 == 0) {
            num++;
            return 2;
        }
        // 需满足两子节点均被覆盖, 相当于: if (temp1 == 2 || temp2 == 2)
        return 1;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
