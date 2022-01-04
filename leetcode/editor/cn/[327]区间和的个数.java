//给你一个整数数组 nums 以及两个整数 lower 和 upper 。求数组中，值位于范围 [lower, upper] （包含 lower 和 
//upper）之内的 区间和的个数 。 
//
// 区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。 
//
// 
//示例 1：
//
// 
//输入：nums = [-2,5,-1], lower = -2, upper = 2
//输出：3
//解释：存在三个区间：[0,0]、[2,2] 和 [0,2] ，对应的区间和分别是：-2 、-1 、2 。
// 
//
// 示例 2： 
//
// 
//输入：nums = [0], lower = 0, upper = 0
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10⁵ 
// -2³¹ <= nums[i] <= 2³¹ - 1 
// -10⁵ <= lower <= upper <= 10⁵ 
// 题目数据保证答案是一个 32 位 的整数 
// 
// Related Topics 树状数组 线段树 数组 二分查找 分治 有序集合 归并排序 👍 366 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int count = 0;
    long lower, upper;
    long[] aux, container;

    // 右侧前缀和与左侧前缀和之差满足条件即可
    // 若左右部分前缀和已排序,则便于计算数量
    // 因此可利用归并
    public int countRangeSum(int[] nums, int lower, int upper) {
        this.lower = lower;
        this.upper = upper;
        int length = nums.length;
        long cumsum = 0;
        // container保存前缀和(container[0]=0必需,保证container后续元素作为可行的前缀和)
        container = new long[length + 1];
        aux = new long[length + 1];
        for (int i = 1; i < length + 1; i++) {
            cumsum += nums[i - 1];
            container[i] = cumsum;
        }
        sort(0, length);
        return count;
    }

    private void sort(int start, int end) {
        if (start == end) {
            return;
        }
        int mid = start + ((end - start) >> 1);
        sort(start, mid);
        sort(mid + 1, end);
        count(start, mid, end);
        if (start == 0 && end == container.length - 1) {
            return;
        }
        merge(start, mid, end);
    }

    private void count(int start, int mid, int end) {
        // 对每个右侧[mid+1,end]元素,选择相应左侧范围(也可对每个左侧元素选择相应右侧范围,见cpp)
        int left = start, right = start;
        for (int i = mid + 1; i <= end; i++) {
            long temp = container[i];
            if (temp > container[mid] + upper) {
                break;
            }
            // 可二分但没必要
            while (left <= mid && temp > container[left] + upper) {
                left++;
            }
            right = Math.max(right, left);
            while (right <= mid && temp >= container[right] + lower) {
                right++;
            }
            // [left,right-1]符合条件
            count += right - left;
        }
    }

    private void merge(int start, int mid, int end) {
        int left = start, right = mid + 1, idx = start;
        for (int i = start; i <= end; i++) {
            aux[i] = container[i];
        }
        while (left <= mid || right <= end) {
            if (left == mid + 1) {
                while (idx <= end) {
                    container[idx++] = aux[right++];
                }
                return;
            }
            if (right == end + 1) {
                while (idx <= end) {
                    container[idx++] = aux[left++];
                }
                return;
            }
            if (aux[left] <= aux[right]) {
                container[idx++] = aux[left++];
            }
            else {
                container[idx++] = aux[right++];
            }
        }
    }
}

//leetcode submit region end(Prohibit modification and deletion)
