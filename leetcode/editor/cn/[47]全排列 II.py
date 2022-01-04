# 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。 
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
#  Related Topics 数组 回溯 
#  👍 768 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def permuteUnique(self, nums: List[int]) -> List[List[int]]:
        nums.sort()
        length = len(nums)
        container = []
        subcontainer = []    # 子序列

        def perm(visited, idx=0):
            # idx: 子序列subcontainer的待填充元素索引(填满length个算一种组合)
            if idx == length:
                container.append(subcontainer[:])
                return
            for i in range(length):
                """
                1. i点已经填入子序列subcontainer, 跳过.
                2. i点与i - 1点值相同, 出现连续的重复值(假设有k个), 只有先取其中第k个(末端)重复值, 重复该步骤
                (不断取出剩余部分中的末端重复值).
                ps: 若抽出其中一个重复值后, 连续重复值不再连续(即取出的不是其中首端重复值), 则中断点(取出点)的
                后一个位置(仍为重复值)不可能再加入子序列subcontainer中, idx无法到达末端, 该子序列不会填入最后
                结果container中.即规定了连续重复值的填入顺序为从后往前(排序后重复值自然连续).
                """
                # 这里的是从后往前填入, 若规定从前往后, 则相应部分改为:
                #                i < length - 1 and nums[i] == nums[i + 1] and visited[i + 1]:
                if visited[i] or i > 0 and nums[i] == nums[i - 1] and visited[i - 1]:
                    continue
                subcontainer.append(nums[i])
                visited[i] = True
                perm(visited, idx + 1)
                visited[i] = False
                subcontainer.pop()

        perm([False] * length)
        return container

# leetcode submit region end(Prohibit modification and deletion)


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
