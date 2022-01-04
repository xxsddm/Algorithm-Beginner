# 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。 
# 
#  子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序
# 列。 
#  
# 
#  示例 1： 
# 
#  
# 输入：nums = [10,9,2,5,3,7,101,18]
# 输出：4
# 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
#  
# 
#  示例 2： 
# 
#  
# 输入：nums = [0,1,0,3,2,3]
# 输出：4
#  
# 
#  示例 3： 
# 
#  
# 输入：nums = [7,7,7,7,7,7,7]
# 输出：1
#  
# 
#  
# 
#  提示： 
# 
#  
#  1 <= nums.length <= 2500 
#  -104 <= nums[i] <= 104 
#  
# 
#  
# 
#  进阶： 
# 
#  
#  你可以设计时间复杂度为 O(n2) 的解决方案吗？ 
#  你能将算法的时间复杂度降低到 O(n log(n)) 吗? 
#  
#  Related Topics 数组 二分查找 动态规划 
#  👍 1732 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
import bisect


class Solution:
    def lengthOfLIS(self, nums: List[int]) -> int:
        container = []
        for num in nums:
            if not container or num > container[-1]:
                container.append(num)
            else:
                # 替换原有位置元素, 不改变已有最大长度, 避免不断取max
                # 且不影响后续更大元素进入数组
                # 需要仔细想一下才能理解, 很妙
                container[bisect.bisect_left(container, num)] = num
        return len(container)

# leetcode submit region end(Prohibit modification and deletion)


class Solution:
    def lengthOfLIS(self, nums: List[int]) -> int:      # DP(很慢)
        dp = [1] * len(nums)
        ans = 1
        for idx in range(len(nums)):
            for i in range(idx):
                if nums[idx] > nums[i]:
                    dp[idx] = max(dp[idx], dp[i] + 1)
            ans = max(ans, dp[idx])
        return ans

