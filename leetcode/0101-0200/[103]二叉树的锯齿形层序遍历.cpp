//给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。 
//
// 例如： 
//给定二叉树 [3,9,20,null,null,15,7], 
//
// 
//    3
//   / \
//  9  20
//    /  \
//   15   7
// 
//
// 返回锯齿形层序遍历如下： 
//
// 
//[
//  [3],
//  [20,9],
//  [15,7]
//]
// 
// Related Topics 树 广度优先搜索 二叉树 👍 517 👎 0


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
    vector<vector<int>> zigzagLevelOrder(TreeNode* root) {
        vector<vector<int>> container;
        if (root == nullptr) {
            return container;
        }
        // 节点正向加入, 填写节点值的时候考虑正反向
        queue<TreeNode*> nodes;
        nodes.push(root);
        // 该层是否正向
        bool forward = true;
        while (!nodes.empty()) {
            vector<int> temp;
            int size = (int) nodes.size();
            for (int i = 0; i < size; i++) {
                TreeNode* node = nodes.front();
                nodes.pop();
                temp.push_back(node->val);
                if (node->left != nullptr) {
                    nodes.push(node->left);
                }
                if (node->right != nullptr) {
                    nodes.push(node->right);
                }
            }
            if (!forward) { // 翻转temp
                vector<int> temptemp(temp.size());
                int half = (int) temp.size() >> 1;
                for (int i = 0; i < half; i++) {
                    int num = temp[i];
                    temp[i] = temp[temp.size() - 1 - i];
                    temp[temp.size() - 1 - i] = num;
                }
                container.push_back(temp);
                forward = true;
            }
            else {
                container.push_back(temp);
                forward = false;
            }

        }
        return container;
    }
};

//leetcode submit region end(Prohibit modification and deletion)
