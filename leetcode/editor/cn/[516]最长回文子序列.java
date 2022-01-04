//给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。 
//
// 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "bbbab"
//输出：4
//解释：一个可能的最长回文子序列为 "bbbb" 。
// 
//
// 示例 2： 
//
// 
//输入：s = "cbbd"
//输出：2
//解释：一个可能的最长回文子序列为 "bb" 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 仅由小写英文字母组成 
// 
// Related Topics 字符串 动态规划 
// 👍 533 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int longestPalindromeSubseq(String s) {
        int[][] dp = new int[s.length()][s.length()];
        // right-1 -> right, left+1 -> left (right从前向后更新, left从后向前更新)
        for (int right = 0; right < s.length(); right++) {
            for (int left = right; left >= 0; left--) {
                if (left == right) {
                    dp[left][right] = 1;
                }
                else if (s.charAt(left) == s.charAt(right)) {
                    dp[left][right] = dp[left + 1][right - 1] + 2;
                }
                else {
                    dp[left][right] = Math.max(dp[left][right - 1], dp[left + 1][right]);
                }
            }
        }
        return dp[0][s.length() - 1];
    }
}

//leetcode submit region end(Prohibit modification and deletion)


class Solution {
    String target;

    public int longestPalindromeSubseq(String s) {      // 超时
        target = s;
        return num(0, s.length() - 1);
    }

    private int num(int left, int right) {
        if (left == right) {
            return 1;
        }
        else if (left == right - 1) {
            return target.charAt(left) == target.charAt(right) ? 2: 1;
        }
        return Math.max(Math.max(num(left + 1, right), num(left, right - 1)),
                num(left + 1, right - 1) + (target.charAt(left) == target.charAt(right) ? 2: 0));
    }
}
