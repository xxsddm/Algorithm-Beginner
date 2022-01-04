//给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。 
//
// 
//
// 示例： 
//
// 输入：
//A: [1,2,3,2,1]
//B: [3,2,1,4,7]
//输出：3
//解释：
//长度最长的公共子数组是 [3, 2, 1] 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= len(A), len(B) <= 1000 
// 0 <= A[i], B[i] < 100 
// 
// Related Topics 数组 二分查找 动态规划 滑动窗口 哈希函数 滚动哈希 👍 521 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findLength(int[] nums1, int[] nums2) {       // DP(空间优化, 由原版观察可得)
        int ans = 0;
        if (nums1.length > nums2.length) {      // 选择更短的数组长度, 进一步优化
            int[] temp = nums2;
            nums2 = nums1;
            nums1 = temp;
        }
        int[] dp = new int[nums1.length + 1];
        for (int num2 : nums2) {
            // 必须从后向前遍历(原版更新的是下一轮num2)
            for (int idx1 = nums1.length - 1; idx1 >= 0; idx1--) {
                if (nums1[idx1] == num2) {
                    dp[idx1 + 1] = dp[idx1] + 1;
                    ans = Math.max(ans, dp[idx1 + 1]);
                }
                else {
                    dp[idx1 + 1] = 0;
                }
            }
        }
        return ans;
    }
}

//leetcode submit region end(Prohibit modification and deletion)


class Solution {
    public int findLength(int[] nums1, int[] nums2) {       // DP
        int ans = 0;
        // idx1+1, idx2+1 -> 该索引(idx1,idx2)起(含)向前计算, 最长重复子数组长度
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
        for (int idx1 = 0; idx1 < nums1.length; idx1++) {
            for (int idx2 = 0; idx2 < nums2.length; idx2++) {
                dp[idx1 + 1][idx2 + 1] = (nums1[idx1] == nums2[idx2] ? dp[idx1][idx2] + 1 : 0);
                ans = Math.max(ans, dp[idx1 + 1][idx2 + 1]);
            }
        }
        return ans;
    }
}


class Solution {
    public int findLength(int[] nums1, int[] nums2) {       // DP(略省空间)
        int ans = 0;
        // idx1, idx2 -> 该索引(idx1,idx2)起(含)向前计算, 最长重复子数组长度
        int[][] dp = new int[nums1.length][nums2.length];
        for (int i = 0; i < nums1.length; i++) {
            dp[i][0] = nums1[i] == nums2[0] ? 1 : 0;
            ans = Math.max(ans, dp[i][0]);
        }
        for (int i = 0; i < nums2.length; i++) {
            dp[0][i] = nums1[0] == nums2[i] ? 1 : 0;
            ans = Math.max(ans, dp[0][i]);
        }
        for (int idx1 = 1; idx1 < nums1.length; idx1++) {
            for (int idx2 = 1; idx2 < nums2.length; idx2++) {
                dp[idx1][idx2] = (nums1[idx1] == nums2[idx2] ? dp[idx1 - 1][idx2 - 1] + 1 : 0);
                ans = Math.max(ans, dp[idx1][idx2]);
            }
        }
        return ans;
    }
}
