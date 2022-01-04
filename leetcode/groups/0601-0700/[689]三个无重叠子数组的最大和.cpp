//给你一个整数数组 nums 和一个整数 k ，找出三个长度为 k 、互不重叠、且 3 * k 项的和最大的子数组，并返回这三个子数组。 
//
// 以下标的数组形式返回结果，数组中的每一项分别指示每个子数组的起始位置（下标从 0 开始）。如果有多个结果，返回字典序最小的一个。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,1,2,6,7,5,1], k = 2
//输出：[0,3,5]
//解释：子数组 [1, 2], [2, 6], [7, 5] 对应的起始下标为 [0, 3, 5]。
//也可以取 [2, 1], 但是结果 [1, 3, 5] 在字典序上更大。
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,1,2,1,2,1,2,1], k = 2
//输出：[0,2,4]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 2 * 10⁴ 
// 1 <= nums[i] < 2¹⁶ 
// 1 <= k <= floor(nums.length / 3) 
// 
// Related Topics 数组 动态规划 👍 216 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    vector<int> maxSumOfThreeSubarrays(vector<int>& nums, int k) {
        vector<int> ans = {0, k, 2 * k};
        int sum[3], length = (int) nums.size();
        for (int i = 0; i < 3; i++) {
            sum[i] = 0;
            for (int j = i * k; j < (i + 1) * k; j++) {
                sum[i] += nums[j];
            }
        }
        int maxsum[3] = {sum[0], sum[0] + sum[1], sum[0] + sum[1] + sum[2]};
        int maxIdx1 = 0, maxIdx[3] = {0, k, 2 * k};
        for (int i = 3 * k; i < length; i++) {
            for (int j = 0; j < 3; j++) {
                sum[j] -= nums[i - (3 - j) * k];
                sum[j] += nums[i - (2 - j) * k];
            }
            if (sum[0] > maxsum[0]) {   // 增大后可能与maxsum[1]中sum[1]有重叠
                maxsum[0] = sum[0];
                maxIdx1 = i - 3 * k + 1;
            }
            if (sum[1] + maxsum[0] > maxsum[1]) {   // 倒数第2个数组及前面数组的最大和一定与sum[2]无重复部分
                maxsum[1] = sum[1] + maxsum[0];
                maxIdx[0] = maxIdx1;
                maxIdx[1] = i - 2 * k + 1;
            }
            if (sum[2] + maxsum[1] > maxsum[2]) {
                maxsum[2] = sum[2] + maxsum[1];
                maxIdx[2] = i - k + 1;
                for (int j = 0; j < 3; j++) {
                    ans[j] = maxIdx[j];
                }
            }
        }
        return ans;
    }
};

//leetcode submit region end(Prohibit modification and deletion)
