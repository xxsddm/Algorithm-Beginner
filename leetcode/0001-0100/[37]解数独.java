//编写一个程序，通过填充空格来解决数独问题。 
//
// 数独的解法需 遵循如下规则： 
//
// 
// 数字 1-9 在每一行只能出现一次。 
// 数字 1-9 在每一列只能出现一次。 
// 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图） 
// 
//
// 数独部分空格内已填入了数字，空白格用 '.' 表示。 
//
// 
//
// 
// 
// 
// 示例： 
//
// 
//输入：board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".
//",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".
//","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6
//"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[
//".",".",".",".","8",".",".","7","9"]]
//输出：[["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8
//"],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],[
//"4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9",
//"6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4",
//"5","2","8","6","1","7","9"]]
//解释：输入的数独如上图所示，唯一有效的解决方案如下所示：
//
//
// 
//
// 
//
// 提示： 
//
// 
// board.length == 9 
// board[i].length == 9 
// board[i][j] 是一位数字或者 '.' 
// 题目数据 保证 输入数独仅有一个解 
// 
// 
// 
// 
// Related Topics 数组 回溯 矩阵 👍 913 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int[] rows = new int[9];                // 行元素
    int[] columns = new int[9];             // 列元素
    int[][] squares = new int[3][3];        // 3x3方阵元素
    char[][] board;
    boolean finish = false;

    public void solveSudoku(char[][] board) {       // 位运算(hash很慢)
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                int temp = 1 << board[i][j] - 49;   // '1'=49
                rows[i] |= temp;
                columns[j] |= temp;
                squares[i / 3][j / 3] |= temp;
            }
        }
        this.board = board;
        backtrack(0);
    }

    private void backtrack(int idx) {
        if (idx == 81) {
            finish = true;
            return;
        }
        int row = idx / 9, column = idx % 9;    // 根据待填入索引计算行列索引
        if (board[row][column] != '.') {        // 当前位置为数字则跳过, 修改下一位
            backtrack(idx + 1);
            return;
        }

        for (int num = 1; num <= 9; num++) {
            int temp = 1 << (num - 1);
            if ((rows[row] & temp) != 0
                    || (columns[column] & temp) != 0
                    || (squares[row / 3][column / 3] & temp) != 0) {
                continue;
            }
            rows[row] |= temp;
            columns[column] |= temp;
            squares[row / 3][column / 3] |= temp;
            board[row][column] = (char) (num + 48);
            backtrack(idx + 1);
            if (finish) {       // 完成则直接结束, 不再修改
                return;
            }
            rows[row] ^= temp;
            columns[column] ^= temp;
            squares[row / 3][column / 3] ^= temp;
            board[row][column] = '.';
        }
    }
}

//leetcode submit region end(Prohibit modification and deletion)


class Solution {
    HashSet<Character>[] rows =(HashSet<Character>[]) new HashSet[9];               // 行元素
    HashSet<Character>[] columns =(HashSet<Character>[]) new HashSet[9];            // 列元素
    HashSet<Character>[][] squares =(HashSet<Character>[][]) new HashSet[3][3];     // 3x3方阵元素
    char[][] board;
    boolean finish = false;

    public void solveSudoku(char[][] board) {       // hash版(位运算不够用的情况再使用)
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet<>();
            columns[i] = new HashSet<>();
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                squares[i][j] = new HashSet<>();
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char temp = board[i][j];
                if (Character.isDigit(temp)) {
                    rows[i].add(temp);
                    columns[j].add(temp);
                    squares[i / 3][j / 3].add(temp);
                }
            }
        }
        this.board = board;
        backtrack(0);
    }

    private void backtrack(int idx) {
        if (idx == 81) {
            finish = true;
            return;
        }
        int row = idx / 9, column = idx % 9;    // 根据待填入索引计算行列索引
        if (board[row][column] != '.') {
            backtrack(idx + 1);
            return;
        }

        for (char num = '1'; num <= '9'; num++) {
            if (rows[row].contains(num)
                    || columns[column].contains(num)
                    || squares[row / 3][column / 3].contains(num)) {
                continue;
            }
            rows[row].add(num);
            columns[column].add(num);
            squares[row / 3][column / 3].add(num);
            board[row][column] = num;
            backtrack(idx + 1);
            if (finish) {       // 完成则直接结束, 不再修改
                return;
            }
            rows[row].remove(num);
            columns[column].remove(num);
            squares[row / 3][column / 3].remove(num);
            board[row][column] = '.';
        }
    }
}
