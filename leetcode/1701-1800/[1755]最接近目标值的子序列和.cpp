//给你一个整数数组 nums 和一个目标值 goal 。 
//
// 你需要从 nums 中选出一个子序列，使子序列元素总和最接近 goal 。也就是说，如果子序列元素和为 sum ，你需要 最小化绝对差 abs(sum -
// goal) 。 
//
// 返回 abs(sum - goal) 可能的 最小值 。 
//
// 注意，数组的子序列是通过移除原始数组中的某些元素（可能全部或无）而形成的数组。 
//
// 
//
// 示例 1： 
//
// 输入：nums = [5,-7,3,5], goal = 6
//输出：0
//解释：选择整个数组作为选出的子序列，元素和为 6 。
//子序列和与目标值相等，所以绝对差为 0 。
// 
//
// 示例 2： 
//
// 输入：nums = [7,-9,15,-2], goal = -5
//输出：1
//解释：选出子序列 [7,-9,-2] ，元素和为 -4 。
//绝对差为 abs(-4 - (-5)) = abs(1) = 1 ，是可能的最小值。
// 
//
// 示例 3： 
//
// 输入：nums = [1,2,3], goal = -7
//输出：7
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 40 
// -10⁷ <= nums[i] <= 10⁷ 
// -10⁹ <= goal <= 10⁹ 
// 
// Related Topics 位运算 数组 双指针 动态规划 状态压缩 👍 47 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int minAbsDifference(vector<int>& nums, int goal) { // 分治+回溯+二分
        int length = (int) nums.size(), ans = INT32_MAX;
        vector<int> leftPart(length >> 1);
        vector<int> rightPart(length - leftPart.size());
        vector<int> sumsLeft;
        vector<int> sumsRight;
        for (int i = 0; i < leftPart.size(); i++) {
            leftPart[i] = nums[i];
        }
        for (int i = 0; i < rightPart.size(); i++) {
            rightPart[i] = nums[leftPart.size() + i];
        }
        sort(leftPart.begin(), leftPart.end());
        sort(rightPart.begin(), rightPart.end());
        backtrack(sumsLeft, leftPart, 0, 0, 0);
        backtrack(sumsRight, rightPart, 0, 0, 0);
        sort(sumsRight.begin(), sumsRight.end());
        for (int sumLeft: sumsLeft) {
            // idx为sumsRight中小于goal-sumLeft的元素数量(即temp>=goal-sumLeft)
            int idx = bisect(sumsRight, goal - sumLeft);
            int temp = abs(sumLeft + sumsRight[idx] - goal);
            // 考虑temp<goal-sumLeft
            if (idx > 0) {
                temp = min(temp, abs(sumLeft + sumsRight[idx - 1] - goal));
            }
            if (temp < ans) {
                ans = temp;
                if (ans == 0) {
                    return 0;
                }
            }
        }
        return ans;
    }

    void backtrack(vector<int>& list, vector<int>& nums, int start, int used, int cumsum) {
        for (int idx = start, XOR = 1 << start; idx < nums.size(); idx++, XOR <<= 1) {
            if (idx > 0 && nums[idx - 1] == nums[idx] && (used & (XOR >> 1)) == 0) {
                continue;
            }
            backtrack(list, nums, idx + 1, used ^ XOR, cumsum + nums[idx]);
        }
        list.push_back(cumsum);     // 考虑本轮不加入元素,及idx=nums.size()
    }

    // 返回list中小于target的元素数量(全部小于target则考虑最大元素)
    int bisect(vector<int>& list, int target) {
        int left = 0, right = (int) list.size() - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (list[mid] > target) {
                right = mid - 1;
            }
            else if (list[mid] < target) {
                left = mid + 1;
            }
            else {
                return mid;
            }
        }
        return left == list.size() ? left - 1 : left;
    }
};

//leetcode submit region end(Prohibit modification and deletion)
