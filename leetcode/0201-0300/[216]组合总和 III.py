# 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。 
# 
#  说明： 
# 
#  
#  所有数字都是正整数。 
#  解集不能包含重复的组合。 
#  
# 
#  示例 1: 
# 
#  输入: k = 3, n = 7
# 输出: [[1,2,4]]
#  
# 
#  示例 2: 
# 
#  输入: k = 3, n = 9
# 输出: [[1,2,6], [1,3,5], [2,3,4]]
#  
#  Related Topics 数组 回溯 👍 334 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def combinationSum3(self, k: int, n: int) -> List[List[int]]:
        def backtrack(start=1, cumsum=0):
            if len(sublist) == k and cumsum == n:
                container.append(sublist[:])
                return
            for num in range(start, min(10 - k + len(sublist), n - cumsum) + 1):
                sublist.append(num)
                backtrack(num + 1, cumsum + num)
                sublist.pop()

        container = []
        sublist = []
        backtrack()
        return container

# leetcode submit region end(Prohibit modification and deletion)
