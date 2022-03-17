//给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
//
//
// '.' 匹配任意单个字符
// '*' 匹配零个或多个前面的那一个元素
//
//
// 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
//
//
// 示例 1：
//
//
//输入：s = "aa", p = "a"
//输出：false
//解释："a" 无法匹配 "aa" 整个字符串。
//
//
// 示例 2:
//
//
//输入：s = "aa", p = "a*"
//输出：true
//解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
//
//
// 示例 3：
//
//
//输入：s = "ab", p = ".*"
//输出：true
//解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
//
//
//
//
// 提示：
//
//
// 1 <= s.length <= 20
// 1 <= p.length <= 30
// s 只包含从 a-z 的小写字母。
// p 只包含从 a-z 的小写字母，以及字符 . 和 *。
// 保证每次出现字符 * 时，前面都匹配到有效的字符
//
// Related Topics 递归 字符串 动态规划 👍 2809 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    bool isMatch(string &s, string &p) {
        int len1 = (int) s.size(), len2 = (int) p.size(), cur = 1;
        bool dp[2][len2 + 1];
        memset(dp, false, sizeof(dp));
        for (int i = 1; i < len2; i += 2) {
            if (p[i] != '*') {
                break;
            }
            dp[0][i + 1] = true;
        }
        dp[0][0] = true;
        for (int i = 0; i < len1; i++) {
            char &letter = s[i];
            int prev = 1 - cur;
            for (int j = 0; j < len2; j++) {
                if (p[j] == letter || p[j] == '.') {
                    dp[cur][j + 1] = dp[prev][j];
                } else if (p[j] == '*') {
                    dp[cur][j + 1] = (p[j - 1] == letter || p[j - 1] == '.') && (dp[prev][j] || dp[prev][j + 1]) || dp[cur][j - 1];
                } else {
                    dp[cur][j + 1] = false;
                }
            }
            dp[0][0] = false;
            cur = prev;
        }
        return dp[1 - cur][len2];
    }
};

//leetcode submit region end(Prohibit modification and deletion)
