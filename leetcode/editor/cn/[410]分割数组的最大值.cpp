//给定一个非负整数数组 nums 和一个整数 m ，你需要将这个数组分成 m 个非空的连续子数组。 
//
// 设计一个算法使得这 m 个子数组各自和的最大值最小。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [7,2,5,10,8], m = 2
//输出：18
//解释：
//一共有四种方法将 nums 分割为 2 个子数组。 其中最好的方式是将其分为 [7,2,5] 和 [10,8] 。
//因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3,4,5], m = 2
//输出：9
// 
//
// 示例 3： 
//
// 
//输入：nums = [1,4,4], m = 3
//输出：4
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 1000 
// 0 <= nums[i] <= 10⁶ 
// 1 <= m <= min(50, nums.length) 
// 
// Related Topics 贪心 数组 二分查找 动态规划 👍 586 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int splitArray(vector<int>& nums, int m) {  // 二分
        int left = 0, right = 0;
        for (int &num: nums) {
            left = max(left, num);
            right += num;
        }
        if (m == (int) nums.size()) {
            return left;
        }
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (check(mid, m, nums)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    bool check(int limit, int m, vector<int> &nums) {
        int count = 1, cumsum = 0;
        for (int &num: nums) {
            if (num + cumsum > limit) {
                cumsum = 0;
                if (++count > m) {
                    return false;
                }
            }
            cumsum += num;
        }
        return true;
    }
};

//leetcode submit region end(Prohibit modification and deletion)
