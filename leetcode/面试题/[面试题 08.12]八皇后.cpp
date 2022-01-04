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


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int n;
    vector<vector<string>> ans;
    string origin;

    vector<vector<string>> solveNQueens(int n) {
        this->n = n;
        origin = string(n, '.');
        vector<string> container;
        backtrack(0, 0, 0, 0, container);
        return ans;
    }

    // 标记用过的列, 主对角线, 副对角线
    void backtrack(int row, int used_col, int used_diag1, int used_diag2, vector<string> &container) {
        if (row == n) {
            ans.push_back(container);
            return;
        }
        for (int col = 0, move = 1; col < n; col++, move <<= 1) {
            if (move & used_col) {
                continue;
            }
            int line1 = 1 << (row - col + n);
            if (used_diag1 & line1) {
                continue;
            }
            int line2 = 1 << (row + col + n);
            if (used_diag2 & line2) {
                continue;
            }
            origin[col] = 'Q';
            container.push_back(origin);
            origin[col] = '.';
            backtrack(row + 1, used_col ^ move, used_diag1 ^ line1, used_diag2 ^ line2, container);
            container.pop_back();
        }
    }
};

//leetcode submit region end(Prohibit modification and deletion)
