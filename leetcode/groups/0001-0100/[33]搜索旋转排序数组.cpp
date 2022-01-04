//整数数组 nums 按升序排列，数组中的值 互不相同 。 
//
// 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[
//k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2
//,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。 
//
// 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [4,5,6,7,0,1,2], target = 0
//输出：4
// 
//
// 示例 2： 
//
// 
//输入：nums = [4,5,6,7,0,1,2], target = 3
//输出：-1 
//
// 示例 3： 
//
// 
//输入：nums = [1], target = 0
//输出：-1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 5000 
// -10^4 <= nums[i] <= 10^4 
// nums 中的每个值都 独一无二 
// 题目数据保证 nums 在预先未知的某个下标上进行了旋转 
// -10^4 <= target <= 10^4 
// 
//
// 
//
// 进阶：你可以设计一个时间复杂度为 O(log n) 的解决方案吗？ 
// Related Topics 数组 二分查找 👍 1589 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int search(vector<int>& nums, int target) { // 二分(二分+DFS见java)
        // left,right为当前锁定的target范围(不会在其他范围)
        int length = (int) nums.size(), left = 0, right = length - 1;
        while (left <= right) {
            if (nums[left] <= nums[right]) {
                return binarySearch(nums, left, right, target);
            }
            int mid = (left + right) >> 1;

            // 考虑可能在左半边的情况
            if (nums[left] <= nums[mid]) {
                if (target >= nums[left] && target <= nums[mid]) {
                    return binarySearch(nums, left, mid, target);
                }
            }
            else if (target >= nums[left] || target <= nums[mid]) {
                right = mid;
                continue;
            }

            // 左半边不可能, 考虑右半边
            left = mid + 1;
        }

        return -1;
    }

    int binarySearch(vector<int>& nums, int start, int end, int target) {
        if (start > end) {
            return -1;
        }
        int left = start, right = end;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (nums[mid] > target) {
                right = mid - 1;
            }
            else if (nums[mid] < target) {
                left = mid + 1;
            }
            else {
                return mid;
            }
        }
        return -1;
    }
};

//leetcode submit region end(Prohibit modification and deletion)
