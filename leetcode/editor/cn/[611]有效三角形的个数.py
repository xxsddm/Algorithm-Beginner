# 给定一个包含非负整数的数组，你的任务是统计其中可以组成三角形三条边的三元组个数。 
# 
#  示例 1: 
# 
#  
# 输入: [2,2,3,4]
# 输出: 3
# 解释:
# 有效的组合是: 
# 2,3,4 (使用第一个 2)
# 2,3,4 (使用第二个 2)
# 2,2,3
#  
# 
#  注意: 
# 
#  
#  数组长度不超过1000。 
#  数组里整数的范围为 [0, 1000]。 
#  
#  Related Topics 贪心 数组 双指针 二分查找 排序 
#  👍 188 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def triangleNumber(self, nums: List[int]) -> int:
        length = len(nums)
        nums.sort()
        count = 0
        for right in range(2, length):  # 这种方法, 必须选择最大索引固定, 选择最小索引则会遗漏情况
            left, mid = 0, right - 1
            while left < mid:
                if nums[left] + nums[mid] > nums[right]:
                    count += mid - left  # 则left可以取 left ~ mid-1
                    mid -= 1
                else:
                    left += 1
        return count
# leetcode submit region end(Prohibit modification and deletion)
from bisect import bisect_left


class Solution:
    def triangleNumber(self, nums: List[int]) -> int:
        length = len(nums)
        nums.sort()
        count = 0
        for i in range(length - 2):
            for j in range(i + 1, length - 1):
                temp = bisect.bisect_left(nums, nums[i] + nums[j]) - j - 1
                if temp > 0:
                    count += temp
        return count
