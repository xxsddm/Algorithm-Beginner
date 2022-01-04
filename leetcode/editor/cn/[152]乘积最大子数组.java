//给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。 
//
// 
//
// 示例 1: 
//
// 输入: [2,3,-2,4]
//输出: 6
//解释: 子数组 [2,3] 有最大乘积 6。
// 
//
// 示例 2: 
//
// 输入: [-2,0,-1]
//输出: 0
//解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。 
// Related Topics 数组 动态规划 
// 👍 1194 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxProduct(int[] nums) {
        int maxbefore = nums[0], minbefore = maxbefore, maxmul = maxbefore, temp = maxbefore;
        for (int i = 1; i < nums.length; i++){
            temp = maxbefore;   // 备份前部最大值
            maxbefore = Math.max(Math.max(maxbefore * nums[i], minbefore * nums[i]), nums[i]);
            minbefore = Math.min(Math.min(temp * nums[i], minbefore * nums[i]), nums[i]);
            maxmul = Math.max(maxmul, maxbefore);
        }
        return maxmul;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
