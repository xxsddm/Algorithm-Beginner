//给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c +
// d 的值与 target 相等？找出所有满足条件且不重复的四元组。 
//
// 注意：答案中不可以包含重复的四元组。 
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
//输入：nums = [], target = 0
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 200 
// -109 <= nums[i] <= 109 
// -109 <= target <= 109 
// 
// Related Topics 数组 双指针 排序 
// 👍 898 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> container = new ArrayList<>();
        if (nums.length < 4) return container;
        Arrays.sort(nums);
        int right2 = nums[nums.length - 1] + nums[nums.length - 2];
        int right3 = right2 + nums[nums.length - 3];
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            if (target > nums[i] + right3) continue;
            if (target < nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3]) break;
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (nums[j] == nums[j - 1] && j > i + 1) continue;
                int aim = target - nums[i] - nums[j];
                if (aim > right2) continue;
                if (aim < nums[j + 1] + nums[j + 2]) break;
                int left = j + 1, right = nums.length - 1;
                while (left < right) {
                    if (nums[left] + nums[right] > aim) {
                        while (left < right && nums[right] == nums[right - 1]) right--;
                        right--;
                    }
                    else if (nums[left] + nums[right] < aim) {
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        left++;
                    }
                    else{
                        container.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;
                        left++;
                        right--;
                    }
                }
            }
        }
        return container;
    }
}

//leetcode submit region end(Prohibit modification and deletion)


class Solution {
    List<List<Integer>> container;
    List<Integer> sublist;
    int[] nums;
    int target;

    public List<List<Integer>> fourSum(int[] nums, int target) {        // k数之和, 优化后还是慢
        container = new ArrayList<>();
        if (nums.length < 4) {
            return container;
        }
        sublist = new ArrayList<>(4);
        this.target = target;
        this.nums = nums;
        Arrays.sort(nums);
        backtrack(0, 0);
        return container;
    }

    private void backtrack(int start, int cumsum) {
        if (sublist.size() == 4) {
            if (cumsum == target) {
                container.add(new ArrayList<>(sublist));
            }
            return;
        }

        for (int idx = start; idx < nums.length + sublist.size() - 3; idx++) {
            int temp = cumsum + nums[idx];
            if (idx > start && nums[idx] == nums[idx - 1]
                    || temp + subsum(nums.length - 3 + sublist.size(), nums.length - 1) < target) {
                continue;
            }
            if (temp + subsum(idx, idx + 2 - sublist.size()) > target) {
                break;
            }
            sublist.add(nums[idx]);
            backtrack(idx + 1, temp);
            sublist.remove(sublist.size() - 1);
        }
    }

    private int subsum(int start, int end) {
        int cumsum = 0;
        for (int i = start; i <= end; i++) {
            cumsum += nums[i];
        }
        return cumsum;
    }
}
