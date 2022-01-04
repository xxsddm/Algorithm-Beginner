# 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重
# 复的三元组。 
# 
#  注意：答案中不可以包含重复的三元组。 
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：nums = [-1,0,1,2,-1,-4]
# 输出：[[-1,-1,2],[-1,0,1]]
#  
# 
#  示例 2： 
# 
#  
# 输入：nums = []
# 输出：[]
#  
# 
#  示例 3： 
# 
#  
# 输入：nums = [0]
# 输出：[]
#  
# 
#  
# 
#  提示： 
# 
#  
#  0 <= nums.length <= 3000 
#  -105 <= nums[i] <= 105 
#  
#  Related Topics 数组 双指针 排序 
#  👍 3542 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def threeSum(self, nums: List[int]) -> List[List[int]]:  # 与18四数之和相同, 这里直接复制
        container = []  # 存放符合要求的列表
        length = len(nums)
        if length < 3:
            return container
        nums.sort()
        right_2 = nums[-1] + nums[-2]    # 末尾两元素和
        for i in range(length - 2):
            # i与上一轮i相同, 跳过本轮i, 因为j, k在后一轮取值范围是前一轮的子集
            if i > 0 and nums[i] == nums[i - 1]:
                continue
            aim = - nums[i]
            if nums[i+1] + nums[i+2] > aim:
                break
            if right_2 < aim:
                continue
            left, right = i + 1, length - 1
            while left < right:
                if nums[left] + nums[right] == aim:
                    container.append([nums[i], nums[left], nums[right]])
                    while left < right and nums[left] == nums[left + 1]:
                        left += 1
                    left += 1
                    while left < right and nums[right] == nums[right - 1]:
                        right -= 1
                    right -= 1
                elif nums[left] + nums[right] < aim:
                    while left < right and nums[left] == nums[left + 1]:
                        left += 1
                    left += 1
                else:
                    while left < right and nums[right] == nums[right - 1]:
                        right -= 1
                    right -= 1
        return container
# leetcode submit region end(Prohibit modification and deletion)
