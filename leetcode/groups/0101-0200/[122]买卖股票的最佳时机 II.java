//给定一个数组 prices ，其中 prices[i] 是一支给定股票第 i 天的价格。 
//
// 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。 
//
// 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。 
//
// 
//
// 示例 1: 
//
// 
//输入: prices = [7,1,5,3,6,4]
//输出: 7
//解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
//     随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
// 
//
// 示例 2: 
//
// 
//输入: prices = [1,2,3,4,5]
//输出: 4
//解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
//     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
// 
//
// 示例 3: 
//
// 
//输入: prices = [7,6,4,3,1]
//输出: 0
//解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。 
//
// 
//
// 提示： 
//
// 
// 1 <= prices.length <= 3 * 10⁴ 
// 0 <= prices[i] <= 10⁴ 
// 
// Related Topics 贪心 数组 动态规划 👍 1331 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxProfit(int[] prices) {        // 所有递增最短子序列的收益和
        int profit = 0;
        for (int day = 0; day < prices.length - 1; day++) {
            int temp = prices[day + 1] - prices[day];
            profit += Math.max(temp, 0);
        }
        return profit;
    }
}

//leetcode submit region end(Prohibit modification and deletion)


class Solution {
    public int maxProfit(int[] prices) {    // DP
        if (prices.length < 2) {
            return 0;
        }
        // 日期; 0: 持有; 1: 未持有 -> 剩余金额
        int[][] dp = new int[prices.length][2];
        dp[0][0] = - prices[0];
        for (int day = 1; day < prices.length; day++) {
            dp[day][0] = Math.max(dp[day - 1][0], dp[day - 1][1] - prices[day]);    // 考虑再次买入
            dp[day][1] = Math.max(dp[day - 1][1], dp[day - 1][0] + prices[day]);
        }
        return dp[prices.length - 1][1];
    }
}
