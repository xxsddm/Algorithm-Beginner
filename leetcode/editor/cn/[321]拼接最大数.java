//给定长度分别为 m 和 n 的两个数组，其元素由 0-9 构成，表示两个自然数各位上的数字。现在从这两个数组中选出 k (k <= m + n) 个数字拼接
//成一个新的数，要求从同一个数组中取出的数字保持其在原数组中的相对顺序。 
//
// 求满足该条件的最大数。结果返回一个表示该最大数的长度为 k 的数组。 
//
// 说明: 请尽可能地优化你算法的时间和空间复杂度。 
//
// 示例 1: 
//
// 输入:
//nums1 = [3, 4, 6, 5]
//nums2 = [9, 1, 2, 5, 8, 3]
//k = 5
//输出:
//[9, 8, 6, 5, 3] 
//
// 示例 2: 
//
// 输入:
//nums1 = [6, 7]
//nums2 = [6, 0, 4]
//k = 5
//输出:
//[6, 7, 6, 0, 4] 
//
// 示例 3: 
//
// 输入:
//nums1 = [3, 9]
//nums2 = [8, 9]
//k = 3
//输出:
//[9, 8, 9] 
// Related Topics 栈 贪心 单调栈 👍 421 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {   // 单调栈+排列组合
        int[] ans = null;
        // 枚举不同的子序列长度组合,归并且选择字典序最大的作为结果
        for (int length1 = Math.max(0, k - nums2.length); length1 <= nums1.length; length1++) {
            int length2 = k - length1;
            if (length2 < 0) {
                break;
            }
            int[] next = merge(build(nums1, length1), build(nums2, length2));
            if (lessThan(ans, next, 0, 0)) {
                ans = next;
            }
        }
        return ans;
    }

    // 生成长度为k的nums最大子序列
    private int[] build(int[] nums, int k) {    // 单调栈
        int length = nums.length, idx = 0;
        int[] ans = new int[k];
        if (k == 0) {
            return ans;
        }
        for (int i = 0; i < length; i++) {
            int num = nums[i];
            while (idx > 0 && ans[idx - 1] < num && k - idx < length - i) {
                idx--;
            }
            if (idx < k) {
                ans[idx++] = num;
            }
        }
        return ans;
    }

    private int[] merge(int[] nums1, int[] nums2) {
        int length1 = nums1.length, length2 = nums2.length;
        int idx = 0, idx1 = 0, idx2 = 0;
        int[] ans = new int[length1 + length2];
        while (idx < ans.length) {
            if (idx1 == length1) {
                for (int i = idx2; i < length2; i++) {
                    ans[idx++] = nums2[i];
                }
                break;
            }
            if (idx2 == length2) {
                for (int i = idx1; i < length1; i++) {
                    ans[idx++] = nums1[i];
                }
                break;
            }
            // 使用字典序较大的(若只比较第一个元素,则相同时会出错)
            if (lessThan(nums1, nums2, idx1, idx2)) {
                ans[idx++] = nums2[idx2++];
            }
            else {
                ans[idx++] = nums1[idx1++];
            }
        }
        return ans;
    }

    // 字典序大小对比,只需关注第一对不同值的大小,若nums1为nums2前缀子数组,则nums2更大
    private boolean lessThan(int[] nums1, int[] nums2, int start1, int start2) {
        if (nums1 == null) {
            return true;
        }
        int length1 = nums1.length, length2 = nums2.length;
        for (int i = start1, j = start2; i < length1 && j < length2; i++, j++) {
            if (nums1[i] > nums2[j]) {
                return false;
            }
            else if (nums1[i] < nums2[j]) {
                return true;
            }
        }
        return length2 - start2 >= length1 - start1;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
