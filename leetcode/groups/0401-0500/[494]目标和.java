//给你一个整数数组 nums 和一个整数 target 。 
//
// 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ： 
//
// 
// 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。 
// 
//
// 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,1,1,1], target = 3
//输出：5
//解释：一共有 5 种方法让最终目标和为 3 。
//-1 + 1 + 1 + 1 + 1 = 3
//+1 - 1 + 1 + 1 + 1 = 3
//+1 + 1 - 1 + 1 + 1 = 3
//+1 + 1 + 1 - 1 + 1 = 3
//+1 + 1 + 1 + 1 - 1 = 3
// 
//
// 示例 2： 
//
// 
//输入：nums = [1], target = 1
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 20 
// 0 <= nums[i] <= 1000 
// 0 <= sum(nums[i]) <= 1000 
// -1000 <= target <= 1000 
// 
// Related Topics 数组 动态规划 回溯 👍 883 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int cumsum = 0;
        target = Math.abs(target);
        for (int num: nums) {
            cumsum += num;
        }
        if ((cumsum - target) % 2 == 1 || target > cumsum) {
            return 0;
        }
        int goal = cumsum - target >> 1;        // 组合出减去的部分(cumsum - target)/2
        int[] counter = new int[goal + 1];      // key: 目标值 -> value: 组合种类
        counter[0] = 1;
        for (int num: nums) {
            // 注意遍历顺序, 后面的值受前面的影响, 这num应该影响到下一轮而不影响本轮
            for (int i = goal - num; i >= 0; i--) {
                counter[i + num] += counter[i];
            }
        }
        return counter[goal];
    }
}

//leetcode submit region end(Prohibit modification and deletion)
