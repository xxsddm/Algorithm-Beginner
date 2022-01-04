//给定一个未排序的整数数组，找到最长递增子序列的个数。 
//
// 示例 1: 
//
// 
//输入: [1,3,5,4,7]
//输出: 2
//解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
// 
//
// 示例 2: 
//
// 
//输入: [2,2,2,2,2]
//输出: 5
//解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
// 
//
// 注意: 给定的数组长度不超过 2000 并且结果一定是32位有符号整数。 
// Related Topics 树状数组 线段树 数组 动态规划 👍 376 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution{
    public int findNumberOfLIS(int[] nums) {    // DP
        int length = nums.length, maxLength = 0, count = 0;
        int[][] dp = new int[length][2];
        for (int end = 0; end < length; end++) {
            // 以end为末端的最长递增子序列长度(索引0), 种类(索引1)
            dp[end][0] = 1;
            dp[end][1] = 1;
            // 枚举所有的前面节点prev
            for (int prev = 0; prev < end; prev++) {
                // 若prev<end,则end可接在prev后面 ( dp[end][0]更新为max(dp[end][0],dp[prev][0]+1) )
                if (nums[prev] < nums[end]) {
                    if (dp[prev][0] + 1 > dp[end][0]) {
                        dp[end][0] = dp[prev][0] + 1;
                        dp[end][1] = dp[prev][1];   // 更新最大长度时,重置种类数量
                    }
                    else if (dp[prev][0] + 1 == dp[end][0]) {
                        dp[end][1] += dp[prev][1];  // 相同长度则增加种类数量
                    }
                }

                // 若prev=end,则end可代替prev ( dp[end][0]更新为max(dp[end][0],dp[prev][0]) )
                // 这种情况可以省略,因为prev的更新过程0~prev-1其实也包含在end更新过程中,即遍历过prev-1次数end情况和prev相同
                // else if (nums[prev] == nums[end]) {
                //     if (dp[prev][0] > dp[end][0]) {
                //         dp[end][0] = dp[prev][0];
                //         dp[end][1] = dp[prev][1];   // 更新最大长度时,重置种类数量
                //     }
                //     else if (dp[prev][0] == dp[end][0]) {
                //         dp[end][1] = dp[prev][1];   // 长度相同时,prev由小到大,则种类数为较大prev对应种类数
                //     }
                }
            }
            // 更新最大长度,计算种类数
            if (dp[end][0] > maxLength) {
                maxLength = dp[end][0];
                count = dp[end][1]; // 更新最大长度则重置种类数量
            }
            else if (dp[end][0] == maxLength) {
                count += dp[end][1];    // end对应最长子序列长度同maxLength则增加种类数量
            }
        }

        return count;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
