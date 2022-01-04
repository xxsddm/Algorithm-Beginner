//给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。如果一个节点有两个子节点的话，那么该节点的值等于两个子节点中较小的一
//个。 
//
// 更正式地说，root.val = min(root.left.val, root.right.val) 总成立。 
//
// 给出这样的一个二叉树，你需要输出所有节点中的第二小的值。如果第二小的值不存在的话，输出 -1 。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [2,2,5,null,null,5,7]
//输出：5
//解释：最小的值是 2 ，第二小的值是 5 。
// 
//
// 示例 2： 
//
// 
//输入：root = [2,2,2]
//输出：-1
//解释：最小的值是 2, 但是不存在第二小的值。
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [1, 25] 内 
// 1 <= Node.val <= 231 - 1 
// 对于树中每个节点 root.val == min(root.left.val, root.right.val) 
// 
// Related Topics 树 深度优先搜索 二叉树 
// 👍 159 👎 0


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
    public int findSecondMinimumValue(TreeNode root) {
        if (root.left == null) return -1;
        int templeft, tempright;

        // 找到异于root值的节点则停止递归
        if (root.left.val != root.val) templeft = root.left.val;
        else templeft = findSecondMinimumValue(root.left);

        if (root.right.val != root.val) tempright = root.right.val;
        else tempright = findSecondMinimumValue(root.right);

        if (templeft == -1) return tempright;
        else if (tempright == -1) return templeft;
        else return Math.min(templeft, tempright);
    }
}
//leetcode submit region end(Prohibit modification and deletion)


class Solution {
    public int findSecondMinimumValue(TreeNode root) {
        if (root.left == null) return -1;
        else if (root.left.val < root.right.val){
            int temp = findSecondMinimumValue(root.left);
            if (temp != -1) return Math.min(temp, root.right.val);
            else return root.right.val;
        }
        else if (root.left.val > root.right.val){
            int temp = findSecondMinimumValue(root.right);
            if (temp != -1) return Math.min(temp, root.left.val);
            else return root.left.val;
        }
        else{
            int templeft = findSecondMinimumValue(root.left);
            int tempright = findSecondMinimumValue(root.right);
            if (templeft == -1) return tempright;
            else if (tempright == -1) return templeft;
            else return Math.min(templeft, tempright);
        }
    }
}
