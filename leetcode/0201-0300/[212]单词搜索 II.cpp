//给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words，找出所有同时在二维网格和字典中出现的单词。 
//
// 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使
//用。 
//
// 
//
// 示例 1： 
//
// 
//输入：board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f",
//"l","v"]], words = ["oath","pea","eat","rain"]
//输出：["eat","oath"]
// 
//
// 示例 2： 
//
// 
//输入：board = [["a","b"],["c","d"]], words = ["abcb"]
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// m == board.length 
// n == board[i].length 
// 1 <= m, n <= 12 
// board[i][j] 是一个小写英文字母 
// 1 <= words.length <= 3 * 10⁴ 
// 1 <= words[i].length <= 10 
// words[i] 由小写英文字母组成 
// words 中的所有字符串互不相同 
// 
// Related Topics 字典树 数组 字符串 回溯 矩阵 👍 432 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int numRow, numCol, count = 0;
    const vector<int> dirs = {1, 0, -1, 0, 1};
    vector<vector<char>> board;
    vector<bool> visited;
    vector<string> ans;
    vector<string> findWords(vector<vector<char>>& board, vector<string>& words) {  // 暴力破解
        this->board = board;
        numRow = (int) board.size();
        numCol = (int) board[0].size();

        vector<int> counter(26);    // 计算字母表中各字母数量(通过数量排除不可能情况)
        for (int row = 0; row < numRow; row++) {
            for (int col = 0; col < numCol; col++) {
                counter[board[row][col] - 'a']++;
            }
        }

        vector<string> possible;    // 加入所有可能匹配的单词
        for (string word: words) {
            if (word.length() > numRow * numCol) {
                continue;
            }
            vector<int> tempCounter(26);
            for (int i = 0; i < word.length(); i++) {
                int idx = word[i] - 'a';
                tempCounter[idx]++;
                if (tempCounter[idx] > counter[idx]) {
                    break;
                }
                if (i == word.length() - 1) {
                    possible.push_back(word);
                }
            }
        }

        if (possible.empty()) {
            return ans;
        }

        visited = vector<bool>(possible.size());
        for (int row = 0; row < numRow; row++) {
            for (int col = 0; col < numCol; col++) {
                // 枚举所有可能的首端和未匹配的单词
                for (int ord = 0; ord < possible.size(); ord++) {
                    if (!visited[ord]) {
                        dfs(0, row, col, possible[ord], ord);
                        if (count == possible.size()) {
                            return ans;
                        }
                    }
                }
            }
        }

        return ans;
    }

    void dfs(int idx, int row, int col, string word, int ord) {
        if (board[row][col] != word[idx]) {
            return;
        }
        if (idx + 1 == word.length()) {
            ans.push_back(word);
            visited[ord] = true;
            count++;
            return;
        }
        board[row][col] = ' ';  // 修改, 避免dfs重复访问
        for (int i = 0; i < 4; i++) {
            int nextRow = row + dirs[i], nextCol = col + dirs[i + 1];
            if (nextRow >= 0 && nextCol >= 0 && nextRow < numRow && nextCol < numCol) {
                dfs(idx + 1, nextRow, nextCol, word, ord);
                if (visited[ord]) {
                    break;
                }
            }
        }
        board[row][col] = word[idx];    // 撤销修改
    }
};

//leetcode submit region end(Prohibit modification and deletion)
