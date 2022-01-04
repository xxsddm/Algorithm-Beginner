# 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。 
# 
#  
# 
#  示例 1： 
# 
#  
# 
#  
# 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
# 输出：6
# 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 
#  
# 
#  示例 2： 
# 
#  
# 输入：height = [4,2,0,3,2,5]
# 输出：9
#  
# 
#  
# 
#  提示： 
# 
#  
#  n == height.length 
#  0 <= n <= 3 * 10⁴ 
#  0 <= height[i] <= 10⁵ 
#  
#  Related Topics 栈 数组 双指针 动态规划 单调栈 👍 2626 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def trap(self, height: List[int]) -> int:       # DP(双指针, 单调栈见java)
        dp = [0] * len(height)

        peak = 0
        for i in range(len(height)):
            peak = max(peak, height[i])
            dp[i] = peak

        peak = 0
        for i in range(len(height) - 1, - 1, - 1):
            peak = max(peak, height[i])
            dp[i] = min(peak, dp[i])

            # 可提前结束
            # if peak >= dp[i]:
            #     break
            # dp[i] = peak

        return sum(x - y for x, y in zip(dp, height))

# leetcode submit region end(Prohibit modification and deletion)
