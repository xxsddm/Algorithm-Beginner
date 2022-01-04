//给你一个整数数组 nums 和一个整数 k ，请你统计并返回该数组中和为 k 的连续子数组的个数。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,1], k = 2
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3], k = 3
//输出：2
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 2 * 10⁴ 
// -1000 <= nums[i] <= 1000 
// -10⁷ <= k <= 10⁷ 
// 
// Related Topics 数组 哈希表 前缀和 👍 1122 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int subarraySum(vector<int>& nums, int k) { // hash
        int cumsum = 0, ans = 0;
        unordered_map<int, int> counter;
        counter[0] = 1;
        for (auto& num: nums) {
            cumsum += num;
            // 匹配的前缀数量
            if (counter.find(cumsum - k) != counter.end()) {
                ans += counter[cumsum - k];
            }
            // 匹配完后记入当前cumsum
            counter[cumsum]++;
        }
        return ans;
    }
};

//leetcode submit region end(Prohibit modification and deletion)
