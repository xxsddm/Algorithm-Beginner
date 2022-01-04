//给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。 
//
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。 
//
// 
//
// 示例 1： 
//
// 
//输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = 
//"ABCCED"
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = 
//"SEE"
//输出：true
// 
//
// 示例 3： 
//
// 
//输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = 
//"ABCB"
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// m == board.length 
// n = board[i].length 
// 1 <= m, n <= 6 
// 1 <= word.length <= 15 
// board 和 word 仅由大小写英文字母组成 
// 
//
// 
//
// 进阶：你可以使用搜索剪枝的技术来优化解决方案，使其在 board 更大的情况下可以更快解决问题？ 
// Related Topics 数组 回溯 矩阵 👍 1024 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    string word;
    int numRow, numCol;
    vector<int> const dirs = {1, 0, -1, 0, 1};
    bool ans = false;

    bool exist(vector<vector<char>>& board, string word) {
        this->word = word;
        numRow = board.size();
        numCol = board[0].size();
        // 枚举所有可能的首端
        for (int row = 0; row < numRow; row++) {
            for (int col = 0; col < numCol; col++) {
                dfs(0, row, col, board);
                if (ans) {
                    return true;
                }
            }
        }
        return ans;
    }

    void dfs(int idx, int row, int col, vector<vector<char>>& board) {
        if (board[row][col] != word[idx]) {
            return;
        }
        if (idx + 1 == word.length()) {
            ans = true;
            return;
        }
        board[row][col] = ' ';  // 修改, 避免dfs重复访问
        for (int i = 0; i < 4; i++) {
            int nextRow = row + dirs[i], nextCol = col + dirs[i + 1];
            if (nextRow >= 0 && nextCol >= 0 && nextRow < numRow && nextCol < numCol) {
                dfs(idx + 1, nextRow, nextCol, board);
                if (ans) {
                    break;
                }
            }
        }
        board[row][col] = word[idx];    // 撤销修改
    }
};

//leetcode submit region end(Prohibit modification and deletion)
