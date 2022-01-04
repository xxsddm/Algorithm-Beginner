//给你一个大小为 m x n 的整数矩阵 grid ，表示一个网格。另给你三个整数 row、col 和 color 。网格中的每个值表示该位置处的网格块的颜色
//。 
//
// 当两个网格块的颜色相同，而且在四个方向中任意一个方向上相邻时，它们属于同一 连通分量 。 
//
// 连通分量的边界 是指连通分量中的所有与不在分量中的网格块相邻（四个方向上）的所有网格块，或者在网格的边界上（第一行/列或最后一行/列）的所有网格块。 
//
// 请你使用指定颜色 color 为所有包含网格块 grid[row][col] 的 连通分量的边界 进行着色，并返回最终的网格 grid 。 
//
// 
//
// 示例 1： 
//
// 
//输入：grid = [[1,1],[1,2]], row = 0, col = 0, color = 3
//输出：[[3,3],[3,2]] 
//
// 示例 2： 
//
// 
//输入：grid = [[1,2,2],[2,3,2]], row = 0, col = 1, color = 3
//输出：[[1,3,3],[2,3,3]] 
//
// 示例 3： 
//
// 
//输入：grid = [[1,1,1],[1,1,1],[1,1,1]], row = 1, col = 1, color = 2
//输出：[[2,2,2],[2,1,2],[2,2,2]] 
//
// 
//
// 提示： 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 50 
// 1 <= grid[i][j], color <= 1000 
// 0 <= row < m 
// 0 <= col < n 
// 
//
// 
// Related Topics 深度优先搜索 广度优先搜索 数组 矩阵 👍 33 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int numRow, numCol, target;
    int[] dirs = {1, 0, -1, 0, 1};

    public int[][] colorBorder(int[][] grid, int row, int col, int color) { // DFS
        target = grid[row][col];
        if (target != color) {
            numRow = grid.length;
            numCol = grid[0].length;
            boolean[][] visited = new boolean[numRow][numCol];
            dfs(row, col, color, grid, visited);
        }
        return grid;
    }

    void dfs(int row, int col, int color, int[][] grid, boolean[][] visited) {
        visited[row][col] = true;
        for (int i = 0; i < 4; i++) {
            int nextRow = row + dirs[i], nextCol = col + dirs[i + 1];
            if (!check(nextRow, nextCol) || !visited[nextRow][nextCol] && grid[nextRow][nextCol] != target) {
                grid[row][col] = color;
            } else if (!visited[nextRow][nextCol]){
                dfs(nextRow, nextCol, color, grid, visited);
            }
        }
    }

    boolean check(int row, int col) {
        return row >= 0 && row < numRow && col >= 0 && col < numCol;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
