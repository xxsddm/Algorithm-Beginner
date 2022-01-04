# 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。 
# 
#  
#  
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：nums = [-4,-1,0,3,10]
# 输出：[0,1,9,16,100]
# 解释：平方后，数组变为 [16,1,0,9,100]
# 排序后，数组变为 [0,1,9,16,100] 
# 
#  示例 2： 
# 
#  
# 输入：nums = [-7,-3,2,3,11]
# 输出：[4,9,9,49,121]
#  
# 
#  
# 
#  提示： 
# 
#  
#  1 <= nums.length <= 104 
#  -104 <= nums[i] <= 104 
#  nums 已按 非递减顺序 排序 
#  
# 
#  
# 
#  进阶： 
# 
#  
#  请你设计时间复杂度为 O(n) 的算法解决本问题 
#  
#  Related Topics 数组 双指针 排序 
#  👍 259 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def sortedSquares(self, nums: List[int]) -> List[int]:
        length = len(nums)
        for i in range(length):
            nums[i] = nums[i] ** 2
        left, right, loc, container = 0, length - 1, length - 1, [0] * length  # 双指针
        while loc >= 0:
            temp1, temp2 = nums[left], nums[right]
            if temp1 < temp2:
                container[loc] = temp2
                right -= 1
            else:
                container[loc] = temp1
                left += 1
            loc -= 1
        return container

# leetcode submit region end(Prohibit modification and deletion)


class Solution:
    def sortedSquares(self, nums: List[int]) -> List[int]:      # 不知道为什么报错
        for i in range(len(nums)):
            nums[i] = nums[i] ** 2
        if len(nums) == 1:
            return nums

        start = None
        for i in range(len(nums) - 1):
            if nums[i] <= nums[i + 1]:
                start = i
                break

        if start is None:
            nums.reverse()
            return nums

        left, right, container = start - 1, start + 1, [nums[start]]
        while left >= 0 or right < len(nums):
            if left < 0:
                container.append(nums[right])
                right += 1
                continue
            if right >= len(nums):
                container.append(nums[left])
                left -= 1
                continue
            num1, num2 = nums[left], nums[right]
            if num1 > num2:
                container.append(num2)
                right += 1
            else:
                container.append(num1)
                left -= 1
        return container
