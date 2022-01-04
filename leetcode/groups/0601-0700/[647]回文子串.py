# 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。 
# 
#  具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。 
# 
#  
# 
#  示例 1： 
# 
#  输入："abc"
# 输出：3
# 解释：三个回文子串: "a", "b", "c"
#  
# 
#  示例 2： 
# 
#  输入："aaa"
# 输出：6
# 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa" 
# 
#  
# 
#  提示： 
# 
#  
#  输入的字符串长度不会超过 1000 。 
#  
#  Related Topics 字符串 动态规划 
#  👍 650 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def countSubstrings(self, s: str) -> int:    # 中心扩散硬刚
        self.count = 0
        length = len(s)

        def count(left, right):
            while left >= 0 and right < length and s[left] == s[right]:
                self.count += 1
                left -= 1
                right += 1

        for i in range(length):
            count(i, i)
            count(i, i + 1)

        return self.count

# leetcode submit region end(Prohibit modification and deletion)


class Solution:
    def countSubstrings(self, s: str) -> int:  # DP
        length = len(s)
        count = 0

        # left~right是否为回文字符串
        dp = [[False] * length for _ in range(length)]
        for left in range(length - 1, - 1, - 1):
            for right in range(left, length):
                if left == right:
                    dp[left][right] = True
                    count += 1
                elif (dp[left + 1][right - 1] or left + 1 == right) and s[left] == s[right]:
                    dp[left][right] = True
                    count += 1

        return count
