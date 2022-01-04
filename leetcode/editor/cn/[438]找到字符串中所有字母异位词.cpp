//给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。 
//
// 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。 
//
// 
//
// 示例 1: 
//
// 
//输入: s = "cbaebabacd", p = "abc"
//输出: [0,6]
//解释:
//起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
//起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
// 
//
// 示例 2: 
//
// 
//输入: s = "abab", p = "ab"
//输出: [0,1,2]
//解释:
//起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
//起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
//起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
// 
//
// 
//
// 提示: 
//
// 
// 1 <= s.length, p.length <= 3 * 10⁴ 
// s 和 p 仅包含小写字母 
// 
// Related Topics 哈希表 字符串 滑动窗口 👍 688 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    vector<int> findAnagrams(string s, string p) {
        vector<int> ans;
        if (s.size() < p.size()) {
            return ans;
        }
        int counterp[26], counter[26], lens = (int) s.size(), lenp = (int) p.size(), count = 0;
        memset(counterp, 0, sizeof(counterp));
        memset(counter, 0, sizeof(counter));
        for (char &c: p) {
            counterp[c - 'a']++;
        }
        for (int i = 0; i < lenp; i++) {
            counter[s[i] - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (counter[i] == counterp[i]) {
                count++;
            }
        }
        if (count == 26) {
            ans.push_back(0);
        }
        for (int i = 1; i <= lens - lenp; i++) {
            int idx1 = s[i - 1] - 'a', idx2 = s[i + lenp - 1] - 'a';
            if (counter[idx1]-- == counterp[idx1]) {
                count--;
            } else if (counter[idx1] == counterp[idx1]) {
                count++;
            }
            if (counter[idx2]++ == counterp[idx2]) {
                count--;
            } else if (counter[idx2] == counterp[idx2]) {
                count++;
            }
            if (count == 26) {
                ans.push_back(i);
            }
        }
        return ans;
    }
};

//leetcode submit region end(Prohibit modification and deletion)
