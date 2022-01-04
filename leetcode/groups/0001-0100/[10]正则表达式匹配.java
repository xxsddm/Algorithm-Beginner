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
//输入：s = "aa" p = "a"
//输出：false
//解释："a" 无法匹配 "aa" 整个字符串。
// 
//
// 示例 2: 
//
// 
//输入：s = "aa" p = "a*"
//输出：true
//解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
// 
//
// 示例 3： 
//
// 
//输入：s = "ab" p = ".*"
//输出：true
//解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
// 
//
// 示例 4： 
//
// 
//输入：s = "aab" p = "c*a*b"
//输出：true
//解释：因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
// 
//
// 示例 5： 
//
// 
//输入：s = "mississippi" p = "mis*is*p*."
//输出：false 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 20 
// 0 <= p.length <= 30 
// s 可能为空，且只包含从 a-z 的小写字母。 
// p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。 
// 保证每次出现字符 * 时，前面都匹配到有效的字符 
// 
// Related Topics 递归 字符串 动态规划 👍 2326 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isMatch(String s, String p) {        // DP(空间优化, 原版观察可得)
        int lens = s.length(), lenp = p.length();
        // 考虑s长度idxs+1的子串(0~idxs)和p长度idxp+1的子串(0~idxp)是否匹配
        boolean[] dp = new boolean[lenp + 1];

        // 初始化('x'+'*'一定可以匹配)
        dp[0] = true;
        for (int i = 0; i < lenp; i += 2) {
            if (i + 1 < lenp && p.charAt(i + 1) == '*') {
                dp[i + 2] = true;
            }
            else {
                break;
            }
        }

        for (int i = 0; i < lens; i++) {
            boolean[] temp = new boolean[lenp + 1];
            for (int j = 0; j < lenp; j++) {
                if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {
                    temp[j + 1] = dp[j];
                }
                else if (p.charAt(j) == '*') {
                    if (s.charAt(i) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                        temp[j + 1] = dp[j] || dp[j + 1];
                    }

                    temp[j + 1] |= temp[j - 1];
                }
            }
            dp = temp;
        }
        return dp[lenp];
    }
}

//leetcode submit region end(Prohibit modification and deletion)


class Solution {
    public boolean isMatch(String s, String p) {        // DP
        int lens = s.length(), lenp = p.length();
        // 考虑s长度idxs+1的子串(0~idxs)和p长度idxp+1的子串(0~idxp)是否匹配
        boolean[][] dp = new boolean[lens + 1][lenp + 1];

        // 初始化('x'+'*'一定可以匹配)
        dp[0][0] = true;
        for (int i = 0; i < lenp; i += 2) {
            if (i + 1 < lenp && p.charAt(i + 1) == '*') {
                dp[0][i + 2] = true;
            }
            else {
                break;
            }
        }

        for (int i = 0; i < lens; i++) {
            for (int j = 0; j < lenp; j++) {

                // 当前s[i], p[j]匹配, 则考虑s[0~i-1]与p[0~j-1](dp[i][j])
                if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {
                    dp[i + 1][j + 1] = dp[i][j];
                }

                // 1. 当前p[j]='*', 考虑p[j-1](被*指定可重复字符)是否与s[i]匹配
                // 2. 若匹配, 则进一步考虑前段部分s[0~i-1]与p[0~j-2](第一个可重复字符之前)是否匹配(dp[i][j-1]),
                //    与其等价的是s[0~i]与p[0~j-1](s第一个可重复字符)是否匹配(dp[i][j], 由前面if部分可看出等价)
                // 3. 以及s[0~i-1]与p[0~j](s后续可重复字符匹配情况同第一个可重复字符i->i+1)是否匹配(dp[i][j+1])
                else if (p.charAt(j) == '*') {
                    if (s.charAt(i) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                        dp[i + 1][j + 1] = dp[i][j] || dp[i][j + 1];    // dp[i][j-1] || dp[i][j + 1]
                    }

                    // 'x'+'*'组合可被跳过, 因此若s[0~i]与p[0~j-1-1](dp[i+1][j-1])匹配
                    // 则s[0~i]与p[0~j](dp[i+1][j+1])一定匹配(跳过'x'+'*')
                    dp[i + 1][j + 1] |= dp[i + 1][j - 1];
                }
            }
        }
        return dp[lens][lenp];
    }
}
