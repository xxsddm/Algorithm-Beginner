//有 n 个气球，编号为0 到 n - 1，每个气球上都标有一个数字，这些数字存在数组 nums 中。 
//
// 现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得 nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。 这里的 i -
// 1 和 i + 1 代表和 i 相邻的两个气球的序号。如果 i - 1或 i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球。 
//
// 求所能获得硬币的最大数量。 
//
// 
//示例 1：
//
// 
//输入：nums = [3,1,5,8]
//输出：167
//解释：
//nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
//coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167 
//
// 示例 2： 
//
// 
//输入：nums = [1,5]
//输出：10
// 
//
// 
//
// 提示： 
//
// 
// n == nums.length 
// 1 <= n <= 500 
// 0 <= nums[i] <= 100 
// 
// Related Topics 数组 动态规划 👍 796 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int maxCoins(vector<int>& nums) {
        int length = (int) nums.size();
        vector<vector<int>> dp(length + 2, vector<int>(length + 2));
        // 开区间(left,right)中戳idx(闭区间需要考虑长度小于3情况)
        // 需要左区间和右区间先确定,故right从左往右(左区间),left从右往左(右区间)
        for (int right = 2; right <= length + 1; right++) {
            for (int left = right - 2; left >= 0; left--) {
                for (int idx = left + 1; idx < right; idx++) {   // 先考虑左右区间,最后戳idx
                    int temp = nums[idx - 1]
                               * (left - 1 >= 0 ? nums[left - 1] : 1)
                               * (right - 1 < length ? nums[right - 1] : 1);
                    dp[left][right] = max(dp[left][right], temp
                                + dp[left][idx] + dp[idx][right]);  // 需要左区间和右区间先确定
                }
            }
        }
        return dp[0][length + 1];
    }
};

//leetcode submit region end(Prohibit modification and deletion)
