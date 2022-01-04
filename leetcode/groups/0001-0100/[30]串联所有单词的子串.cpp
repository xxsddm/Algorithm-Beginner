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
        vector<int> container;
        int totalLength = (int) words[0].length() * (int) words.size(); // 单词长度相同
        if (totalLength > s.length()) { // 排除不可能匹配的情况
            return container;
        }
        vector<int> nums(26), wordNums(26);
        for (char letter : s) {
            nums[letter - 'a']++;
        }
        for (string word: words) {  // 排除不可能匹配的情况(这段删了反而更快)
            for (char letter : word) {
                int idx = letter - 'a';
                wordNums[idx]++;
                if (wordNums[idx] > nums[idx]) {
                    return container;
                }
            }
        }
        unordered_map<string, int> counter; // 记录每种单词的数量
        for (string word: words) {
            counter[word]++;
        }
        int length = (int) words[0].length();
        // match记录每个s索引开始, 可以匹配的单词(无法匹配则为null)
        vector<string> match(s.length());
        for (int i = 0; i <= s.length() - length; i++) {
            string temp = s.substr(i, length);
            for (string word: words) {
                if (temp == word) {
                    match[i] = word;
                    break;
                }
            }
        }
        // 枚举所有可能的起点
        for (int i = 0; i <= s.length() - totalLength; i++) {
            bool skip = true;
            // 利用单词相等长度的性质, 每隔length索引检查一次match的值是否为null
            for (int j = i; j <= i + totalLength - length; j += length) {
                if (match[j].empty()) {
                    break;
                }
                if (j == i + totalLength - length) {
                    skip = false;
                    break;
                }
            }
            if (skip) {
                continue;
            }
            // match通过初步判定, 可以进一步判断是否可以完整拼接
            unordered_map<string, int> tempCounter(counter);
            for (int j = i; j <= i + totalLength - length; j += length) {
                string word = match[j]; // match[j]表示当前索引j下匹配的单词
                tempCounter[word]--;
                if (tempCounter[word] < 0) {    // 若s匹配的单词已超出单词数量, 则不可能匹配
                    break;
                }
                if (j == i + totalLength - length) {    // 完成匹配, 记录起点i
                    container.push_back(i);
                }
            }
        }
        return container;
    }
};

//leetcode submit region end(Prohibit modification and deletion)
