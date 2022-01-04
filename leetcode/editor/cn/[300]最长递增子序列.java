//给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。 
//
// 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序
//列。 
// 
//
// 示例 1： 
//
// 
//输入：nums = [10,9,2,5,3,7,101,18]
//输出：4
//解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,1,0,3,2,3]
//输出：4
// 
//
// 示例 3： 
//
// 
//输入：nums = [7,7,7,7,7,7,7]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 2500 
// -104 <= nums[i] <= 104 
// 
//
// 
//
// 进阶： 
//
// 
// 你可以设计时间复杂度为 O(n2) 的解决方案吗？ 
// 你能将算法的时间复杂度降低到 O(n log(n)) 吗? 
// 
// Related Topics 数组 二分查找 动态规划 
// 👍 1734 👎 0

import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] container = new int[nums.length];
        int len = 0;
        for (int num: nums){
            if (len == 0 || container[len - 1] < num) {
                container[len++] = num;
            }
            else{
//                替换原有位置元素, 不改变已有最大长度, 避免不断取max
//                且不影响后续更大元素进入数组
//                需要仔细想一下才能理解, 很妙
                int left = 0, right = len - 1;
                int loc = right;
                while (left <= right){
                    int mid = (left + right) >> 1;
                    if (container[mid] >= num) {
                        loc = mid;
                        right = mid - 1;
                    }
                    else left = mid + 1;
                }
                container[loc] = num;
            }
        }
        return len;
    }
}

//leetcode submit region end(Prohibit modification and deletion)


class Solution {
    public int lengthOfLIS(int[] nums) {        // DP(O(n^2)慢很多, 前面方法是O(n*logn))
        int[] dp = new int[nums.length];
        int ans = 1;
        Arrays.fill(dp, 1);
        for (int idx = 1; idx < nums.length; idx++) {
            for (int i = 0; i < idx; i++) {
                if (nums[idx] > nums[i]) {
                    dp[idx] = Math.max(dp[idx], dp[i] + 1);
                }
            }
            ans = Math.max(ans, dp[idx]);
        }
        return ans;
    }
}
