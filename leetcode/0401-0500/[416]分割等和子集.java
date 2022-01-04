//给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,5,11,5]
//输出：true
//解释：数组可以分割成 [1, 5, 5] 和 [11] 。 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3,5]
//输出：false
//解释：数组不能分割成两个元素和相等的子集。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 200 
// 1 <= nums[i] <= 100 
// 
// Related Topics 数组 动态规划 👍 917 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean canPartition(int[] nums) {
        int target = 0;
        for (int num: nums) {
            target += num;
        }
        if (target % 2 == 1) {
            return false;
        }
        target >>= 1;
        // 即01背包问题, 容量为target, 重量和价值均为nums元素, 对于整个数组nums, 最大化总价值是否为target
        int[] bag = new int[target + 1];
        for (int num : nums) {
            // 一维数组DP需从后向前, 避免状态转移时使用被修改后的前元素修改后元素
            for (int weight = target; weight >= num; weight--) {
                bag[weight] = Math.max(bag[weight], bag[weight - num] + num);
            }
        }
        return bag[target] == target;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
