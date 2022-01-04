# 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。 
# 
#  子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。 
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：s = "bbbab"
# 输出：4
# 解释：一个可能的最长回文子序列为 "bbbb" 。
#  
# 
#  示例 2： 
# 
#  
# 输入：s = "cbbd"
# 输出：2
# 解释：一个可能的最长回文子序列为 "bb" 。
#  
# 
#  
# 
#  提示： 
# 
#  
#  1 <= s.length <= 1000 
#  s 仅由小写英文字母组成 
#  
#  Related Topics 字符串 动态规划 
#  👍 533 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def longestPalindromeSubseq(self, s: str) -> int:   # DP
        length = len(s)
        dp = [[0] * length for _ in range(length)]      # row: 左端点; column: 右端点; value: 最长回文子序列长度
        for left in range(length - 1, - 1, - 1):        # 子序列的左端点
            for right in range(left, length):           # 子序列的右端点
                if left == right:
                    dp[left][right] = 1
                elif s[left] == s[right]:
                    dp[left][right] = dp[left + 1][right - 1] + 2
                else:
                    dp[left][right] = max(dp[left + 1][right], dp[left][right - 1])

        return dp[0][length - 1]

# leetcode submit region end(Prohibit modification and deletion)


class Solution:
    def longestPalindromeSubseq(self, s: str) -> int:    # 超时

        def num(left=0, right=len(s) - 1):
            if left == right:
                return 1
            elif left == right - 1:
                return 2 if (s[left] == s[right]) else 1
            return (max(num(left + 1, right), num(left, right - 1),
                        num(left + 1, right - 1) + (s[left] == s[right]) * 2))

        return num()
