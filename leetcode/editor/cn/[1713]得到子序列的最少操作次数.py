# 给你一个数组 target ，包含若干 互不相同 的整数，以及另一个整数数组 arr ，arr 可能 包含重复元素。 
# 
#  每一次操作中，你可以在 arr 的任意位置插入任一整数。比方说，如果 arr = [1,4,1,2] ，那么你可以在中间添加 3 得到 [1,4,3,1,
# 2] 。你可以在数组最开始或最后面添加整数。 
# 
#  请你返回 最少 操作次数，使得 target 成为 arr 的一个子序列。 
# 
#  一个数组的 子序列 指的是删除原数组的某些元素（可能一个元素都不删除），同时不改变其余元素的相对顺序得到的数组。比方说，[2,7,4] 是 [4,2,3,
# 7,2,1,4] 的子序列（加粗元素），但 [2,4,2] 不是子序列。 
# 
#  
# 
#  示例 1： 
# 
#  输入：target = [5,1,3], arr = [9,4,2,3,4]
# 输出：2
# 解释：你可以添加 5 和 1 ，使得 arr 变为 [5,9,4,1,2,3,4] ，target 为 arr 的子序列。
#  
# 
#  示例 2： 
# 
#  输入：target = [6,4,8,1,3,2], arr = [4,7,6,2,3,8,6,1]
# 输出：3
#  
# 
#  
# 
#  提示： 
# 
#  
#  1 <= target.length, arr.length <= 105 
#  1 <= target[i], arr[i] <= 109 
#  target 不包含任何重复元素。 
#  
#  Related Topics 贪心 数组 哈希表 二分查找 
#  👍 97 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def minOperations(self, target: List[int], arr: List[int]) -> int:
        valuekey = {num: i for i, num in enumerate(target)}
        container = []
        for value in arr:
            if value in valuekey:
                key = valuekey[value]   # 在target中的坐标
                i = bisect.bisect_left(container, key)
                if i == len(container):
                    container.append(key)
                else:
                    container[i] = key  # 替换不改变原有最大长度
        return len(target) - len(container)
# leetcode submit region end(Prohibit modification and deletion)


class Solution:
    def minOperations(self, target: List[int], arr: List[int]) -> int:  # 超时
        valuekey = {num: i for i, num in enumerate(target)}
        container = []
        indexlist = []
        for i in range(len(arr)):
            value = arr[i]
            if value in valuekey:
                maxnum = 1
                indexlist.append(valuekey[value])
                for j in range(len(indexlist)):
                    if indexlist[j] < valuekey[value]:
                        maxnum = max(maxnum, container[j] + 1)
                container.append(maxnum)
        container.append(0)
        return len(target) - max(container)


class Solution:
    def minOperations(self, target: List[int], arr: List[int]) -> int:
        valuekey = {num: i for i, num in enumerate(target)}
        container = []
        for value in arr:
            if value in valuekey:
                key = valuekey[value]   # 在target中的坐标

                # 二分查找key应插入的位置(左边key均小于key), 肯定没有bisect快
                left, right = 0, len(container) - 1
                while left <= right:
                    mid = (left + right) // 2
                    if key < container[mid]:
                        right = mid - 1
                    elif key > container[mid]:
                        left = mid + 1
                    else:
                        left = mid
                        break
                if left == len(container):
                    container.append(key)
                else:
                    container[left] = key  # 替换不改变原有最大长度

        return len(target) - len(container)
