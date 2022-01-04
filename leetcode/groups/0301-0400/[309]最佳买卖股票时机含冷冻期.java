//给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。 
//
// 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）: 
//
// 
// 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。 
// 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。 
// 
//
// 示例: 
//
// 输入: [1,2,3,0,2]
//输出: 3 
//解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出] 
// Related Topics 数组 动态规划 👍 871 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxProfit(int[] prices) {        // DP(空间优化)
        if (prices.length < 2) {
            return 0;
        }
        int[] dp = new int[3];
        dp[0] = - prices[0];
        for (int day = 1; day < prices.length; day++) {
            int dp0 = Math.max(dp[0], dp[2] - prices[day]);
            int dp1 = Math.max(dp[1], dp[0] + prices[day]);
            int dp2 = Math.max(dp[2], dp[1]);      // 比常规股票问题多一个冷冻期
            dp[0] = dp0;
            dp[1] = dp1;
            dp[2] = dp2;
        }
        // 最后的取值可能在刚好出售的日期, 也可能是之后的冷冻期
        return Math.max(dp[1], dp[2]);
    }
}

//leetcode submit region end(Prohibit modification and deletion)


class Solution {
    public int maxProfit(int[] prices) {        // DP(不同于其他股票问题, 不能直接空间优化, 需设置临时新变量)
        if (prices.length < 2) {
            return 0;
        }
        int[][] dp = new int[prices.length][3];
        dp[0][0] = - prices[0];
        for (int day = 1; day < prices.length; day++) {
            dp[day][0] = Math.max(dp[day - 1][0], dp[day - 1][2] - prices[day]);
            dp[day][1] = Math.max(dp[day - 1][1], dp[day - 1][0] + prices[day]);
            dp[day][2] = Math.max(dp[day - 1][2], dp[day - 1][1]);      // 比常规股票问题多一个冷冻期
        }
        // 最后的取值可能在刚好出售的日期, 也可能是之后的冷冻期
        return Math.max(dp[prices.length - 1][1], dp[prices.length - 1][2]);
    }
}
