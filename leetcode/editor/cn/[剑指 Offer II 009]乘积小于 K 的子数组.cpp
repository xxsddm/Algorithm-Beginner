//给定一个正整数数组 nums和整数 k ，请找出该数组内乘积小于 k 的连续的子数组的个数。 
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [10,5,2,6], k = 100
//输出: 8
//解释: 8 个乘积小于 100 的子数组分别为: [10], [5], [2], [6], [10,5], [5,2], [2,6], [5,2,6]。
//需要注意的是 [10,5,2] 并不是乘积小于100的子数组。
// 
//
// 示例 2: 
//
// 
//输入: nums = [1,2,3], k = 0
//输出: 0 
//
// 
//
// 提示: 
//
// 
// 1 <= nums.length <= 3 * 10⁴ 
// 1 <= nums[i] <= 1000 
// 0 <= k <= 10⁶ 
// 
//
// 
//
// 注意：本题与主站 713 题相同：https://leetcode-cn.com/problems/subarray-product-less-than-
//k/ 
// Related Topics 数组 滑动窗口 👍 9 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int numSubarrayProductLessThanK(vector<int>& nums, int k) {
        if (k <= 1) {
            return 0;
        }
        int left = 0, right = 0, cum = 1, ans = 0;
        while (right < nums.size()) {
            cum *= nums[right++];
            while (cum >= k && left < right) {
                cum /= nums[left++];
            }
            // 此时[left,right-1]被包含在滑动窗口, 含新加入的right-1的所有子序列均非重复
            ans += right - left;
        }
        return ans;
    }
};

//leetcode submit region end(Prohibit modification and deletion)
