# 给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[
# l] = 0。 
# 
#  为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -228 到 228 - 1 之间，最
# 终结果不会超过 231 - 1 。 
# 
#  例如: 
# 
#  
# 输入:
# A = [ 1, 2]
# B = [-2,-1]
# C = [-1, 2]
# D = [ 0, 2]
# 
# 输出:
# 2
# 
# 解释:
# 两个元组如下:
# 1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
# 2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
#  
#  Related Topics 数组 哈希表 
#  👍 392 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
import bisect


class Solution:
    def fourSumCount(self, nums1: List[int], nums2: List[int], nums3: List[int], nums4: List[int]) -> int:
        container = {}
        count = 0
        for num1 in nums1:
            for num2 in nums2:
                if num1 + num2 in container:
                    container[num1 + num2] += 1
                else:
                    container[num1 + num2] = 1

        for num3 in nums3:
            for num4 in nums4:
                if - num3 - num4 in container:
                    count += container[- num3 - num4]

        return count
# leetcode submit region end(Prohibit modification and deletion)


class Solution:
    def fourSumCount(self, nums1: List[int], nums2: List[int], nums3: List[int], nums4: List[int]) -> int:
        nums1.sort(), nums2.sort(), nums3.sort(), nums4.sort()
        before1 = before2 = before3 = 0
        count = 0
        length = len(nums1)
        for first in range(length):
            if first > 0 and nums1[first] == nums1[first - 1]:
                count += before1
                continue
            elif nums1[first] + nums2[0] + nums3[0] + nums4[0] > 0:
                break
            before1 = 0
            for second in range(length):
                if second > 0 and nums2[second] == nums2[second - 1]:
                    count += before2
                    before1 += before2
                    continue
                elif nums1[first] + nums2[second] + nums3[0] + nums4[0] > 0:
                    break
                before2 = 0
                for third in range(length):
                    if third > 0 and nums3[third] == nums3[third - 1]:
                        count += before3
                        before2 += before3
                        continue
                    elif nums1[first] + nums2[second] + nums3[third] + nums4[0] > 0:
                        break
                    before3 = 0
                    target = - nums1[first] - nums2[second] - nums3[third]
                    forth = bisect.bisect_left(target, nums4)
                    if nums4[forth] == target:
                        before3 += 1
                        while forth + 1 < length and nums4[forth] == nums4[forth + 1]:
                            forth += 1
                            before3 += 1
                        count += before3
                    else:
                        break
                    before2 += before3
                before1 += before2
        return count
