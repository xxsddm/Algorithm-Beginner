# 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。 
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：nums1 = [1,3], nums2 = [2]
# 输出：2.00000
# 解释：合并数组 = [1,2,3] ，中位数 2
#  
# 
#  示例 2： 
# 
#  
# 输入：nums1 = [1,2], nums2 = [3,4]
# 输出：2.50000
# 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
#  
# 
#  示例 3： 
# 
#  
# 输入：nums1 = [0,0], nums2 = [0,0]
# 输出：0.00000
#  
# 
#  示例 4： 
# 
#  
# 输入：nums1 = [], nums2 = [1]
# 输出：1.00000
#  
# 
#  示例 5： 
# 
#  
# 输入：nums1 = [2], nums2 = []
# 输出：2.00000
#  
# 
#  
# 
#  提示： 
# 
#  
#  nums1.length == m 
#  nums2.length == n 
#  0 <= m <= 1000 
#  0 <= n <= 1000 
#  1 <= m + n <= 2000 
#  -10⁶ <= nums1[i], nums2[i] <= 10⁶ 
#  
# 
#  
# 
#  进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？ 
#  Related Topics 数组 二分查找 分治 👍 4377 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def findMedianSortedArrays(self, nums1: List[int], nums2: List[int]) -> float:
        if len(nums1) > len(nums2):
            temp = nums2
            nums2 = nums1
            nums1 = temp
        length1, length2 = len(nums1), len(nums2)
        cover = (len(nums1) + len(nums2) + 1) >> 1
        left, right = 0, len(nums1)
        cover1 = cover2 = 0
        while left <= right:
            cover1 = (left + right) >> 1
            cover2 = cover - cover1
            if cover1 > 0 and cover2 < length2 and nums1[cover1 - 1] > nums2[cover2]:
                right = cover1 - 1
            elif cover2 > 0 and cover1 < length1 and nums1[cover1] < nums2[cover2 - 1]:
                left = cover1 + 1
            else:
                break
        leftmax1 = -math.inf if cover1 == 0 else nums1[cover1 - 1]
        leftmax2 = -math.inf if cover2 == 0 else nums2[cover2 - 1]
        if (length1 + length2) % 2 == 0:
            rightmin1 = math.inf if cover1 == length1 else nums1[cover1]
            rightmax2 = math.inf if cover2 == length2 else nums2[cover2]
            return (max(leftmax1, leftmax2) + min(rightmin1, rightmax2)) / 2
        return max(leftmax1, leftmax2)

# leetcode submit region end(Prohibit modification and deletion)
