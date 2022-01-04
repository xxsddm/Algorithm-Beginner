//给定一个整数数组，返回所有数对之间的第 k 个最小距离。一对 (A, B) 的距离被定义为 A 和 B 之间的绝对差值。 
//
// 示例 1: 
//
// 
//输入：
//nums = [1,3,1]
//k = 1
//输出：0 
//解释：
//所有数对如下：
//(1,3) -> 2
//(1,1) -> 0
//(3,1) -> 2
//因此第 1 个最小距离的数对是 (1,1)，它们之间的距离为 0。
// 
//
// 提示: 
//
// 
// 2 <= len(nums) <= 10000. 
// 0 <= nums[i] < 1000000. 
// 1 <= k <= len(nums) * (len(nums) - 1) / 2. 
// 
// Related Topics 数组 双指针 二分查找 排序 👍 209 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int smallestDistancePair(vector<int>& nums, int k) {    // 二分嵌套
        sort(nums.begin(), nums.end());
        int left = INT32_MAX, right = nums[nums.size() - 1] - nums[0];
        for (int i = 1; i < nums.size(); i++) {
            left = min(left, nums[i] - nums[i - 1]);
        }
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (check(mid, k, nums)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    bool check(int target, int k, vector<int>& nums) {  // nums中距离小于等于target的距离对是否达到k个
        int right = bisect(nums[0] + target, 1, nums);
        int count = right - 1, limit = (int) nums.size() - 1;
        // 枚举较小数字,查找较大数字
        for (int left = 1; left < limit; left++) {
            if (nums[right - 1] < nums[left] + target) {
                right = bisect(nums[left] + target, right, nums);
            }
            count += right - left - 1;
            if (count >= k) {
                return true;
            }
        }
        return count >= k;
    }

    // 计算nums中大于target的最左侧元素索引
    int bisect(int target, int start, vector<int>& nums) {
        int left = start, right = (int) nums.size() - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (target >= nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
};

//leetcode submit region end(Prohibit modification and deletion)
