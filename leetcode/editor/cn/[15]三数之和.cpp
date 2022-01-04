//给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重
//复的三元组。 
//
// 注意：答案中不可以包含重复的三元组。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-1,0,1,2,-1,-4]
//输出：[[-1,-1,2],[-1,0,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：nums = [0]
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 3000 
// -10⁵ <= nums[i] <= 10⁵ 
// 
// Related Topics 数组 双指针 排序 👍 3781 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    vector<vector<int>> threeSum(vector<int>& nums) {
        vector<vector<int>> container;
        if (nums.size() < 3) {
            return container;
        }
        sort(nums.begin(), nums.end());
        int length = (int) nums.size(), last2 = nums[length - 1] + nums[length - 2];
        for (int first = 0; first < length - 2; first++) {
            // 排除重复值
            if (first > 0 && nums[first - 1] == nums[first] || nums[first] + last2 < 0) {
                continue;
            }
            if (nums[first] + nums[first + 1] + nums[first + 2] > 0) {
                break;
            }
            int left = first + 1, right = length - 1, aim = - nums[first];
            while (left < right) {
                // 排除重复值
                if (left > first + 1 && nums[left - 1] == nums[left]
                    || nums[left] + nums[right] < aim) {
                    left++;
                }
                // 排除重复值
                else if (right < length - 1 && nums[right + 1] == nums[right]
                         || nums[left] + nums[right] > aim) {
                    right--;
                }
                else {
                    container.push_back(vector<int> {nums[first], nums[left], nums[right]});
                    left++;
                    right--;
                }
            }
        }

        return container;
    }
};

//leetcode submit region end(Prohibit modification and deletion)
