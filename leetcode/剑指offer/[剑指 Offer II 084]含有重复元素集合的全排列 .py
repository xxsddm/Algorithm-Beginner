# 给定一个可包含重复数字的整数集合 nums ，按任意顺序 返回它所有不重复的全排列。 
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：nums = [1,1,2]
# 输出：
# [[1,1,2],
#  [1,2,1],
#  [2,1,1]]
#  
# 
#  示例 2： 
# 
#  
# 输入：nums = [1,2,3]
# 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
#  
# 
#  
# 
#  提示： 
# 
#  
#  1 <= nums.length <= 8 
#  -10 <= nums[i] <= 10 
#  
# 
#  
# 
#  注意：本题与主站 47 题相同： https://leetcode-cn.com/problems/permutations-ii/ 
#  Related Topics 数组 回溯 
#  👍 1 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def permuteUnique(self, nums: List[int]) -> List[List[int]]:
        length = len(nums)
        if length == 1:
            return [[nums[0]]]  # 不能是[nums], 中途会不断修改同一个nums导致出错
        back = set()
        container = []
        for i in range(length):  # 交换
            last = nums[length - 1]
            nums[length - 1] = nums[i]
            nums[i] = last
            temp = nums.pop()
            sub = self.permuteUnique(nums)
            for subcontainer in sub:
                subcontainer.append(temp)
                subtuple = tuple(subcontainer)
                if subtuple not in back:
                    container.append(subcontainer)
                    back.add(subtuple)
            nums.append(temp)
            nums[i] = nums[length - 1]
            nums[length - 1] = last
        return container
# leetcode submit region end(Prohibit modification and deletion)
