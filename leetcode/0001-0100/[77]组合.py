# 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。 
# 
#  你可以按 任何顺序 返回答案。 
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：n = 4, k = 2
# 输出：
# [
#   [2,4],
#   [3,4],
#   [2,3],
#   [1,2],
#   [1,3],
#   [1,4],
# ] 
# 
#  示例 2： 
# 
#  
# 输入：n = 1, k = 1
# 输出：[[1]] 
# 
#  
# 
#  提示： 
# 
#  
#  1 <= n <= 20 
#  1 <= k <= n 
#  
#  Related Topics 数组 回溯 👍 667 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def combine(self, n: int, k: int) -> List[List[int]]:       # 从小到大加入数字, 避免重复
        self.limit = n
        self.length = k
        self.container = []
        self.sublist = []
        self.backtrack(1)
        return self.container

    def backtrack(self, start):     # start: 还未加入的1~n中, 可加入的最小元素(避免重复从小到大加入组合)
        if len(self.sublist) == self.length:
            self.container.append(self.sublist[:])
            return
        for element in range(start, self.limit - self.length + len(self.sublist) + 2):
            self.sublist.append(element)
            self.backtrack(element + 1)
            self.sublist.pop()

# leetcode submit region end(Prohibit modification and deletion)
