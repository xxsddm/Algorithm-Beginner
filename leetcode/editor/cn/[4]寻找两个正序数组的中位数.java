//给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums1 = [1,3], nums2 = [2]
//输出：2.00000
//解释：合并数组 = [1,2,3] ，中位数 2
// 
//
// 示例 2： 
//
// 
//输入：nums1 = [1,2], nums2 = [3,4]
//输出：2.50000
//解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
// 
//
// 示例 3： 
//
// 
//输入：nums1 = [0,0], nums2 = [0,0]
//输出：0.00000
// 
//
// 示例 4： 
//
// 
//输入：nums1 = [], nums2 = [1]
//输出：1.00000
// 
//
// 示例 5： 
//
// 
//输入：nums1 = [2], nums2 = []
//输出：2.00000
// 
//
// 
//
// 提示： 
//
// 
// nums1.length == m 
// nums2.length == n 
// 0 <= m <= 1000 
// 0 <= n <= 1000 
// 1 <= m + n <= 2000 
// -10⁶ <= nums1[i], nums2[i] <= 10⁶ 
// 
//
// 
//
// 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？ 
// Related Topics 数组 二分查找 分治 👍 4376 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {    // 双指针
        int length = nums1.length + nums2.length;
        if ((length & 1) != 0) {
            return findKth(nums1, nums2, (length >> 1) + 1);
        }
        return (findKth(nums1, nums2, length >> 1)
                + findKth(nums1, nums2, (length >> 1) + 1)) / 2D;

    }

    private int findKth(int[] nums1, int[] nums2, int k) {
        // nums1[idx1,length1-1]和nums2[idx2,length2-1]中查找第k小元素
        int idx1 = 0, idx2 = 0;
        int length1 = nums1.length, length2 = nums2.length;
        while (true) {
            // 三种情况可直接得到结果
            if (idx1 == length1) {
                return nums2[k + idx2 - 1];
            }
            if (idx2 == length2) {
                return nums1[k + idx1 - 1];
            }
            if (k == 1) {
                return Math.min(nums1[idx1], nums2[idx2]);
            }
            // 若更新该idx,则下轮idx为nextIdx,当前待比较元素索引为nextIdx-1
            int candidate1 = idx1 + (k >> 1), candidate2 = idx2 + (k >> 1);
            int nextIdx1 = Math.min(candidate1, length1), pivot1 = nums1[nextIdx1 - 1];
            int nextIdx2 = Math.min(candidate2, length2), pivot2 = nums2[nextIdx2 - 1];
            // candidat不大于length且k为偶数时,若pivot相等则可提前结束
            if (pivot1 == pivot2 && candidate1 <= length1 && candidate2 <= length2 && (k & 1) == 0) {
                return pivot1;
            }
            // 较小部分的数组,指针移动部分[idx,nextIdx-1]被剔除
            // 在另一端无法覆盖k/2元素,不可能作为第k元素,k也相应减少
            if (pivot1 < pivot2) {
                k -= nextIdx1 - idx1;
                idx1 = nextIdx1;
            }
            else {
                k -= nextIdx2 - idx2;
                idx2 = nextIdx2;
            }
        }
    }
}

//leetcode submit region end(Prohibit modification and deletion)
