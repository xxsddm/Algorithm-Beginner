# 给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。 
# 
#  请你找出符合题意的 最短 子数组，并输出它的长度。 
# 
#  
# 
#  
#  
#  示例 1： 
# 
#  
# 输入：nums = [2,6,4,8,10,9,15]
# 输出：5
# 解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
#  
# 
#  示例 2： 
# 
#  
# 输入：nums = [1,2,3,4]
# 输出：0
#  
# 
#  示例 3： 
# 
#  
# 输入：nums = [1]
# 输出：0
#  
# 
#  
# 
#  提示： 
# 
#  
#  1 <= nums.length <= 104 
#  -105 <= nums[i] <= 105 
#  
# 
#  
# 
#  进阶：你可以设计一个时间复杂度为 O(n) 的解决方案吗？ 
#  
#  
#  Related Topics 栈 贪心 数组 双指针 排序 单调栈 
#  👍 589 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def findUnsortedSubarray(self, nums: List[int]) -> int:
        length = len(nums)
        left, right = length - 1, 0  # 中间待排序子数组的左右节点索引
        numleft, numright = nums[-1], nums[0]
        for i in range(1, length):
            if nums[i] >= numright:
                numright = nums[i]
            else:
                right = i   # 出现 右 < 左max 情况, 则i归入待排序子数组, 需要更新right
        
            if nums[length - i - 1] <= numleft:
                numleft = nums[length - i - 1]
            else:
                left = length - i - 1
        return right - left + 1 if right != 0 else 0

# leetcode submit region end(Prohibit modification and deletion)


class Solution:
    def findUnsortedSubarray(self, nums: List[int]) -> int:  # 笨办法
        temp = [i for i in nums]
        temp.sort()
        left, right = 0, length - 1
        while left <= right and temp[left] == nums[left]:
            left += 1
        while left <= right and temp[right] == nums[right]:
            right -= 1
        return right - left + 1
