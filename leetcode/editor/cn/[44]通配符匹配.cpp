//给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。 
//
// '?' 可以匹配任何单个字符。
//'*' 可以匹配任意字符串（包括空字符串）。
// 
//
// 两个字符串完全匹配才算匹配成功。 
//
// 说明: 
//
// 
// s 可能为空，且只包含从 a-z 的小写字母。 
// p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。 
// 
//
// 示例 1: 
//
// 输入:
//s = "aa"
//p = "a"
//输出: false
//解释: "a" 无法匹配 "aa" 整个字符串。 
//
// 示例 2: 
//
// 输入:
//s = "aa"
//p = "*"
//输出: true
//解释: '*' 可以匹配任意字符串。
// 
//
// 示例 3: 
//
// 输入:
//s = "cb"
//p = "?a"
//输出: false
//解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
// 
//
// 示例 4: 
//
// 输入:
//s = "adceb"
//p = "*a*b"
//输出: true
//解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
// 
//
// 示例 5: 
//
// 输入:
//s = "acdcb"
//p = "a*c?b"
//输出: false 
// Related Topics 贪心 递归 字符串 动态规划 👍 745 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    bool isMatch(string s, string p) {  // DP(空间优化, 由原版观察可得, 注意细节dp[0]=false)(原版见java)
        // s作为目标字符串txt, p作为模式字符串pat
        int lenTxt = (int) s.length(), lenPat = (int) p.length();
        // p子序列长度 -> 是否可以匹配     根据s子序列长度逐轮更新
        vector<bool> dp(lenPat + 1);
        // 初始化(长度为0部分匹配)
        dp[0] = true;
        for (int idxPat = 0; idxPat < lenPat && p[idxPat] == '*'; idxPat++) {
            dp[idxPat + 1] = true;
        }

        for (int idxTxt = 0; idxTxt < lenTxt; idxTxt++) {
            char tempTxt = s[idxTxt];
            vector<bool> prev(dp);
            for (int idxPat = 0; idxPat < lenPat; idxPat++) {
                char tempPat = p[idxPat];
                if (tempTxt == tempPat || tempPat == '?') {
                    dp[idxPat + 1] = prev[idxPat];
                }
                else if (tempPat == '*') {
                    dp[idxPat + 1] = prev[idxPat] || prev[idxPat + 1] || dp[idxPat];
                }
                else {
                    dp[idxPat + 1] = false;
                }
            }
            // !!!
            // 放在这里更新可以应对lenTxt=1的情况, 保证dp[0]=false(lenPat可能为0)一定执行, 且在更新一轮之后执行
            dp[0] = false;
        }

        return dp[lenPat];
    }
};

//leetcode submit region end(Prohibit modification and deletion)
