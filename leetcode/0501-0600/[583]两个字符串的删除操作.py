# 给定两个单词 word1 和 word2，找到使得 word1 和 word2 相同所需的最小步数，每步可以删除任意一个字符串中的一个字符。 
# 
#  
# 
#  示例： 
# 
#  输入: "sea", "eat"
# 输出: 2
# 解释: 第一步将"sea"变为"ea"，第二步将"eat"变为"ea"
#  
# 
#  
# 
#  提示： 
# 
#  
#  给定单词的长度不超过500。 
#  给定单词中的字符只含有小写字母。 
#  
#  Related Topics 字符串 动态规划 👍 219 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:     # 可以考虑
    def minDistance(self, word1: str, word2: str) -> int:       # DP(空间优化, 原版见java)
        if len(word1) > len(word2):
            word1, word2 = word2, word1
        length1, length2 = len(word1), len(word2)
        dp = [_ for _ in range(length1 + 1)]
        for j in range(length2):
            temp = [0] * (length1 + 1)
            temp[0] = j + 1
            for i in range(length1):
                if word1[i] == word2[j]:
                    temp[i + 1] = dp[i]
                else:
                    temp[i + 1] = min(temp[i], dp[i + 1]) + 1
            dp = temp
        return dp[length1]

# leetcode submit region end(Prohibit modification and deletion)
