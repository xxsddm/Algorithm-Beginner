# 给定一个整数数组 A，我们只能用以下方法修改该数组：我们选择某个索引 i 并将 A[i] 替换为 -A[i]，然后总共重复这个过程 K 次。（我们可以多次选
# 择同一个索引 i。） 
# 
#  以这种方式修改数组后，返回数组可能的最大和。 
# 
#  
# 
#  示例 1： 
# 
#  输入：A = [4,2,3], K = 1
# 输出：5
# 解释：选择索引 (1,) ，然后 A 变为 [4,-2,3]。
#  
# 
#  示例 2： 
# 
#  输入：A = [3,-1,0,2], K = 3
# 输出：6
# 解释：选择索引 (1, 2, 2) ，然后 A 变为 [3,1,0,2]。
#  
# 
#  示例 3： 
# 
#  输入：A = [2,-3,-1,5,-4], K = 2
# 输出：13
# 解释：选择索引 (1, 4) ，然后 A 变为 [2,3,-1,5,4]。
#  
# 
#  
# 
#  提示： 
# 
#  
#  1 <= A.length <= 10000 
#  1 <= K <= 10000 
#  -100 <= A[i] <= 100 
#  
#  Related Topics 贪心 数组 排序 👍 99 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def largestSumAfterKNegations(self, nums: List[int], k: int) -> int:
        maxneg = -100
        cumsum = 0
        nums.sort()
        for idx, num in enumerate(nums):
            if num < 0:
                if k > 0:
                    cumsum -= num
                    k -= 1
                else:
                    cumsum += num
            else:
                if maxneg == -100 and k > 0:
                    if idx > 0:
                        maxneg = nums[idx - 1]
                    minpos = num
                    cumsum -= (k % 2) * 2 * min(minpos, -maxneg)
                    maxneg = k = 0
                cumsum += num
        if k > 0:
            cumsum += (k % 2) * 2 * nums[-1]
        return cumsum

# leetcode submit region end(Prohibit modification and deletion)
