//给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。 
//
// 字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列
//，而 "AEC" 不是） 
//
// 题目数据保证答案符合 32 位带符号整数范围。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "rabbbit", t = "rabbit"
//输出：3
//解释：
//如下图所示, 有 3 种可以从 s 中得到 "rabbit" 的方案。
//rabbbit
//rabbbit
//rabbbit 
//
// 示例 2： 
//
// 
//输入：s = "babgbag", t = "bag"
//输出：5
//解释：
//如下图所示, 有 5 种可以从 s 中得到 "bag" 的方案。 
//babgbag
//babgbag
//babgbag
//babgbag
//babgbag
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length, t.length <= 1000 
// s 和 t 由英文字母组成 
// 
// Related Topics 字符串 动态规划 👍 581 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int numDistinct(String s, String t) {
        // i+1, j+1: 目标t, 容器s中所涉及元素个数 -> s(0~j)所能组成t(0~i)的方式数量
        int[][] dp = new int[t.length() + 1][s.length() + 1];

        // 初始化, 包含目标t中0个元素时, 组合方式为1
        for (int i = 0; i <= s.length(); i++) {
            dp[0][i] = 1;
        }
        for (int i = 0; i < t.length(); i++) {
            // 索引j至少从索引i开始, 否则无法覆盖0~i-1部分
            for (int j = i; j < s.length(); j++) {
                // j元素(第j+1个)可与i元素(第i+1个)匹配, 则j也可以搭配之前(j-1, i-1)元素组合(0~j-1,0~i-1)现有的组合
                dp[i + 1][j + 1] = dp[i + 1][j] + (t.charAt(i) == s.charAt(j) ? dp[i][j] : 0);
            }
        }
        return dp[t.length()][s.length()];
    }
}

//leetcode submit region end(Prohibit modification and deletion)
