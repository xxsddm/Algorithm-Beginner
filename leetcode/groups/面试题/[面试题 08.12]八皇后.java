//设计一种算法，打印 N 皇后在 N × N 棋盘上的各种摆法，其中每个皇后都不同行、不同列，也不在对角线上。这里的“对角线”指的是所有的对角线，不只是平分整
//个棋盘的那两条对角线。 
//
// 注意：本题相对原题做了扩展 
//
// 示例: 
//
//  输入：4
// 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
// 解释: 4 皇后问题存在如下两个不同的解法。
//[
// [".Q..",  // 解法 1
//  "...Q",
//  "Q...",
//  "..Q."],
//
// ["..Q.",  // 解法 2
//  "Q...",
//  "...Q",
//  ".Q.."]
//]
// 
// Related Topics 数组 回溯 👍 114 👎 0

import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int n;
    StringBuilder origin = new StringBuilder();
    List<List<String>> ans = new LinkedList<>();

    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        for (int i = 0; i < n; i++) {
            origin.append('.');
        }
        backtrack(0, 0, 0, 0, new LinkedList<>());
        return ans;
    }

    // 标记用过的列, 主对角线, 副对角线
    void backtrack(int row, int usedCol, int usedDiag1, int usedDiag2, LinkedList<String> container) {
        if (row == n) {
            ans.add(new LinkedList<>(container));
            return;
        }
        for (int col = 0, move = 1; col < n; col++, move <<= 1) {
            if ((move & usedCol) != 0) {
                continue;
            }
            int line1 = 1 << (row - col + n);
            if ((usedDiag1 & line1) != 0) {
                continue;
            }
            int line2 = 1 << (row + col + n);
            if ((usedDiag2 & line2) != 0) {
                continue;
            }
            origin.setCharAt(col, 'Q');
            container.add(origin.toString());
            origin.setCharAt(col, '.');
            backtrack(row + 1, usedCol ^ move,
                    usedDiag1 ^ line1, usedDiag2 ^ line2, container);
            container.pollLast();
        }
    }
}

//leetcode submit region end(Prohibit modification and deletion)
