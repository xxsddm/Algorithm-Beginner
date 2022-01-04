//给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。 
//
// 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。 
//
// 
//
// 示例 1： 
//
// 输入："abc"
//输出：3
//解释：三个回文子串: "a", "b", "c"
// 
//
// 示例 2： 
//
// 输入："aaa"
//输出：6
//解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa" 
//
// 
//
// 提示： 
//
// 
// 输入的字符串长度不会超过 1000 。 
// 
// Related Topics 字符串 动态规划 
// 👍 650 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int count = 0;
    String str;

    public int countSubstrings(String s) {
        str = s;
        for (int i = 0; i < s.length(); i++) {
            count(i, i);
            count(i, i + 1);
        }
        return count;
    }

    private void count(int left, int right) {
        while (left >= 0 && right < str.length() && str.charAt(left) == str.charAt(right)) {
            count++;
            left--;
            right++;
        }
    }
}

//leetcode submit region end(Prohibit modification and deletion)


class Solution {
    public int countSubstrings(String s) {      // DP
        // left~right是否为回文字符串
        boolean[][] dp = new boolean[s.length()][s.length()];
        // 初始化
        int count = s.length();
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
        }

        for (int left = s.length() - 1; left >= 0; left--) {
            for (int right = left + 1; right < s.length(); right++)
                if (s.charAt(left) == s.charAt(right)
                        && (dp[left + 1][right - 1] || left + 1 == right)) {
                    dp[left][right] = true;
                    count++;
                }
        }

        return count;
    }
}
