//给定字符串 s 和 t ，判断 s 是否为 t 的子序列。 
//
// 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而
//"aec"不是）。 
//
// 进阶： 
//
// 如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代
//码？ 
//
// 致谢： 
//
// 特别感谢 @pbrother 添加此问题并且创建所有测试用例。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "abc", t = "ahbgdc"
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：s = "axc", t = "ahbgdc"
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 100 
// 0 <= t.length <= 10^4 
// 两个字符串都只由小写字符组成。 
// 
// Related Topics 双指针 字符串 动态规划 👍 496 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isSubsequence(String s, String t) {      // 双指针
        if (s.length() > t.length()) {
            return false;
        }
        int slow = 0, fast = 0;
        while (slow < s.length() && fast < t.length()) {
            if (s.charAt(slow) == t.charAt(fast)) {
                slow++;
            }
            else if (t.length() - fast - 1 < s.length() - slow) {
                return false;
            }
            fast++;
        }
        return slow == s.length();
    }
}

//leetcode submit region end(Prohibit modification and deletion)


class Solution {
    public boolean isSubsequence(String s, String t) {      // DP(转换为最长公共子序列问题, 很慢)
        if (s.length() == 0) {
            return true;
        }
        if (s.length() > t.length()) {
            return false;
        }
        int length1 = s.length(), length2 = t.length();
        int[][] dp = new int[length1 + 1][length2 + 1];
        for (int i = 0; i < length1; i++) {
            for (int j = 0; j < length2; j++) {
                if (s.charAt(i) == t.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                    // 可提前退出
                    if (dp[i + 1][j + 1] == length1) {
                        return true;
                    }
                }
                else {
                    // 与最长公共子序列问题不同, 不相同时相当于t中指针未移动
                    dp[i + 1][j + 1] = dp[i + 1][j];
                    // 可提前退出(仅需不相同时判断)
                    if (length2 - dp[i + 1][j + 1] < (length1 - i - 1)) {
                        return false;
                    }
                }
            }
        }
        return false;
    }
}
