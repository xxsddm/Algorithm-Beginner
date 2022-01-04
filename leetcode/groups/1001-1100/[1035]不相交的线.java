//在两条独立的水平线上按给定的顺序写下 nums1 和 nums2 中的整数。 
//
// 现在，可以绘制一些连接两个数字 nums1[i] 和 nums2[j] 的直线，这些直线需要同时满足满足： 
//
// 
// nums1[i] == nums2[j] 
// 且绘制的直线不与任何其他连线（非水平线）相交。 
// 
//
// 请注意，连线即使在端点也不能相交：每个数字只能属于一条连线。 
//
// 以这种方法绘制线条，并返回可以绘制的最大连线数。 
//
// 
//
// 示例 1： 
//
//
// 
//输入：nums1 = [1,4,2], nums2 = [1,2,4]
//输出：2
//解释：可以画出两条不交叉的线，如上图所示。 
//但无法画出第三条不相交的直线，因为从 nums1[1]=4 到 nums2[2]=4 的直线将与从 nums1[2]=2 到 nums2[1]=2 的直线相
//交。
// 
//
// 
// 示例 2： 
//
// 
//输入：nums1 = [2,5,1,2,5], nums2 = [10,5,2,1,5,2]
//输出：3
// 
//
// 
// 示例 3： 
//
// 
//输入：nums1 = [1,3,7,1,7,5], nums2 = [1,9,2,5,1]
//输出：2 
//
// 
// 
// 
//
// 提示： 
//
// 
// 1 <= nums1.length <= 500 
// 1 <= nums2.length <= 500 
// 1 <= nums1[i], nums2[i] <= 2000 
// 
//
// 
// Related Topics 数组 动态规划 👍 235 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {        // 与 1143.最长公共子序列 本质相同, 不相交的连接线就是公共子序列
    public int maxUncrossedLines(int[] nums1, int[] nums2) {        // DP
        // idx1+1,idx2+1(扫过的长度): 考虑nums1的0~idx1, nums2的0~idx2最大公共长度
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
        for (int idx1 = 0; idx1 < nums1.length; idx1++) {
            for (int idx2 = 0; idx2 < nums2.length; idx2++) {
                if (nums1[idx1] == nums2[idx2]) {
                    dp[idx1 + 1][idx2 + 1] = dp[idx1][idx2] + 1;
                }
                else {
                    dp[idx1 + 1][idx2 + 1] = Math.max(dp[idx1 + 1][idx2], dp[idx1][idx2 + 1]);
                }
            }
        }
        return dp[nums1.length][nums2.length];
    }
}

//leetcode submit region end(Prohibit modification and deletion)
