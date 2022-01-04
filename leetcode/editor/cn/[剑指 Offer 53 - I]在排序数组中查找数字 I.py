# 统计一个数字在排序数组中出现的次数。 
# 
#  
# 
#  示例 1: 
# 
#  输入: nums = [5,7,7,8,8,10], target = 8
# 输出: 2 
# 
#  示例 2: 
# 
#  输入: nums = [5,7,7,8,8,10], target = 6
# 输出: 0 
# 
#  
# 
#  限制： 
# 
#  0 <= 数组长度 <= 50000 
# 
#  
# 
#  注意：本题与主站 34 题相同（仅返回值不同）：https://leetcode-cn.com/problems/find-first-and-last-
# position-of-element-in-sorted-array/ 
#  Related Topics 数组 二分查找 
#  👍 162 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def search(self, nums: List[int], target: int) -> int:
        if nums:
            if nums[0] > target or nums[-1] < target:
                return 0
        else:
            return 0
        num = 0
        for left in range(len(nums)):
            if nums[left] == target:
                num = 1
                break
        if num:
            for right in range(len(nums) - 1, -1, -1):
                if nums[right] == target:
                    return right - left + 1
        else:
            return 0
# leetcode submit region end(Prohibit modification and deletion)
