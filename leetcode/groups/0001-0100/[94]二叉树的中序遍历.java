//给定一个二叉树的根节点 root ，返回它的 中序 遍历。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,null,2,3]
//输出：[1,3,2]
// 
//
// 示例 2： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：root = [1]
//输出：[1]
// 
//
// 示例 4： 
//
// 
//输入：root = [1,2]
//输出：[2,1]
// 
//
// 示例 5： 
//
// 
//输入：root = [1,null,2]
//输出：[1,2]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [0, 100] 内 
// -100 <= Node.val <= 100 
// 
//
// 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 深度优先搜索 二叉树 
// 👍 1052 👎 0


//leetcode submit region begin(Prohibit modification and deletion)


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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

class Solution {    // 迭代
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> container = new ArrayList<>();
        if (root == null)    return container;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node == null) {
                node = stack.pop();
                container.add(node.val);
                continue;
            }
            if (node.right != null)    stack.push(node.right);

            stack.push(node);
            stack.push(null);

            if (node.left != null)    stack.push(node.left);
        }
        return container;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


class Solution {    // 递归
    List<Integer> container = new ArrayList<>();

    public List<Integer> inorderTraversal(TreeNode root) {
        search(root);
        return container;
    }

    private void search(TreeNode node){
        if (node == null)   return;
        search(node.left);
        container.add(node.val);
        search(node.right);
    }
}

class Solution {    // 迭代
    List<Integer> container = new ArrayList<>();
    Stack<TreeNode> stack = new Stack<>();
    HashSet<TreeNode> ignore = new HashSet<>();

    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null)    return container;
        stack.add(root);
        ignore.add(null);
        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            if (node.right != null && !ignore.contains(node.right)) {
                stack.add(node.right);
                ignore.add(node.right);
            }
            stack.add(node);
            if (node.left != null && !ignore.contains(node.left)) {
                stack.add(node.left);
            }
            if (ignore.contains(node.left)){
                TreeNode temp = stack.pop();
                container.add(temp.val);
                ignore.add(temp);
            }
        }
        return container;
    }
}
