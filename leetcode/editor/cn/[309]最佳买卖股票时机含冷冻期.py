# 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。 
# 
#  设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）: 
# 
#  
#  你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。 
#  卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。 
#  
# 
#  示例: 
# 
#  输入: [1,2,3,0,2]
# 输出: 3 
# 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出] 
#  Related Topics 数组 动态规划 👍 871 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def maxProfit(self, prices: List[int]) -> int:      # DP(空间优化, 原版见java)
        if len(prices) < 2:
            return 0
        dp = [0] * 3
        dp[0] = - prices[0]
        for day in range(1, len(prices)):
            dp0 = max(dp[0], dp[2] - prices[day])
            dp1 = max(dp[1], dp[0] + prices[day])
            dp2 = max(dp[2], dp[1])
            dp[0], dp[1], dp[2] = dp0, dp1, dp2
        return max(dp[1], dp[2])

# leetcode submit region end(Prohibit modification and deletion)
