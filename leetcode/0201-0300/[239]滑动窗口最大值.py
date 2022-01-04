# 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位
# 。 
# 
#  返回滑动窗口中的最大值。 
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
# 输出：[3,3,5,5,6,7]
# 解释：
# 滑动窗口的位置                最大值
# ---------------               -----
# [1  3  -1] -3  5  3  6  7       3
#  1 [3  -1  -3] 5  3  6  7       3
#  1  3 [-1  -3  5] 3  6  7       5
#  1  3  -1 [-3  5  3] 6  7       5
#  1  3  -1  -3 [5  3  6] 7       6
#  1  3  -1  -3  5 [3  6  7]      7
#  
# 
#  示例 2： 
# 
#  
# 输入：nums = [1], k = 1
# 输出：[1]
#  
# 
#  示例 3： 
# 
#  
# 输入：nums = [1,-1], k = 1
# 输出：[1,-1]
#  
# 
#  示例 4： 
# 
#  
# 输入：nums = [9,11], k = 2
# 输出：[11]
#  
# 
#  示例 5： 
# 
#  
# 输入：nums = [4,-2], k = 2
# 输出：[4] 
# 
#  
# 
#  提示： 
# 
#  
#  1 <= nums.length <= 105 
#  -104 <= nums[i] <= 104 
#  1 <= k <= nums.length 
#  
#  Related Topics 队列 数组 滑动窗口 单调队列 堆（优先队列） 
#  👍 1113 👎 0

from collections import deque


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def maxSlidingWindow(self, nums: List[int], k: int) -> List[int]:
        container = deque()    # 单调非增子序列索引, 存为双向链表
        for i in range(k):
            while container and nums[i] >= nums[container[-1]]:    # 如果当前元素比链表末位元素大(或相等), 则链表末位可以更换删除
                container.pop()
            container.append(i)

        ans = [nums[container[0]]]
        for index in range(k, len(nums)):
            while container and nums[index] >= nums[container[-1]]:    # 如果当前元素比链表末位元素大(或相等), 则链表末位可以更换删除
                container.pop()
            container.append(index)
            while container[0] <= index - k:    # 链表首位元素最大, 但可能已不再窗口内
                container.popleft()
            ans.append(nums[container[0]])

        return ans
# leetcode submit region end(Prohibit modification and deletion)
