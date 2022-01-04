# 给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。 
# 
#  
# 
#  示例： 
# 
#  输入：
# A: [1,2,3,2,1]
# B: [3,2,1,4,7]
# 输出：3
# 解释：
# 长度最长的公共子数组是 [3, 2, 1] 。
#  
# 
#  
# 
#  提示： 
# 
#  
#  1 <= len(A), len(B) <= 1000 
#  0 <= A[i], B[i] < 100 
#  
#  Related Topics 数组 二分查找 动态规划 滑动窗口 哈希函数 滚动哈希 👍 521 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def findLength(self, nums1: List[int], nums2: List[int]) -> int:    # DP(空间优化见java)
        dp = [[0] * (len(nums2) + 1) for _ in range(len(nums1) + 1)]
        ans = 0
        for idx1 in range(len(nums1)):
            for idx2 in range(len(nums2)):
                dp[idx1 + 1][idx2 + 1] = 1 + dp[idx1][idx2] if nums1[idx1] == nums2[idx2] else 0
                ans = max(ans, dp[idx1 + 1][idx2 + 1])
        return ans

# leetcode submit region end(Prohibit modification and deletion)


class Solution:
    def findLength(self, nums1: List[int], nums2: List[int]) -> int:    # DP(略省空间)
        dp = [[0] * len(nums2) for _ in range(len(nums1))]
        ans = 0
        for i in range(len(nums1)):
            dp[i][0] = 1 if nums1[i] == nums2[0] else 0
            ans = max(ans, dp[i][0])
        for i in range(len(nums2)):
            dp[0][i] = 1 if nums1[0] == nums2[i] else 0
            ans = max(ans, dp[0][i])
        for idx1 in range(1, len(nums1)):
            for idx2 in range(1, len(nums2)):
                dp[idx1][idx2] = 1 + dp[idx1 - 1][idx2 - 1] if nums1[idx1] == nums2[idx2] else 0
                ans = max(ans, dp[idx1][idx2])
        return ans
