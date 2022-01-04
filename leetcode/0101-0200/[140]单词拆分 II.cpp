//给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的
//句子。 
//
// 说明： 
//
// 
// 分隔时可以重复使用字典中的单词。 
// 你可以假设字典中没有重复的单词。 
// 
//
// 示例 1： 
//
// 输入:
//s = "catsanddog"
//wordDict = ["cat", "cats", "and", "sand", "dog"]
//输出:
//[
//  "cats and dog",
//  "cat sand dog"
//]
// 
//
// 示例 2： 
//
// 输入:
//s = "pineapplepenapple"
//wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
//输出:
//[
//  "pine apple pen apple",
//  "pineapple pen apple",
//  "pine applepen apple"
//]
//解释: 注意你可以重复使用字典中的单词。
// 
//
// 示例 3： 
//
// 输入:
//s = "catsandog"
//wordDict = ["cats", "dog", "sand", "and", "cat"]
//输出:
//[]
// 
// Related Topics 字典树 记忆化搜索 哈希表 字符串 动态规划 回溯 👍 547 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    struct Node {
        string val;
        Node* next[26];
    };

    int length;
    Node* root = new Node();
    vector<string> ans;

    vector<string> wordBreak(string s, vector<string>& wordDict) {
        length = (int) s.size();
        for (string &word : wordDict) {
            add(word);
        }
        deque<string> container;
        dfs(0, s, container);
        return ans;
    }

    void add(string &word) {
        Node* node = root;
        for (char &c : word) {
            if (node->next[c - 'a'] == nullptr) {
                node->next[c - 'a'] = new Node();
            }
            node = node->next[c - 'a'];
        }
        node->val = word;
    }

    void dfs(int start, string &s, deque<string> &container) {
        if (start == length) {
            string temp;
            for (string &word : container) {
                temp += word;
                temp += ' ';
            }
            temp.pop_back();
            ans.push_back(temp);
            return;
        }
        Node* node = root;
        for (int idx = start; idx < length; idx++) {
            node = node->next[s[idx] - 'a'];
            if (node == nullptr) {
                return;
            }
            if (!(node->val).empty()) {
                container.push_back(node->val);
                dfs(idx + 1, s, container);
                container.pop_back();
            }
        }
    }
};

//leetcode submit region end(Prohibit modification and deletion)
