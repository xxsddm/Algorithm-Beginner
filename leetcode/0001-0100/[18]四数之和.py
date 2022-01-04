# 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c +
#  d 的值与 target 相等？找出所有满足条件且不重复的四元组。 
# 
#  注意：答案中不可以包含重复的四元组。 
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：nums = [1,0,-1,0,-2,2], target = 0
# 输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
#  
# 
#  示例 2： 
# 
#  
# 输入：nums = [], target = 0
# 输出：[]
#  
# 
#  
# 
#  提示： 
# 
#  
#  0 <= nums.length <= 200 
#  -109 <= nums[i] <= 109 
#  -109 <= target <= 109 
#  
#  Related Topics 数组 双指针 排序 
#  👍 898 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def fourSum(self, nums: List[int], target: int) -> List[List[int]]:
        container = []  # 存放符合要求的列表
        length = len(nums)
        if length < 4:
            return container
        nums.sort()
        right_1 = nums[-1]  # 末尾单元素和
        right_2 = right_1 + nums[-2]  # 末尾两元素和
        right_3 = right_2 + nums[-3]  # 末尾三元素和
        for i in range(length - 3):
            # i与上一轮i相同, 跳过本轮i, 因为j, k, l在后一轮取值范围是前一轮的子集
            if i > 0 and nums[i] == nums[i - 1]:
                continue
            aim = target - nums[i]
            if nums[i + 1] + nums[i + 2] + nums[i + 3] > aim:
                break
            if right_3 < aim:
                continue
            for j in range(i + 1, length - 2):
                if j > i + 1 and nums[j] == nums[j - 1]:
                    continue
                aim = target - nums[i] - nums[j]
                if nums[j + 1] + nums[j + 2] > aim:
                    break
                if right_2 < aim:
                    continue
                left, right = j + 1, length - 1
                while left < right:
                    if nums[left] + nums[right] == aim:
                        container.append([nums[i], nums[j], nums[left], nums[right]])
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


class Solution:
    def fourSum(self, nums: List[int], target: int) -> List[List[int]]:  # k数之和, 优化后还是慢
        if len(nums) < 4:
            return []
        nums.sort()
        container = []
        sublist = []

        def backtrack(start=0, cumsum=0):
            if len(sublist) == 4:
                if cumsum == target:
                    container.append(sublist[:])
                return
            for idx in range(start, len(nums) + len(sublist) - 3):
                temp = cumsum + nums[idx]
                if idx > start and nums[idx] == nums[idx - 1] \
                        or temp + subsum(len(nums) - 3 + len(sublist)) < target:
                    continue
                if temp + subsum(idx, idx + 2 - len(sublist)) > target:
                    break
                sublist.append(nums[idx])
                backtrack(idx + 1, temp)
                sublist.pop()

        def subsum(start, end=len(nums) - 1):
            cumsum = 0
            for i in range(start, end + 1):
                cumsum += nums[i]
            return cumsum

        backtrack()
        return container


class Solution:
    # 最后两层替换为二分查找
    def fourSum(self, nums: List[int], target: int) -> List[List[int]]:
        container = []  # 存放符合要求的列表
        length = len(nums)
        if length < 4:
            return container
        nums.sort()
        right_1 = nums[-1]  # 末尾单元素和
        right_2 = right_1 + nums[-2]  # 末尾两元素和
        right_3 = right_2 + nums[-3]  # 末尾三元素和
        backj = backk = backl = -1  # 备份已加入container的列表元素索引
        for i in range(length - 3):
            if container and i > 0:
                if nums[i] == nums[i - 1]:  # i与上一轮i相同, 考虑是否跳过本轮i
                    if backj != i or container[-1][1] != nums[i]:
                        continue
            aim = target - nums[i]
            if nums[i + 1] + nums[i + 2] + nums[i + 3] > aim:
                break
            if right_3 < aim:
                continue
            for j in range(i + 1, length - 2):
                if container and j > i + 1:
                    if nums[j] == nums[j - 1]:
                        if backk != j or container[-1][2] != nums[j]:
                            continue
                aim = target - nums[i] - nums[j]
                if nums[j + 1] + nums[j + 2] > aim:
                    break
                if right_2 < aim:
                    continue
                for k in range(j + 1, length - 1):
                    if container and k > j + 1:
                        if nums[k] == nums[k - 1]:
                            if backl != k or container[-1][3] != nums[k]:
                                continue
                    aim = target - nums[i] - nums[j] - nums[k]
                    left = k + 1
                    right = length - 1
                    if aim < nums[left]:
                        break
                    if right_1 < aim:
                        continue
                    loc = (left + right) // 2
                    while left <= right:
                        if nums[loc] > aim:
                            right = loc - 1
                        elif nums[loc] < aim:
                            left = loc + 1
                        else:
                            container.append([nums[i], nums[j], nums[k], aim])
                            backj = backk = backl = j, k, loc
                            break
                        loc = (left + right) // 2
        return container
