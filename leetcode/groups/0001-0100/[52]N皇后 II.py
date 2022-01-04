# n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。 
# 
#  给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。 
# 
#  
# 
#  
#  
#  示例 1： 
# 
#  
# 输入：n = 4
# 输出：2
# 解释：如上图所示，4 皇后问题存在两个不同的解法。
#  
# 
#  示例 2： 
# 
#  
# 输入：n = 1
# 输出：1
#  
# 
#  
# 
#  提示： 
# 
#  
#  1 <= n <= 9 
#  皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。 
#  
#  
#  
#  Related Topics 回溯 👍 285 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def totalNQueens(self, n: int) -> int:
        def backtrack(row=0):
            if row == n:
                self.counter += 1
                return
            for column in range(n):
                if (self.usedcolumn & (1 << column)) != 0:
                    continue

                skip = False
                for prevRow in range(row):
                    if abs(prevRow - row) == abs(rowcolumn[prevRow] - column):
                        skip = True
                        break
                if skip:
                    continue

                self.usedcolumn |= 1 << column
                rowcolumn[row] = column
                backtrack(row + 1)
                self.usedcolumn -= 1 << column

        self.counter = 0
        self.usedcolumn = 0
        rowcolumn = [0] * n

        backtrack()
        return self.counter

# leetcode submit region end(Prohibit modification and deletion)
