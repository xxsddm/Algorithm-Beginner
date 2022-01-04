//给你一个大小为 m x n 的网格和一个球。球的起始坐标为 [startRow, startColumn] 。你可以将球移到在四个方向上相邻的单元格内（可以
//穿过网格边界到达网格之外）。你 最多 可以移动 maxMove 次球。 
//
// 给你五个整数 m、n、maxMove、startRow 以及 startColumn ，找出并返回可以将球移出边界的路径数量。因为答案可能非常大，返回对 
//109 + 7 取余 后的结果。 
//
// 
//
// 示例 1： 
//
// 
//输入：m = 2, n = 2, maxMove = 2, startRow = 0, startColumn = 0
//输出：6
// 
//
// 示例 2： 
//
// 
//输入：m = 1, n = 3, maxMove = 3, startRow = 0, startColumn = 1
//输出：12
// 
//
// 
//
// 提示： 
//
// 
// 1 <= m, n <= 50 
// 0 <= maxMove <= 50 
// 0 <= startRow < m 
// 0 <= startColumn < n 
// 
// Related Topics 动态规划 
// 👍 147 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    final int mod = (int) 1e9 + 7;
    final int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};      // 每步可能的移动方向
    int row;
    int column;
    int[][][] container;

    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        row = m;
        column = n;
        container = new int[m][n][maxMove + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < maxMove + 1; k++) {
                    container[i][j][k] = -1;
                }
            }
        }
        return dfs(startRow, startColumn, maxMove);
    }

    private int dfs(int startRow, int startColumn, int maxMove) {
        if (startRow < 0 || startColumn < 0 || startRow >= row || startColumn >= column) {
            return 1;
        }
        if (maxMove == 0 || startRow + maxMove < row && startRow - maxMove >= 0
                && startColumn + maxMove < column && startColumn - maxMove >= 0) {
            return 0;
        }
        if (container[startRow][startColumn][maxMove] != -1) {
            return container[startRow][startColumn][maxMove];
        }
        int count = 0;
        for (int[] dir: dirs) {
            count = (count + dfs(startRow + dir[0], startColumn + dir[1], maxMove - 1)) % mod;
        }
        container[startRow][startColumn][maxMove] = count;      // 保存, 避免重复计算
        return count;
    }
}

//leetcode submit region end(Prohibit modification and deletion)


class Solution {
    final int mod = (int) 1e9 + 7;
    int[][][] container;
    int row;
    int column;

    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        if (maxMove == 0) {
            return 0;
        }
        container = new int[m][n][maxMove + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < maxMove + 1; k++) {
                    container[i][j][k] = -1;
                }
            }
        }
        row = m;
        column = n;
        int count = 0;
        for (int move = 1; move <= maxMove; move++){
            fillarray(startRow, startColumn, move);
            count = (count + container[startRow][startColumn][move]) % mod;
        }
        return count % mod;
    }

    private void fillarray(int startRow, int startColumn, int maxMove) {
        if (container[startRow][startColumn][maxMove] != -1) {      // 不是初始值-1, 则已填, 可跳过
            return;
        }
        if (maxMove == 1) {     // maxMove=1时若在边界则填入出界路径数
            if (startRow == row - 1 || startRow == 0) {
                if (startColumn == column - 1 || startColumn == 0) {
                    container[startRow][startColumn][maxMove] = 2;
                    if (column - 1 == 0) {
                        container[startRow][startColumn][maxMove]++;
                    }
                }
                else {
                    container[startRow][startColumn][maxMove] = 1;
                }
                if (row - 1 == 0) {
                    container[startRow][startColumn][maxMove]++;
                }
            }
            else if (startColumn == column - 1 || startColumn == 0) {
                container[startRow][startColumn][maxMove] = 1;
                if (column - 1 == 0) {
                    container[startRow][startColumn][maxMove]++;
                }
            }
            else {
                container[startRow][startColumn][maxMove] = 0;
            }
            return;
        }
        else if (startRow - maxMove >= 0 && startRow + maxMove <= row - 1
                && startColumn - maxMove >= 0 && startColumn + maxMove <= column - 1) {
            container[startRow][startColumn][maxMove] = 0;      // 必定无法出界的情况
            return;
        }

        int count = 0;
        if (startRow < row - 1) {       // 每步有上下左右, 4种移动方向
            if (container[startRow + 1][startColumn][maxMove - 1] == -1){
                fillarray(startRow + 1, startColumn, maxMove - 1);
            }
            count = (count + container[startRow + 1][startColumn][maxMove - 1]) % mod;
        }
        if (startColumn < column - 1) {
            if (container[startRow][startColumn + 1][maxMove - 1] == -1) {
                fillarray(startRow, startColumn + 1, maxMove - 1);
            }
            count = (count + container[startRow][startColumn + 1][maxMove - 1]) % mod;
        }
        if (startRow > 0) {
            if (container[startRow - 1][startColumn][maxMove - 1] == -1) {
                fillarray(startRow - 1, startColumn, maxMove - 1);
            }
            count = (count + container[startRow - 1][startColumn][maxMove - 1]) % mod;
        }
        if (startColumn > 0) {
            if (container[startRow][startColumn - 1][maxMove - 1] == -1) {
                fillarray(startRow, startColumn - 1, maxMove - 1);
            }
            count = (count + container[startRow][startColumn - 1][maxMove - 1]) % mod;
        }
        container[startRow][startColumn][maxMove] = count;
    }
}
