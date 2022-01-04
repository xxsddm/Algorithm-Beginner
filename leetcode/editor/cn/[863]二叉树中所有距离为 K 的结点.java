//给定一个二叉树（具有根结点 root）， 一个目标结点 target ，和一个整数值 K 。 
//
// 返回到目标结点 target 距离为 K 的所有结点的值的列表。 答案可以以任何顺序返回。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 输入：root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
//输出：[7,4,1]
//解释：
//所求结点为与目标结点（值为 5）距离为 2 的结点，
//值分别为 7，4，以及 1
//
//
//
//注意，输入的 "root" 和 "target" 实际上是树上的结点。
//上面的输入仅仅是对这些对象进行了序列化描述。
// 
//
// 
//
// 提示： 
//
// 
// 给定的树是非空的。 
// 树上的每个结点都具有唯一的值 0 <= node.val <= 500 。 
// 目标结点 target 是树上的结点。 
// 0 <= K <= 1000. 
// 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 
// 👍 322 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    Map<Integer, TreeNode> parents = new HashMap<>();
    List<Integer> ans = new ArrayList<>();

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        parents.put(root.val, null);
        findParents(root);
        // 这里的父节点必须设置为null, 同时防止root==target
        findAns(target, null, 0, k);
        return ans;
    }

    public void findParents(TreeNode node) {
        if (node.left != null) {
            parents.put(node.left.val, node);
            findParents(node.left);
        }
        if (node.right != null) {
            parents.put(node.right.val, node);
            findParents(node.right);
        }
    }

    public void findAns(TreeNode node, TreeNode from, int distance, int k) {
        if (node == null) {
            return;
        }
        if (distance == k) {
            ans.add(node.val);
            return;
        }
        if (node.left != from) {    // 如果当前节点沿着正常节点顺序递归
            findAns(node.left, node, distance + 1, k);
        }
        if (node.right != from) {
            findAns(node.right, node, distance + 1, k);
        }
        // 颠倒节点顺序, 查看target父节点部分(往回查找), 当前节点为target子节点部分则不需要
        if (parents.get(node.val) != from) {
            findAns(parents.get(node.val), node, distance + 1, k);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
