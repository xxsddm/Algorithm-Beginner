//n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。 
//
// 给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。 
//
// 
//
// 
// 
// 示例 1： 
//
// 
//输入：n = 4
//输出：2
//解释：如上图所示，4 皇后问题存在两个不同的解法。
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 9 
// 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。 
// 
// 
// 
// Related Topics 回溯 👍 285 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int count = 0;
    int n;
    int usedColumn = 0;     // 用过的 列 (使用rowColumn就够了, 但可能很慢)
    int[] rowColumn;        // 用过的 行 -> 列

    public int totalNQueens(int n) {
        this.n = n;
        rowColumn = new int[n];
        backtrack(0);
        return count;
    }

    private void backtrack(int row) {
        if (row == n) {
            count++;
            return;
        }
        for (int column = 0; column < n; column++) {
            if ((usedColumn & (1 << column)) != 0) {        // 判断列是否用过
                continue;
            }

            boolean skip = false;       // 判断对角线是否用过
            for (int prevRow = 0; prevRow < row; prevRow++) {
                if (Math.abs(prevRow - row) == Math.abs(rowColumn[prevRow] - column)) {
                    skip = true;
                    break;
                }
            }
            if (skip) {
                continue;
            }

            usedColumn |= 1 << column;
            rowColumn[row] = column;
            backtrack(row + 1);
            usedColumn -= 1 << column;

        }
    }
}

//leetcode submit region end(Prohibit modification and deletion)
