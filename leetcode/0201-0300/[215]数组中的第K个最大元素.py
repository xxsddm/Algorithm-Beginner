# 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。 
# 
#  请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。 
# 
#  
# 
#  示例 1: 
# 
#  
# 输入: [3,2,1,5,6,4] 和 k = 2
# 输出: 5
#  
# 
#  示例 2: 
# 
#  
# 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
# 输出: 4 
# 
#  
# 
#  提示： 
# 
#  
#  1 <= k <= nums.length <= 104 
#  -104 <= nums[i] <= 104 
#  
#  Related Topics 数组 分治 快速选择 排序 堆（优先队列） 
#  👍 1209 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
from queue import PriorityQueue


class Solution:
    def findKthLargest(self, nums: List[int], k: int) -> int:
        container = PriorityQueue()
        for num in nums:
            container.put(num)
        while container.qsize() > k:
            container.get()
        return container.get()
# leetcode submit region end(Prohibit modification and deletion)
