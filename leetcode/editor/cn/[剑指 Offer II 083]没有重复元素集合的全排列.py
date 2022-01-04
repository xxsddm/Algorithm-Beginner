# 给定一个不含重复数字的整数数组 nums ，返回其 所有可能的全排列 。可以 按任意顺序 返回答案。 
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
# 
#  
# 
#  注意：本题与主站 46 题相同：https://leetcode-cn.com/problems/permutations/ 
#  Related Topics 数组 回溯 
#  👍 1 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def permute(self, nums: List[int]) -> List[List[int]]:
        length = len(nums)
        if length == 1:
            return [[nums[0]]]    # 不能是[nums], 中途会不断修改同一个nums导致出错
        container = []
        for i in range(length):  # 交换
            last = nums[length - 1]
            nums[length - 1] = nums[i]
            nums[i] = last
            temp = nums.pop()
            sub = self.permute(nums)
            for subcontainer in sub:
                subcontainer.append(temp)
                container.append(subcontainer)
            nums.append(temp)
            nums[i] = nums[length - 1]
            nums[length - 1] = last
        return container
# leetcode submit region end(Prohibit modification and deletion)
