//给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。 
//
// 如果数组中不存在目标值 target，返回 [-1, -1]。 
//
// 进阶： 
//
// 
// 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？ 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [5,7,7,8,8,10], target = 8
//输出：[3,4] 
//
// 示例 2： 
//
// 
//输入：nums = [5,7,7,8,8,10], target = 6
//输出：[-1,-1] 
//
// 示例 3： 
//
// 
//输入：nums = [], target = 0
//输出：[-1,-1] 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 105 
// -109 <= nums[i] <= 109 
// nums 是一个非递减数组 
// -109 <= target <= 109 
// 
// Related Topics 数组 二分查找 
// 👍 1125 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int target;
    int[] nums;

    public int[] searchRange(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        this.target = target;
        this.nums = nums;
        int leftIdx = binarySearch(left, right, true);
        int rightIdx = binarySearch(leftIdx, right, false);
        if (leftIdx >= left && leftIdx <= right && rightIdx >= left && rightIdx <= right
                && nums[leftIdx] == nums[rightIdx]) {
            return new int[] {leftIdx, rightIdx};
        }
        return new int[] {- 1, - 1};
    }

    // lower: 是否寻找左边元素数量
    private int binarySearch(int left, int right, boolean lower) {  // 可分成两个函数实现相同功能
        int mid;
        while (left <= right) {
            mid = (left + right) >> 1;
            // 取等的时候, 若找 严格小于 target的元素数量, 应继续左移
            if (nums[mid] > target || lower && nums[mid] == target) {
                right = mid - 1;
            }
            // 取等的时候, 若找 最后一个不大于 target的元素索引, 应继续右移
            else {  // 即: else if (nums[mid] < target || !lower && nums[mid] == target)
                left = mid + 1;
            }
        }
        return lower ? left : right;
    }
}

//leetcode submit region end(Prohibit modification and deletion)


class Solution {
    public int[] searchRange(int[] nums, int target) {
        int left = 0, right = nums.length - 1, mid;
        while (left <= right){
            mid = (left + right) >> 1;
            if (nums[mid] > target) {
                right = mid - 1;
            }
            else if (nums[mid] < target) {
                left = mid + 1;
            }
            else {
                left = mid;
                right = mid;
                while (left - 1 >= 0 && nums[left - 1] == target) {
                    left--;
                }
                while (right + 1 < nums.length && nums[right + 1] == target) {
                    right++;
                }
                return new int[]{left, right};
            }
        }
        return new int[]{-1, -1};
    }
}
