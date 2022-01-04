# 元素的 频数 是该元素在一个数组中出现的次数。 
# 
#  给你一个整数数组 nums 和一个整数 k 。在一步操作中，你可以选择 nums 的一个下标，并将该下标对应元素的值增加 1 。 
# 
#  执行最多 k 次操作后，返回数组中最高频元素的 最大可能频数 。 
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：nums = [1,2,4], k = 5
# 输出：3
# 解释：对第一个元素执行 3 次递增操作，对第二个元素执 2 次递增操作，此时 nums = [4,4,4] 。
# 4 是数组中最高频元素，频数是 3 。 
# 
#  示例 2： 
# 
#  
# 输入：nums = [1,4,8,13], k = 5
# 输出：2
# 解释：存在多种最优解决方案：
# - 对第一个元素执行 3 次递增操作，此时 nums = [4,4,8,13] 。4 是数组中最高频元素，频数是 2 。
# - 对第二个元素执行 4 次递增操作，此时 nums = [1,8,8,13] 。8 是数组中最高频元素，频数是 2 。
# - 对第三个元素执行 5 次递增操作，此时 nums = [1,4,13,13] 。13 是数组中最高频元素，频数是 2 。
#  
# 
#  示例 3： 
# 
#  
# 输入：nums = [3,9,6], k = 2
# 输出：1
#  
# 
#  
# 
#  提示： 
# 
#  
#  1 <= nums.length <= 105 
#  1 <= nums[i] <= 105 
#  1 <= k <= 105 
#  
#  Related Topics 数组 二分查找 前缀和 滑动窗口 
#  👍 90 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def maxFrequency(self, nums: List[int], k: int) -> int:
        nums.sort()
        left = right = cumsum = max_length = 0
        length = len(nums)
        while right < length:
            while cumsum <= k:  # 所需累和不超过k, 右指针继续移动
                max_length = max(max_length, right - left + 1)
                right += 1
                if right < length:
                    cumsum += (right - left) * (nums[right] - nums[right - 1])
                else:
                    break
            leftmove = 0
            stop = False
            while cumsum > k:
                leftmove += 1
                if leftmove >= length - right:  # 右边剩余元素量不超过左指针已移动次数则可提前结束
                    stop = True
                    break
                cumsum += nums[left] - nums[right]
                left += 1
            if stop:
                break
        return max_length
# leetcode submit region end(Prohibit modification and deletion)
