//给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。 
//
// 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。 
//
// 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。 
//
// 
//
// 示例 1: 
//
// 
//输入：prices = [3,3,5,0,0,3,1,4]
//输出：6
//解释：在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
//     随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。 
//
// 示例 2： 
//
// 
//输入：prices = [1,2,3,4,5]
//输出：4
//解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。   
//     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。   
//     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
// 
//
// 示例 3： 
//
// 
//输入：prices = [7,6,4,3,1] 
//输出：0 
//解释：在这个情况下, 没有交易完成, 所以最大利润为 0。 
//
// 示例 4： 
//
// 
//输入：prices = [1]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= prices.length <= 10⁵ 
// 0 <= prices[i] <= 10⁵ 
// 
// Related Topics 数组 动态规划 👍 854 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxProfit(int[] prices) {    // DP
        if (prices.length < 2) {
            return 0;
        }
        // 日期, 状态 -> 利润
        // 0: 空过; 1: 第一次买入; 2: 第一次卖出; 3: 第二次买入; 4: 第二次卖出
        // 当前状态不是当期操作, 可以是之前就已经操作. 如1: 当期或前期买入, 未卖出
        int[][] dp = new int[prices.length][5];
        dp[0][1] = - prices[0];
        dp[0][3] = - prices[0];     // 这个初始化可视为第一天买入卖出再买入
        for (int day = 1; day < prices.length; day++) {
            dp[day][1] = Math.max(dp[day - 1][1], - prices[day]);   // dp[day - 1][0] - prices[day])
            dp[day][2] = Math.max(dp[day - 1][2], dp[day - 1][1] + prices[day]);
            dp[day][3] = Math.max(dp[day - 1][3], dp[day - 1][2] - prices[day]);
            dp[day][4] = Math.max(dp[day - 1][4], dp[day - 1][3] + prices[day]);
        }
        return dp[prices.length - 1][4];
    }
}

//leetcode submit region end(Prohibit modification and deletion)


class Solution {
    public int maxProfit(int[] prices) {    // DP(空间优化)
        if (prices.length < 2) {
            return 0;
        }
        // 日期, 状态 -> 利润
        // 0: 第一次买入; 1: 第一次卖出; 2: 第二次买入; 3: 第二次卖出
        // 当前状态不是当期操作, 可以是之前就已经操作. 如0: 当期或前期买入, 未卖出
        int[] dp = new int[4];
        dp[0] = - prices[0];
        dp[2] = - prices[0];     // 这个初始化可视为第一天买入卖出再买入
        for (int day = 1; day < prices.length; day++) {
            // 按理应该dp[3]~dp[0]遍历, 但不变顺序也是对的
            dp[0] = Math.max(dp[0], - prices[day]);
            dp[1] = Math.max(dp[1], dp[0] + prices[day]);
            dp[2] = Math.max(dp[2], dp[1] - prices[day]);
            dp[3] = Math.max(dp[3], dp[2] + prices[day]);
        }
        return dp[3];
    }
}
