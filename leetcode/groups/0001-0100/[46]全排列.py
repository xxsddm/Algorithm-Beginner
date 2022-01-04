# 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。 
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：nums = [1,2,3]
# 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
#  
# 
#  示例 2： 
# 
#  
# 输入：nums = [0,1]
# 输出：[[0,1],[1,0]]
#  
# 
#  示例 3： 
# 
#  
# 输入：nums = [1]
# 输出：[[1]]
#  
# 
#  
# 
#  提示： 
# 
#  
#  1 <= nums.length <= 6 
#  -10 <= nums[i] <= 10 
#  nums 中的所有整数 互不相同 
#  
#  Related Topics 数组 回溯 
#  👍 1487 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def permute(self, nums: List[int]) -> List[List[int]]:
        length = len(nums)
        if length == 1:
            return [[nums[0]]]      # 不能是[nums], 中途会不断修改同一个nums导致出错
        container = []
        for i in range(length):     # 交换
            nums[i], nums[length - 1] = nums[length - 1], nums[i]
            temp = nums.pop()       # 栈移除末端元素, 剩余元素继续递归, 这种末端元素可以是原有nums中任意元素, 要交换位置
            for subcontainer in self.permute(nums):
                subcontainer.append(temp)
                container.append(subcontainer)
            nums.append(temp)
            nums[i], nums[length - 1] = nums[length - 1], nums[i]
        return container

# leetcode submit region end(Prohibit modification and deletion)


class Solution:
    def permute(self, nums):
        # 不同组合可以视为原始数组nums基础上交换元素实现, 不断增大起始交换点进行递归
        # 参与交换的一对点中, 前面的为start, 后续交换中start增大, 避免重复
        def backtrack(start):
            if start == n:
                res.append(nums[:])
            for i in range(start, n):
                nums[start], nums[i] = nums[i], nums[start]    # 交换
                backtrack(start + 1)
                nums[start], nums[i] = nums[i], nums[start]    # 恢复

        n = len(nums)
        container = []
        backtrack(0)
        return container


class Solution:
    def permute(self, nums: List[int]) -> List[List[int]]:
        container = []

        def backtrack(target, temp):    # 没有对输入参数做出修改
            if not target:
                container.append(temp)
            else:
                for i in range(len(target)):
                    backtrack(target[: i] + target[i + 1:], temp + [target[i]])    # 不能用temp.append, 会改变其他地方已有的temp

        backtrack(nums, [])
        return container
