//给定一个字符串 s 和一些 长度相同 的单词 words 。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
//
// 注意子串要与 words 中的单词完全匹配，中间不能有其他字符 ，但不需要考虑 words 中单词串联的顺序。
//
//
//
// 示例 1：
//
//
//输入：s = "barfoothefoobarman", words = ["foo","bar"]
//输出：[0,9]
//解释：
//从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
//输出的顺序不重要, [9,0] 也是有效答案。
//
//
// 示例 2：
//
//
//输入：s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
//输出：[]
//
//
// 示例 3：
//
//
//输入：s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
//输出：[6,9,12]
//
//
//
//
// 提示：
//
//
// 1 <= s.length <= 10⁴
// s 由小写英文字母组成
// 1 <= words.length <= 5000
// 1 <= words[i].length <= 30
// words[i] 由小写英文字母组成
//
// Related Topics 哈希表 字符串 滑动窗口 👍 539 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    vector<int> findSubstring(string s, vector<string>& words) {
        vector<int> ans;
        int totalLength = (int) words[0].length() * (int) words.size(); // 单词长度相同
        if (totalLength > s.length()) { // 排除不可能匹配的情况
            return ans;
        }
        int nums[26];
        memset(nums, 0, sizeof(nums));
        for (char &letter : s) {
            nums[letter - 'a']++;
        }
        for (string &word : words) {    // 排除不可能匹配的情况
            for (char &letter : word) {
                int idx = letter - 'a';
                if (--nums[idx] < 0) {
                    return ans;
                }
            }
        }
        int counter[words.size()];
        memset(counter, 0, sizeof(counter));
        unordered_map<string, int> word2idx;
        for (string &word : words) {
            if (!word2idx.count(word)) {
                word2idx[word] = (int) word2idx.size();
            }
            counter[word2idx[word]]++;
        }
        int length = (int) words[0].length(), match[s.length()], cumsum[s.length()];
        memset(match, -1, sizeof(match));
        memset(cumsum, 0, sizeof(cumsum));
        for (int i = 0, limit = (int) s.length() - length; i <= limit; i++) {
            string temp = s.substr(i, length);
            if (word2idx.count(temp)) {
                match[i] = word2idx[temp];
                cumsum[i] = 1;
                if (i - length >= 0) {
                    cumsum[i] += cumsum[i - length];
                }
            }
        }
        // 枚举所有可能的起点
        for (int i = 0, size = (int) words.size(), limit = (int) s.length() - totalLength,
                tempCounter[word2idx.size()]; i <= limit; i++) {
            if (match[i] == -1 || cumsum[i + totalLength - length] - cumsum[i] != size - 1) {
                continue;
            }
            // match通过初步判定, 可以进一步判断是否可以完整拼接
            memset(tempCounter, 0, sizeof(tempCounter));
            for (int j = i, temp = i + totalLength - length; j <= temp; j += length) {
                int &idx = match[j];
                if (++tempCounter[idx] > counter[idx]) {
                    break;
                }
                if (j == temp) {    // 完成匹配, 记录起点i
                    ans.push_back(i);
                }
            }
        }
        return ans;
    }
};

//leetcode submit region end(Prohibit modification and deletion)
