//在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。 
//
// 
//
// 示例 1: 
//
// 输入: [7,5,6,4]
//输出: 5 
//
// 
//
// 限制： 
//
// 0 <= 数组长度 <= 50000 
// Related Topics 树状数组 线段树 数组 二分查找 分治 有序集合 归并排序 👍 516 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int count = 0;

    public int reversePairs(int[] nums) {
        sort(nums, 0, nums.length - 1, new int[nums.length]);
        return count;
    }

    private void sort(int[] nums, int start, int end, int[] aux) {
        if (start >= end) {
            return;
        }
        int mid = (start + end) >> 1;
        sort(nums, start, mid, aux);
        sort(nums, mid + 1, end, aux);
        for (int i = start; i <= end; i++) {
            aux[i] = nums[i];
        }
        int left = start, right = mid + 1, idx = start;
        while (idx <= end) {
            if (left > mid) {
                while (idx <= end) {
                    nums[idx++] = aux[right++];
                }
                break;
            }
            else if (right > end) {
                while (idx <= end) {
                    nums[idx++] = aux[left++];
                }
                break;
            }
            if (aux[left] <= aux[right]) {
                nums[idx++] = aux[left++];
            }
            else {
                nums[idx++] = aux[right++];
                // 填入右侧数字时, 剩余左侧数字均可以与被填入右侧数字组成逆序对
                count += mid - left + 1;
            }
        }
    }
}

//leetcode submit region end(Prohibit modification and deletion)
