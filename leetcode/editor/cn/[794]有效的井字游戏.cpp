//用字符串数组作为井字游戏的游戏板 board。当且仅当在井字游戏过程中，玩家有可能将字符放置成游戏板所显示的状态时，才返回 true。 
//
// 该游戏板是一个 3 x 3 数组，由字符 " "，"X" 和 "O" 组成。字符 " " 代表一个空位。 
//
// 以下是井字游戏的规则： 
//
// 
// 玩家轮流将字符放入空位（" "）中。 
// 第一个玩家总是放字符 “X”，且第二个玩家总是放字符 “O”。 
// “X” 和 “O” 只允许放置在空位中，不允许对已放有字符的位置进行填充。 
// 当有 3 个相同（且非空）的字符填充任何行、列或对角线时，游戏结束。 
// 当所有位置非空时，也算为游戏结束。 
// 如果游戏结束，玩家不允许再放置字符。 
// 
//
// 
//示例 1:
//输入: board = ["O  ", "   ", "   "]
//输出: false
//解释: 第一个玩家总是放置“X”。
//
//示例 2:
//输入: board = ["XOX", " X ", "   "]
//输出: false
//解释: 玩家应该是轮流放置的。
//
//示例 3:
//输入: board = ["XXX", "   ", "OOO"]
//输出: false
//
//示例 4:
//输入: board = ["XOX", "O O", "XOX"]
//输出: true
// 
//
// 说明: 
//
// 
// 游戏板 board 是长度为 3 的字符串数组，其中每个字符串 board[i] 的长度为 3。 
// board[i][j] 是集合 {" ", "X", "O"} 中的一个字符。 
// 
// Related Topics 数组 字符串 👍 40 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    bool validTicTacToe(vector<string>& board) {    // 模拟
        int countX = 0, countO = 0;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == 'X') {
                    countX++;
                } else if (board[row][col] == 'O') {
                    countO++;
                }
            }
        }
        if (countX > countO + 1 || countX < countO) {
            return false;
        }
        // 根据数量判断最后一个填入的元素
        // 由于之前的数量判定,同元素不可能填满3个行,列或对角线
        // 只需要判断非最后填入元素，该类别不可出现填满的行,列或对角线
        if (countX > countO) {
            return countO < 3 || check(board, 'O');
        }
        return countX < 3 || check(board, 'X');
    }

    bool check(vector<string>& board, char target) {
        for (int row = 0; row < 3; row++) { // 检查行
            for (int col = 0; col < 3; col++) {
                if (board[row][col] != target) {
                    break;
                }
                if (col == 2) {
                    return false;
                }
            }
        }
        for (int col = 0; col < 3; col++) { // 检查列
            for (int row = 0; row < 3; row++) {
                if (board[row][col] != target) {
                    break;
                }
                if (row == 2) {
                    return false;
                }
            }
        }
        for (int i = 0; i < 3; i++) {   // 检查主对角线
            if (board[i][i] != target) {
                break;
            }
            if (i == 2) {
                return false;
            }
        }
        for (int i = 0; i < 3; i++) {   // 检查副对角线
            if (board[i][2 - i] != target) {
                break;
            }
            if (i == 2) {
                return false;
            }
        }
        return true;
    }
};

//leetcode submit region end(Prohibit modification and deletion)

class Solution {
public:
    int x = 0, o = 0, total = 0;
    string target;
    unordered_set<string> container;

    bool validTicTacToe(vector<string>& board) {    // 回溯
        int countX = 0, countO = 0;
        for (string &b: board) {
            target += b;
        }
        for (int i = 0, temp = 1; i < 9; i++, temp <<= 1) {
            if (target[i] == 'X') {
                x ^= temp;
                countX++;
            } else if (target[i] == 'O') {
                o ^= temp;
                countO++;
            }
        }
        if (countO > countX || countX > countO + 1) {
            return false;
        }
        total = countX + countO;
        if (total == 0) {
            return true;
        }
        return backtrack(1);
    }

    bool backtrack(int count) {
        if (count == total) {
            return true;
        }
        if (container.count(target)) {
            return false;
        }
        int *letter = count & 1 ? &x : &o;
        char character = count & 1 ? 'X' : 'O';
        for (int i = 0, temp = 1; i < 9; i++, temp <<= 1) {
            if ((*letter & temp) == 0) {
                continue;
            }
            *letter ^= temp;
            target[i] = character;
            bool skipRow = true, skipCol = true, skipDiag1 = false, skipDiag2 = false;
            int row = i / 3, col = i % 3;
            for (int r = 0; r < 3; r++) {   // 检查同列
                if (target[r * 3 + col] != character) {
                    skipRow = false;
                    break;
                }
            }
            if (!skipRow) {
                for (int c = 0; c < 3; c++) {   // 检查同列
                    if (target[row * 3 + c] != character) {
                        skipCol = false;
                        break;
                    }
                }
            }
            if (!skipRow && !skipCol && row == col) {
                skipDiag1 = true;
                for (int j = 0; j < 3; j++) {   // 检查主对角线
                    if (target[j * 4] != character) {
                        skipDiag1 = false;
                        break;
                    }
                }
            }
            if (!skipRow && !skipCol && row + col == 2) {
                skipDiag2 = true;
                for (int r = 0; r < 3; r++) {   // 检查副对角线
                    if (target[r * 2 + 2] != character) {
                        skipDiag2 = false;
                        break;
                    }
                }
            }
            if (!skipRow && !skipCol && !skipDiag1 && !skipDiag2) {
                if (backtrack(count + 1)) {
                    return true;
                }
            }
            *letter ^= temp;
            target[i] = ' ';
        }
        container.insert(target);
        return false;
    }
};
