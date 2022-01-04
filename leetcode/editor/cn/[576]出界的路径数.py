# 给你一个大小为 m x n 的网格和一个球。球的起始坐标为 [startRow, startColumn] 。你可以将球移到在四个方向上相邻的单元格内（可以
# 穿过网格边界到达网格之外）。你 最多 可以移动 maxMove 次球。 
# 
#  给你五个整数 m、n、maxMove、startRow 以及 startColumn ，找出并返回可以将球移出边界的路径数量。因为答案可能非常大，返回对 
# 109 + 7 取余 后的结果。 
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：m = 2, n = 2, maxMove = 2, startRow = 0, startColumn = 0
# 输出：6
#  
# 
#  示例 2： 
# 
#  
# 输入：m = 1, n = 3, maxMove = 3, startRow = 0, startColumn = 1
# 输出：12
#  
# 
#  
# 
#  提示： 
# 
#  
#  1 <= m, n <= 50 
#  0 <= maxMove <= 50 
#  0 <= startRow < m 
#  0 <= startColumn < n 
#  
#  Related Topics 动态规划 
#  👍 147 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def findPaths(self, m: int, n: int, maxMove: int, startRow: int, startColumn: int) -> int:
        if maxMove == 0:
            return 0
        self.mod = int(1e9 + 7)
        self.row = m
        self.column = n
        self.dirs = [[1, 0], [-1, 0], [0, 1], [0, -1]]
        self.container = [[[-1] * (maxMove + 1) for _ in range(n)] for __ in range(m)]
        return self.dfs(startRow, startColumn, maxMove)

    def dfs(self, startRow, startColumn, maxMove):
        if startRow < 0 or startRow >= self.row or startColumn < 0 or startColumn >= self.column:
            return 1
        if maxMove == 0 or startRow + maxMove < self.row and startRow - maxMove >= 0 \
                and startColumn + maxMove < self.column and startColumn - maxMove >= 0:
            return 0
        if self.container[startRow][startColumn][maxMove] != -1:
            return self.container[startRow][startColumn][maxMove]
        count = 0
        for dir in self.dirs:
            count = (count + self.dfs(startRow + dir[0], startColumn + dir[1], maxMove - 1)) % self.mod
        self.container[startRow][startColumn][maxMove] = count
        return count

# leetcode submit region end(Prohibit modification and deletion)


class Solution:
    def findPaths(self, m: int, n: int, maxMove: int, startRow: int, startColumn: int) -> int:
        if maxMove == 0:
            return 0
        self.mod = int(1e9 + 7)
        self.row = m
        self.column = n
        self.container = [[[-1] * (maxMove + 1) for _ in range(n)] for __ in range(m)]
        count = 0
        for move in range(1, maxMove + 1):
            self.fillarray(startRow, startColumn, move)
            count += self.container[startRow][startColumn][move]
        return count % self.mod

    def fillarray(self, startRow, startColumn, maxMove):
        if self.container[startRow][startColumn][maxMove] != -1:
            return

        if maxMove == 1:
            if startRow == self.row - 1 or startRow == 0:
                if startColumn == self.column - 1 or startColumn == 0:
                    self.container[startRow][startColumn][maxMove] = 2
                    if self.column - 1 == 0:
                        self.container[startRow][startColumn][maxMove] += 1
                else:
                    self.container[startRow][startColumn][maxMove] = 1
                if self.row - 1 == 0:
                    self.container[startRow][startColumn][maxMove] += 1
            elif startColumn == self.column - 1 or startColumn == 0:
                self.container[startRow][startColumn][maxMove] = 1
                if self.column - 1 == 0:
                    self.container[startRow][startColumn][maxMove] += 1
            else:
                self.container[startRow][startColumn][maxMove] = 0
            return

        count = 0
        if startRow < self.row - 1:
            if self.container[startRow + 1][startColumn][maxMove - 1] == -1:
                self.fillarray(startRow + 1, startColumn, maxMove - 1)
            count += self.container[startRow + 1][startColumn][maxMove - 1]
        if startColumn < self.column - 1:
            if self.container[startRow][startColumn + 1][maxMove - 1] == -1:
                self.fillarray(startRow, startColumn + 1, maxMove - 1)
            count += self.container[startRow][startColumn + 1][maxMove - 1]
        if startRow > 0:
            if self.container[startRow - 1][startColumn][maxMove - 1] == -1:
                self.fillarray(startRow - 1, startColumn, maxMove - 1)
            count += self.container[startRow - 1][startColumn][maxMove - 1]
        if startColumn > 0:
            if self.container[startRow][startColumn - 1][maxMove - 1] == -1:
                self.fillarray(startRow, startColumn - 1, maxMove - 1)
            count += self.container[startRow][startColumn - 1][maxMove - 1]
        self.container[startRow][startColumn][maxMove] = count % self.mod
