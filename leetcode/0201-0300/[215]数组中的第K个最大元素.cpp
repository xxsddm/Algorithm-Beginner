//给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。 
//
// 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。 
//
// 
//
// 示例 1: 
//
// 
//输入: [3,2,1,5,6,4] 和 k = 2
//输出: 5
// 
//
// 示例 2: 
//
// 
//输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
//输出: 4 
//
// 
//
// 提示： 
//
// 
// 1 <= k <= nums.length <= 10⁴ 
// -10⁴ <= nums[i] <= 10⁴ 
// 
// Related Topics 数组 分治 快速选择 排序 堆（优先队列） 👍 1316 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int findKthLargest(vector<int>& nums, int k) {  // 快排
        return find(nums, 0, (int) nums.size() - 1, (int) nums.size() - k);
    }

    int find(vector<int>& nums, int start, int end, int target) {
        if (start == end) {
            return nums[start];
        }
        int left = start, right = end + 1, idx = start + 1, pivot = nums[start];
        while (idx < right) {
            if (nums[idx] < pivot) {
                exchange(nums, idx++, ++left);
            }
            else if (nums[idx] > pivot) {
                exchange(nums, idx, --right);
            }
            else {
                idx++;
            }
        }
        exchange(nums, start, left--);
        if (left >= target) {
            return find(nums, start, left, target);
        }
        if (right <= target) {
            return find(nums, right, end, target);
        }
        return pivot;
    }

    static void exchange(vector<int>& nums, int idx1, int idx2) {
        int temp = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = temp;
    }
};

//leetcode submit region end(Prohibit modification and deletion)
