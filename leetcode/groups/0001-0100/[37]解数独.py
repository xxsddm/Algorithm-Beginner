# 编写一个程序，通过填充空格来解决数独问题。 
# 
#  数独的解法需 遵循如下规则： 
# 
#  
#  数字 1-9 在每一行只能出现一次。 
#  数字 1-9 在每一列只能出现一次。 
#  数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图） 
#  
# 
#  数独部分空格内已填入了数字，空白格用 '.' 表示。 
# 
#  
# 
#  
#  
#  
#  示例： 
# 
#  
# 输入：board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".
# ",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".
# ","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6
# "],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[
# ".",".",".",".","8",".",".","7","9"]]
# 输出：[["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8
# "],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],[
# "4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9",
# "6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4",
# "5","2","8","6","1","7","9"]]
# 解释：输入的数独如上图所示，唯一有效的解决方案如下所示：
# 
# 
#  
# 
#  
# 
#  提示： 
# 
#  
#  board.length == 9 
#  board[i].length == 9 
#  board[i][j] 是一位数字或者 '.' 
#  题目数据 保证 输入数独仅有一个解 
#  
#  
#  
#  
#  Related Topics 数组 回溯 矩阵 👍 913 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def solveSudoku(self, board: List[List[str]]) -> None:
        """
        Do not return anything, modify board in-place instead.
        """
        rows = [0] * 9
        columns = [0] * 9
        squares = [[0] * 3 for _ in range(3)]
        self.finish = False
        for i in range(9):
            for j in range(9):
                if board[i][j] == '.':
                    continue
                temp = 1 << (ord(board[i][j]) - 49)
                rows[i] |= temp
                columns[j] |= temp
                squares[i // 3][j // 3] |= temp

        def backtrack(idx=0):
            if idx == 81:
                self.finish = True
                return
            row, column = idx // 9, idx % 9
            if board[row][column] != '.':
                backtrack(idx + 1)
                return
            for num in range(1, 10):
                temp = 1 << (num - 1)
                if (rows[row] & temp) != 0 \
                        or (columns[column] & temp) != 0 \
                        or (squares[row // 3][column // 3] & temp) != 0:
                    continue
                rows[row] |= temp
                columns[column] |= temp
                squares[row // 3][column // 3] |= temp
                board[row][column] = str(num)
                backtrack(idx + 1)
                if self.finish:
                    return
                rows[row] ^= temp
                columns[column] ^= temp
                squares[row // 3][column // 3] ^= temp
                board[row][column] = '.'

        backtrack()

# leetcode submit region end(Prohibit modification and deletion)
