//所有 DNA 都由一系列缩写为 'A'，'C'，'G' 和 'T' 的核苷酸组成，例如："ACGAATTCCG"。在研究 DNA 时，识别 DNA 中的重复
//序列有时会对研究非常有帮助。 
//
// 编写一个函数来找出所有目标子串，目标子串的长度为 10，且在 DNA 字符串 s 中出现次数超过一次。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
//输出：["AAAAACCCCC","CCCCCAAAAA"]
// 
//
// 示例 2： 
//
// 
//输入：s = "AAAAAAAAAAAAA"
//输出：["AAAAAAAAAA"]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 10⁵ 
// s[i] 为 'A'、'C'、'G' 或 'T' 
// 
// Related Topics 位运算 哈希表 字符串 滑动窗口 哈希函数 滚动哈希 👍 196 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    vector<string> findRepeatedDnaSequences(string s) { // hash(排序和位运算见java)
        vector<string> ans;
        int length = s.size() - 10 + 1;
        if (length <= 0) {
            return ans;
        }
        unordered_map<string, int> counter;
        for (int i = 0; i < length; i++) {
            // cpp截取第二参数为子序列长度
            string temp = s.substr(i, 10);
            counter[temp]++;
        }
        for (const auto& keyValue: counter) {
            if (keyValue.second >= 1) {
                ans.push_back(keyValue.first);
            }
        }
        return ans;
    }
};

//leetcode submit region end(Prohibit modification and deletion)
