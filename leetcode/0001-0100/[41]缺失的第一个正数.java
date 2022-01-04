//给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。 
//请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,0]
//输出：3
// 
//
// 示例 2： 
//
// 
//输入：nums = [3,4,-1,1]
//输出：2
// 
//
// 示例 3： 
//
// 
//输入：nums = [7,8,9,11,12]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 5 * 10⁵ 
// -2³¹ <= nums[i] <= 2³¹ - 1 
// 
// Related Topics 数组 哈希表 👍 1187 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int firstMissingPositive(int[] nums) {
        int limit = nums.length, ans = limit + 1, idx = 0, numsIdx;
        // 标记无用数字(数组长度确定时, 缺失正数范围也确定为1~nums.length+1)
        for (int i = 0; i < limit; i++) {
            if (nums[i] >= limit + 1 || nums[i] <= 0) {
                nums[i] = limit + 1;
            }
        }

        // 用相应索引对应数字表示该数字是否出现(出现的数字应在 数字-1 的索引位置)
        while (idx < limit) {
            numsIdx = nums[idx];
            if (numsIdx == limit + 1 || idx == numsIdx - 1) {
                idx++;
            }
            else if (numsIdx != nums[numsIdx - 1]) {
                nums[idx] = nums[numsIdx - 1];
                nums[numsIdx - 1] = numsIdx;
            }
            else {
                nums[idx++] = limit + 1;
            }
        }

        for (int i = 0; i < limit; i++) {
            if (nums[i] == limit + 1) {
                ans = i + 1;
                break;
            }
        }

        return ans;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
