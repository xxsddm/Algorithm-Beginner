# 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。 
# 
#  要求时间复杂度为O(n)。 
# 
#  
# 
#  示例1: 
# 
#  输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
# 输出: 6
# 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。 
# 
#  
# 
#  提示： 
# 
#  
#  1 <= arr.length <= 10^5 
#  -100 <= arr[i] <= 100 
#  
# 
#  注意：本题与主站 53 题相同：https://leetcode-cn.com/problems/maximum-subarray/ 
# 
#  
#  Related Topics 数组 分治 动态规划 
#  👍 304 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        maxsum = before = nums[0]
        for i in range(1, len(nums)):
            before = max(before + nums[i], nums[i])
            maxsum = max(maxsum, before)
        return maxsum
# leetcode submit region end(Prohibit modification and deletion)
