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
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */

class Solution {
public:
    int kthSmallest(TreeNode* root, int k) {    // 迭代(BFS)
        stack<TreeNode*> stack;
        int count = 0, ans;
        while (root != nullptr) {
            stack.push(root);
            root = root->left;
        }
        while (true) {
            TreeNode* node = stack.top();
            stack.pop();
            ans = node->val;
            count++;
            if (count == k) {
                break;
            }
            // 中序遍历, 当前节点遍历后考虑right子节点和该子节点的left区域
            node = node->right;
            while (node != nullptr) {
                stack.push(node);
                node = node->left;
            }
        }
        return ans;
    }
};

//leetcode submit region end(Prohibit modification and deletion)

class Solution {
public:
    stack<int> stack;
    int ans = -1, k;
    int kthSmallest(TreeNode* root, int k) {    // 迭代(BFS)
        this->k = k;
        dfs(root);
        return ans;
    }

    void dfs(TreeNode* node) {
        if (ans != -1 || node == nullptr) {
            return;
        }
        dfs(node->left);
        if (ans != -1) {
            return;
        }
        stack.push(node->val);
        if (stack.size() == k) {    // 加入后考虑是否已经含k个值
            ans = stack.top();
            return;
        }
        dfs(node->right);
    }
};
