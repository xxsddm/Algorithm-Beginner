//请实现一个函数用来匹配包含'. '和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）。在本题中，匹配
//是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配。 
//
// 示例 1: 
//
// 输入:
//s = "aa"
//p = "a"
//输出: false
//解释: "a" 无法匹配 "aa" 整个字符串。
// 
//
// 示例 2: 
//
// 输入:
//s = "aa"
//p = "a*"
//输出: true
//解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
// 
//
// 示例 3: 
//
// 输入:
//s = "ab"
//p = ".*"
//输出: true
//解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
// 
//
// 示例 4: 
//
// 输入:
//s = "aab"
//p = "c*a*b"
//输出: true
//解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
// 
//
// 示例 5: 
//
// 输入:
//s = "mississippi"
//p = "mis*is*p*."
//输出: false 
//
// 
// s 可能为空，且只包含从 a-z 的小写字母。 
// p 可能为空，且只包含从 a-z 的小写字母以及字符 . 和 *，无连续的 '*'。 
// 
//
// 注意：本题与主站 10 题相同：https://leetcode-cn.com/problems/regular-expression-matching/
// 
// Related Topics 递归 字符串 动态规划 👍 272 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    bool isMatch(string s, string p) {  // DP(具体说明及空间优化见java)
        int lenTxt = s.size(), lenExp = p.size();
        // s匹配长度, p匹配长度 -> 是否匹配
        vector<vector<bool>> dp(lenTxt + 1, vector<bool>(lenExp + 1, false));
        dp[0][0] = true;
        int idx = 1;
        while (idx < lenExp && p[idx] == '*') { // 初始化txt匹配长度为0的情况
            dp[0][idx + 1] = true;
            idx += 2;
        }
        for (int len1 = 1; len1 < lenTxt + 1; len1++) {
            for (int len2 = 1; len2 < lenExp + 1; len2++) {
                if (s[len1 - 1] == p[len2 - 1] || p[len2 - 1] == '.') {
                    dp[len1][len2] = dp[len1 - 1][len2 - 1];
                }
                else if (p[len2 - 1] == '*') {
                    if (s[len1 - 1] == p[len2 - 2] || p[len2 - 2] == '.') {
                        // 第一个匹配元素, 后续匹配元素
                        dp[len1][len2] = dp[len1 - 1][len2 - 2] || dp[len1 - 1][len2];
                    }
                    if (dp[len1][len2 - 2]) {   // '*'可忽略, 故前面元素不匹配仍可继承exp前两位情况
                        dp[len1][len2] = true;
                    }

                }
            }
        }
        return dp[lenTxt][lenExp];
    }
};

//leetcode submit region end(Prohibit modification and deletion)
