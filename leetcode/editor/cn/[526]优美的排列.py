# 假设有从 1 到 N 的 N 个整数，如果从这 N 个数字中成功构造出一个数组，使得数组的第 i 位 (1 <= i <= N) 满足如下两个条件中的一个，
# 我们就称这个数组为一个优美的排列。条件： 
# 
#  
#  第 i 位的数字能被 i 整除 
#  i 能被第 i 位上的数字整除 
#  
# 
#  现在给定一个整数 N，请问可以构造多少个优美的排列？ 
# 
#  示例1: 
# 
#  
# 输入: 2
# 输出: 2
# 解释: 
# 
# 第 1 个优美的排列是 [1, 2]:
#   第 1 个位置（i=1）上的数字是1，1能被 i（i=1）整除
#   第 2 个位置（i=2）上的数字是2，2能被 i（i=2）整除
# 
# 第 2 个优美的排列是 [2, 1]:
#   第 1 个位置（i=1）上的数字是2，2能被 i（i=1）整除
#   第 2 个位置（i=2）上的数字是1，i（i=2）能被 1 整除
#  
# 
#  说明: 
# 
#  
#  N 是一个正整数，并且不会超过15。 
#  
#  Related Topics 位运算 数组 动态规划 回溯 状态压缩 
#  👍 147 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def countArrangement(self, n: int) -> int:      # 记忆化搜索DP
        def bitcount(target):       # 计算state中1的数量
            ans = 0
            while target > 0:
                ans += target & 1
                target >>= 1
            return ans
        maxnum = (1 << n) - 1
        container = [0] * (maxnum + 1)
        container[0] = 1
        for state in range(maxnum):
            loc = bitcount(state) + 1
            for num in range(1, n + 1):
                if (state >> num - 1) & 1 == 0 and (num % loc == 0 or loc % num == 0):
                    container[state | 1 << num - 1] += container[state]
        return container[maxnum]

# leetcode submit region end(Prohibit modification and deletion)


class Solution:
    def countArrangement(self, n: int) -> int:      # 记忆化搜索DFS(可以进一步状态压缩)
        self.num = n
        self.used = 0
        # python用位运算和数组保存反而会慢一些
        self.container = {}
        return self.backtrack(0)

    def backtrack(self, idx):
        count = 0
        for i in range(self.num):
            if (self.used & 1 << i) == 0 and ((i + 1) % (idx + 1) == 0 or (idx + 1) % (i + 1) == 0):
                self.used |= 1 << i
                if idx + 1 < self.num:
                    if (idx + 1, self.used) not in self.container:
                        self.container[(idx + 1, self.used)] = self.backtrack(idx + 1)
                    count += self.container[(idx + 1, self.used)]
                else:
                    count += 1
                self.used -= 1 << i
        return count


class Solution:
    def countArrangement(self, n: int) -> int:      # 无记忆化搜索
        used = [False] * n      # 使用过的为True, 用位运算反而变慢, 看不懂

        def backtrack(idx):     # 构造排列方式, 从索引0开始填入, 直到填满n-1
            if idx == n:
                return 1
            count = 0
            for i in range(n):
                if not used[i] and ((i + 1) % (idx + 1) == 0 or (idx + 1) % (i + 1) == 0):
                    used[i] = True
                    count += backtrack(idx + 1)
                    used[i] = False
            return count

        return backtrack(0)
