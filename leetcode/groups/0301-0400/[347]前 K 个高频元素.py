# 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。 
# 
#  
# 
#  示例 1: 
# 
#  
# 输入: nums = [1,1,1,2,2,3], k = 2
# 输出: [1,2]
#  
# 
#  示例 2: 
# 
#  
# 输入: nums = [1], k = 1
# 输出: [1] 
# 
#  
# 
#  提示： 
# 
#  
#  1 <= nums.length <= 105 
#  k 的取值范围是 [1, 数组中不相同的元素的个数] 
#  题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的 
#  
# 
#  
# 
#  进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。 
#  Related Topics 数组 哈希表 分治 桶排序 计数 快速选择 排序 堆（优先队列） 
#  👍 820 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
from queue import PriorityQueue


class Solution:
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        container = {}    # num -> times
        queue = PriorityQueue()    # python的优先队列默认小根堆
        ans = [0] * k
        for num in nums:
            if num not in container:
                container[num] = 1
            else:
                container[num] += 1
        while container:
            num, times = container.popitem()
            queue.put((-times, num))
        for i in range(k):
            ans[i] = queue.get()[1]
        return ans
# leetcode submit region end(Prohibit modification and deletion)
