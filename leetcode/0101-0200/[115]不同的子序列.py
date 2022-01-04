# 给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。 
# 
#  字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列
# ，而 "AEC" 不是） 
# 
#  题目数据保证答案符合 32 位带符号整数范围。 
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：s = "rabbbit", t = "rabbit"
# 输出：3
# 解释：
# 如下图所示, 有 3 种可以从 s 中得到 "rabbit" 的方案。
# rabbbit
# rabbbit
# rabbbit 
# 
#  示例 2： 
# 
#  
# 输入：s = "babgbag", t = "bag"
# 输出：5
# 解释：
# 如下图所示, 有 5 种可以从 s 中得到 "bag" 的方案。 
# babgbag
# babgbag
# babgbag
# babgbag
# babgbag
#  
# 
#  
# 
#  提示： 
# 
#  
#  0 <= s.length, t.length <= 1000 
#  s 和 t 由英文字母组成 
#  
#  Related Topics 字符串 动态规划 👍 581 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def numDistinct(self, s: str, t: str) -> int:
        if len(s) < len(t):
            return 0
        length1 = len(s)
        length2 = len(t)
        # idxs+1, idxt+1: 容器s, 目标t中所涉及元素个数 -> s(0~idxs)所能组成t(0~idxt)的方式数量
        dp = [[0] * (length2 + 1) for _ in range(length1 + 1)]
        for i in range(length1 + 1):
            dp[i][0] = 1
        for idxt in range(length2):
            for idxs in range(idxt, length1):
                dp[idxs + 1][idxt + 1] = dp[idxs][idxt + 1]\
                        + (dp[idxs][idxt] if s[idxs] == t[idxt] else 0)
        return dp[length1][length2]

# leetcode submit region end(Prohibit modification and deletion)
