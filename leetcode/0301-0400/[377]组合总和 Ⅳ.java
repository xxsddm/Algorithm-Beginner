//给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。请你从 nums 中找出并返回总和为 target 的元素组合的个数。 
//
// 题目数据保证答案符合 32 位整数范围。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3], target = 4
//输出：7
//解释：
//所有可能的组合为：
//(1, 1, 1, 1)
//(1, 1, 2)
//(1, 2, 1)
//(1, 3)
//(2, 1, 1)
//(2, 2)
//(3, 1)
//请注意，顺序不同的序列被视作不同的组合。
// 
//
// 示例 2： 
//
// 
//输入：nums = [9], target = 3
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 200 
// 1 <= nums[i] <= 1000 
// nums 中的所有元素 互不相同 
// 1 <= target <= 1000 
// 
//
// 
//
// 进阶：如果给定的数组中含有负数会发生什么？问题会产生何种变化？如果允许负数出现，需要向题目中添加哪些限制条件？ 
// Related Topics 数组 动态规划 👍 478 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {        // 类比518
    public int combinationSum4(int[] nums, int target) {
        int[] bag = new int[target + 1];    // 总数 -> 组合数
        bag[0] = 1;
        // 不同的加入顺序算不同的组合, 需要对于每一个总数, 遍历可行的所有数字, 所以外循环为总数
        for (int total = 0; total < target + 1; total++) {
            for (int num: nums) {
                if (total - num >= 0) {
                    bag[total] += bag[total - num];
                }
            }
        }
        return bag[target];
    }
}

//leetcode submit region end(Prohibit modification and deletion)
