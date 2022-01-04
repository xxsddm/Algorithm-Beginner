//实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列（即，组合出下一个更大的整数）。 
//
// 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。 
//
// 必须 原地 修改，只允许使用额外常数空间。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：[1,3,2]
// 
//
// 示例 2： 
//
// 
//输入：nums = [3,2,1]
//输出：[1,2,3]
// 
//
// 示例 3： 
//
// 
//输入：nums = [1,1,5]
//输出：[1,5,1]
// 
//
// 示例 4： 
//
// 
//输入：nums = [1]
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 100 
// 0 <= nums[i] <= 100 
// 
// Related Topics 数组 双指针 👍 1336 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public void nextPermutation(int[] nums) {   // 二分
        // 从右往左应单调非减,找到第一个递减的点(小于右侧值)changeIdx
        int changeIdx = nums.length - 2;
        while (changeIdx >= 0) {
            if (nums[changeIdx] >= nums[changeIdx + 1]) {
                changeIdx--;
            }
            else {
                break;
            }
        }
        // 若从右往左单调非减,反转数组
        if (changeIdx < 0) {
            reverse(nums, 0);
            return;
        }
        // 查找[changeIdx+1,nums.length-1]中大于nums[changeIdx]的最小值索引(有重复值则返回最右侧索引便于最后reverse)
        boolean needReverse = false;
        int targetIdx = binarySearch(nums, changeIdx + 1, nums[changeIdx]);
//        if (nums[targetIdx] <= nums[changeIdx + 1]) {
//            needReverse = true;
//        }
        exchange(nums, changeIdx, targetIdx);
//        if (needReverse) {
//            reverse(nums, changeIdx + 1);
//        }
        // 上面的反转条件一定成立,直接反转[changeIdx+1,nums.length-1]即可
        reverse(nums, changeIdx + 1);
    }

    private void exchange(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    // 返回nums[start,nums.length]中严格大于target的最小值的索引
    // 若有多个相同的大于target的最小值,返回最右侧的索引
    private int binarySearch(int[] nums, int start, int target) {
        int left = start, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (nums[mid] > target) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        return right;
    }

    private void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            exchange(nums, left++, right--);
        }
    }
}

//leetcode submit region end(Prohibit modification and deletion)
