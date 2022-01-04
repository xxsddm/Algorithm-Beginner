# 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。 
# 
#  示例 1: 
# 
#  输入: 2
# 输出: 1
# 解释: 2 = 1 + 1, 1 × 1 = 1。 
# 
#  示例 2: 
# 
#  输入: 10
# 输出: 36
# 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。 
# 
#  说明: 你可以假设 n 不小于 2 且不大于 58。 
#  Related Topics 数学 动态规划 👍 577 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def integerBreak(self, n: int) -> int:
        nums = [0] * n
        for i in range(1, n):
            maxnum = 0
            for j in range(1, i):
                maxnum = max(maxnum, nums[i - j] * j)
            for j in range(1, int(math.sqrt(i + 1)) + 1):
                maxnum = max(maxnum, (i - j + 1) * j)
            nums[i] = maxnum
        return nums[n - 1]

# leetcode submit region end(Prohibit modification and deletion)
