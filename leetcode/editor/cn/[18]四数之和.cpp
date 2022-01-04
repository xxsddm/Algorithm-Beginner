//给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[
//b], nums[c], nums[d]] ： 
//
// 
// 0 <= a, b, c, d < n 
// a、b、c 和 d 互不相同 
// nums[a] + nums[b] + nums[c] + nums[d] == target 
// 
//
// 你可以按 任意顺序 返回答案 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,0,-1,0,-2,2], target = 0
//输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [2,2,2,2,2], target = 8
//输出：[[2,2,2,2]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 200 
// -10⁹ <= nums[i] <= 10⁹ 
// -10⁹ <= target <= 10⁹ 
// 
// Related Topics 数组 双指针 排序 👍 957 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    vector<vector<int>> fourSum(vector<int>& nums, int target) {    // 和15三数之和思路一致
        vector<vector<int>> container;
        if (nums.size() < 4) {
            return container;
        }
        sort(nums.begin(), nums.end());
        int length = (int) nums.size();
        // 测试用例太恶心,int过不了,unsigned int或许可以,但考虑易读性直接选择long
        long last3 = (long) nums[length - 1] + nums[length - 2] + nums[length - 3];
        long last2 = (long) nums[length - 1] + nums[length - 2];
        for (int first = 0; first < length - 3; first++) {
            if (first > 0 && nums[first - 1] == nums[first]
                || nums[first] + last3 < target) {
                continue;
            }
            if ((long) nums[first] + nums[first + 1] + nums[first + 2] + nums[first + 3] > target) {
                break;
            }
            for (int second = first + 1; second < length - 2; second++) {
                if (second > first + 1 && nums[second - 1] == nums[second]
                    || nums[first] + nums[second] + last2 < target) {
                    continue;
                }
                if ((long) nums[first] + nums[second] + nums[second + 1] + nums[second + 2] > target) {
                    break;
                }
                int left = second + 1, right = length - 1, aim = target - nums[first] - nums[second];
                while (left < right) {
                    if (left > second + 1 && nums[left - 1] == nums[left]
                        || (long) nums[left] + nums[right] < aim) {
                        left++;
                    }
                    else if (right < length - 1 && nums[right + 1] == nums[right]
                             || (long) nums[left] + nums[right] > aim) {
                        right--;
                    }
                    else {
                        container.push_back(vector<int> {nums[first], nums[second], nums[left], nums[right]});
                        left++;
                        right--;
                    }
                }
            }

        }

        return container;
    }
};

//leetcode submit region end(Prohibit modification and deletion)
