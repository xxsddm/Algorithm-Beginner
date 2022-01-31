# 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。 
# 
#  
# 
#  示例 1: 
# 
#  输入: [7,5,6,4]
# 输出: 5 
# 
#  
# 
#  限制： 
# 
#  0 <= 数组长度 <= 50000 
#  Related Topics 树状数组 线段树 数组 二分查找 分治 有序集合 归并排序 👍 608 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
def sort(nums: List[int]) -> int:
    return mergesort(0, len(nums) - 1, nums, [0] * len(nums))

def mergesort(start: int, end: int, nums: List[int], aux: List[int]) -> int:
    if start >= end:
        return 0
    mid = (start + end) >> 1
    ans = mergesort(start, mid, nums, aux) + mergesort(mid + 1, end, nums, aux)
    for i in range(start, end + 1):
        aux[i] = nums[i]
    left, right, idx = start, mid + 1, start
    while True:
        if left == mid + 1:
            while idx <= end:
                nums[idx] = aux[right]
                idx += 1
                right += 1
            return ans
        if right == end + 1:
            while idx <= end:
                nums[idx] = aux[left]
                idx += 1
                left += 1
            return ans
        if aux[left] <= aux[right]:
            nums[idx] = aux[left]
            idx += 1
            left += 1
            ans += end - right + 1
        else:
            nums[idx] = aux[right]
            idx += 1
            right += 1


class Solution:
    def reversePairs(self, nums: List[int]) -> int:
        n = len(nums)
        return n * (n - 1) // 2 - sort(nums)

# leetcode submit region end(Prohibit modification and deletion)
