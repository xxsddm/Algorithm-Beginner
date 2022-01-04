//给定一个二叉树，返回它的 后序 遍历。 
//
// 示例: 
//
// 输入: [1,null,2,3]  
//   1
//    \
//     2
//    /
//   3 
//
//输出: [3,2,1] 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 深度优先搜索 二叉树 
// 👍 639 👎 0


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

class Solution {    // 递归
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> container = new ArrayList<>();
        if (root == null) return container;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node == null) {
                node = stack.pop();
                container.add(node.val);
                continue;
            }
            stack.push(node);
            stack.push(null);

            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
        return container;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


class Solution {    // 递归
    List<Integer> container = new ArrayList<>();

    public List<Integer> postorderTraversal(TreeNode root) {
        search(root);
        return container;
    }

    private void search(TreeNode node){
        if (node == null)   return;
        search(node.left);
        search(node.right);
        container.add(node.val);
    }
}


class Solution {    // 迭代
    List<Integer> container = new ArrayList<>();
    Stack<TreeNode> stack = new Stack<>();
    HashSet<TreeNode> ignore = new HashSet<>();

    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null)    return container;
        stack.add(root);
        ignore.add(null);
        while (!stack.isEmpty()){
            TreeNode node = stack.peek();
            if (node.right != null && !ignore.contains(node.right))    stack.add(node.right);
            if (node.left != null && !ignore.contains(node.left))    stack.add(node.left);
            if (ignore.contains(node.right) && ignore.contains(node.left)){
                TreeNode temp = stack.pop();
                container.add(temp.val);
                ignore.add(temp);
            }
        }
        return container;
    }
}
