//序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方
//式重构得到原数据。 
//
// 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反
//序列化为原始的树结构。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：root = [1,2,3,null,null,4,5]
//输出：[1,2,3,null,null,4,5]
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
//输出：[1,2]
// 
//
// 
//
// 提示： 
//
// 
// 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，也可以采用其他的方法解决这
//个问题。 
// 树中结点数在范围 [0, 10⁴] 内 
// -1000 <= Node.val <= 1000 
// 
//
// 
//
// 注意：本题与主站 297 题相同：https://leetcode-cn.com/problems/serialize-and-deserialize-
//binary-tree/ 
// Related Topics 树 深度优先搜索 广度优先搜索 设计 字符串 二叉树 👍 3 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */

class Codec {
public:

    // Encodes a tree to a single string.
    string serialize(TreeNode* root) {
        queue<TreeNode*> queue;
        string ans;
        queue.push(root);
        ans.append("[");
        while (!queue.empty()) {
            int size = (int) queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode* node = queue.front();
                queue.pop();
                if (node == nullptr) {
                    ans.append("null,");
                    continue;
                }
                ans += to_string(node->val);
                ans.append(",");
                queue.push(node->left);
                queue.push(node->right);
            }
        }
        ans[ans.length() - 1] = ']';
        return ans;
    }

    // Decodes your encoded data to tree.
    TreeNode* deserialize(string data) {
        queue<string> queueVal;
        queue<TreeNode*> queueNode;
        int idx = 1, length = (int) data.length();
        // 扫描序列,将值保存进节点值的队列("null"可用于占位)
        while (idx < length - 1) {
            int tempIdx = idx;
            while (idx < length - 1 && data[idx] != ',') {  // 直到idx指向','
                idx++;
            }
            queueVal.push(data.substr(tempIdx, idx - tempIdx));
            idx++;
        }
        if (queueVal.front() == "null") {
            // root为null时
            return nullptr;
        }
        TreeNode* root = new TreeNode(stoi(queueVal.front()));  // 构建新TreeNode结构体
        queueVal.pop();
        queueNode.push(root);
        while (!queueNode.empty()) {
            int size = (int) queueNode.size();    // 每个node(非null)对应两个子节点
            for (int i = 0; i < size; i++) {
                TreeNode* node = queueNode.front();
                queueNode.pop();
                string leftStr = queueVal.front();
                queueVal.pop();
                string rightStr = queueVal.front();
                queueVal.pop();
                // 判断null节点,null则不加入队列
                if (leftStr != "null") {
                    TreeNode* left = new TreeNode(stoi(leftStr));
                    node->left = left;
                    queueNode.push(left);
                }
                if (rightStr != "null") {
                    TreeNode* right = new TreeNode(stoi(rightStr));
                    node->right = right;
                    queueNode.push(right);
                }
            }
        }

        return root;
    }
};

// Your Codec object will be instantiated and called as such:
// Codec ser, deser;
// TreeNode* ans = deser.deserialize(ser.serialize(root));
//leetcode submit region end(Prohibit modification and deletion)
