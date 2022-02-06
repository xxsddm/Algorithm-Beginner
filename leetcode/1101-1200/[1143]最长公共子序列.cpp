//给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
//
// 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
//
//
// 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
//
//
// 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
//
//
//
// 示例 1：
//
//
//输入：text1 = "abcde", text2 = "ace"
//输出：3
//解释：最长公共子序列是 "ace" ，它的长度为 3 。
//
//
// 示例 2：
//
//
//输入：text1 = "abc", text2 = "abc"
//输出：3
//解释：最长公共子序列是 "abc" ，它的长度为 3 。
//
//
// 示例 3：
//
//
//输入：text1 = "abc", text2 = "def"
//输出：0
//解释：两个字符串没有公共子序列，返回 0 。
//
//
//
//
// 提示：
//
//
// 1 <= text1.length, text2.length <= 1000
// text1 和 text2 仅由小写英文字符组成。
//
// Related Topics 字符串 动态规划 👍 830 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int longestCommonSubsequence(string &text1, string &text2) {    // DP
        if (text1.size() < text2.size()) {
            swap(text1, text2);
        }
        int length1 = (int) text1.size(), length2 = (int) text2.size();
        int dp[2][length2 + 1], idx = 0;
        dp[0][0] = 0;
        memset(dp[1], 0, sizeof(dp[1]));
        for (int i = 0; i < length1; i++, idx = 1 - idx) {
            int back = 1 - idx;
            char &temp = text1[i];
            for (int j = 0; j < length2; j++) {
                dp[idx][j + 1] = temp == text2[j] ?
                                 (dp[back][j] + 1) : max(dp[idx][j], dp[back][j + 1]);
            }
        }
        return dp[1 - idx][length2];
    }
};

//leetcode submit region end(Prohibit modification and deletion)
