//请实现两个函数，分别用来序列化和反序列化二叉树。 
//
// 你需要设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字
//符串反序列化为原始的树结构。 
//
// 提示：输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方
//法解决这个问题。 
//
// 
//
// 示例： 
//
// 
//输入：root = [1,2,3,null,null,4,5]
//输出：[1,2,3,null,null,4,5]
// 
//
// 
//
// 注意：本题与主站 297 题相同：https://leetcode-cn.com/problems/serialize-and-deserialize-
//binary-tree/ 
// Related Topics 树 深度优先搜索 广度优先搜索 设计 字符串 二叉树 👍 233 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.LinkedList;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class Codec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {    // 层序遍历(BFS)
        LinkedList<TreeNode> queue = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        queue.add(root);
        sb.append('[');
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node == null) {
                    sb.append("null,");
                    continue;
                }
                sb.append(node.val);
                sb.append(',');
                queue.add(node.left);
                queue.add(node.right);
            }
        }
        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {  // 层序遍历
        LinkedList<String> queue = new LinkedList<>();
        LinkedList<TreeNode> queueNode = new LinkedList<>();
        int idx = 1, length = data.length();
        // 扫描序列,将值保存进节点值的队列("null"可用于占位)
        while (idx < length - 1) {
            int tempIdx = idx;
            while (idx < length - 1 && data.charAt(idx) != ',') {   // 直到idx指向','
                idx++;
            }
            queue.add(data.substring(tempIdx, idx));
            idx++;
        }
        if (queue.peekFirst().equals("null")) {
            // root为null时
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(queue.poll()));
        queueNode.add(root);
        while (!queueNode.isEmpty()) {
            int size = queueNode.size();    // 每个node(非null)对应两个子节点
            for (int i = 0; i < size; i++) {
                TreeNode node = queueNode.poll();
                String leftStr = queue.poll(), rightStr = queue.poll();
                // 判断null节点,null则不加入队列
                if (!leftStr.equals("null")) {
                    TreeNode left = new TreeNode(Integer.parseInt(leftStr));
                    node.left = left;
                    queueNode.add(left);

                }
                if (!rightStr.equals("null")) {
                    TreeNode right = new TreeNode(Integer.parseInt(rightStr));
                    node.right = right;
                    queueNode.add(right);
                }
            }
        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
//leetcode submit region end(Prohibit modification and deletion)
