//「推箱子」是一款风靡全球的益智小游戏，玩家需要将箱子推到仓库中的目标位置。 
//
// 游戏地图用大小为 n * m 的网格 grid 表示，其中每个元素可以是墙、地板或者是箱子。 
//
// 现在你将作为玩家参与游戏，按规则将箱子 'B' 移动到目标位置 'T' ： 
//
// 
// 玩家用字符 'S' 表示，只要他在地板上，就可以在网格中向上、下、左、右四个方向移动。 
// 地板用字符 '.' 表示，意味着可以自由行走。 
// 墙用字符 '#' 表示，意味着障碍物，不能通行。 
// 箱子仅有一个，用字符 'B' 表示。相应地，网格上有一个目标位置 'T'。 
// 玩家需要站在箱子旁边，然后沿着箱子的方向进行移动，此时箱子会被移动到相邻的地板单元格。记作一次「推动」。 
// 玩家无法越过箱子。 
// 
//
// 返回将箱子推到目标位置的最小 推动 次数，如果无法做到，请返回 -1。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：grid = [["#","#","#","#","#","#"],
//             ["#","T","#","#","#","#"],
//             ["#",".",".","B",".","#"],
//             ["#",".","#","#",".","#"],
//             ["#",".",".",".","S","#"],
//             ["#","#","#","#","#","#"]]
//输出：3
//解释：我们只需要返回推箱子的次数。 
//
// 示例 2： 
//
// 输入：grid = [["#","#","#","#","#","#"],
//             ["#","T","#","#","#","#"],
//             ["#",".",".","B",".","#"],
//             ["#","#","#","#",".","#"],
//             ["#",".",".",".","S","#"],
//             ["#","#","#","#","#","#"]]
//输出：-1
// 
//
// 示例 3： 
//
// 输入：grid = [["#","#","#","#","#","#"],
//             ["#","T",".",".","#","#"],
//             ["#",".","#","B",".","#"],
//             ["#",".",".",".",".","#"],
//             ["#",".",".",".","S","#"],
//             ["#","#","#","#","#","#"]]
//输出：5
//解释：向下、向左、向左、向上再向上。
// 
//
// 示例 4： 
//
// 输入：grid = [["#","#","#","#","#","#","#"],
//             ["#","S","#",".","B","T","#"],
//             ["#","#","#","#","#","#","#"]]
//输出：-1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= grid.length <= 20 
// 1 <= grid[i].length <= 20 
// grid 仅包含字符 '.', '#', 'S' , 'T', 以及 'B'。 
// grid 中 'S', 'B' 和 'T' 各只能出现一个。 
// 
// Related Topics 广度优先搜索 数组 矩阵 堆（优先队列） 👍 68 👎 0


import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int numRow, numCol;
    int[] dirs = {-1, 0, 1, 0, -1};

    public int minPushBox(char[][] grid) {  // BFS
        numRow = grid.length;
        numCol = grid[0].length;
        int ans = 0;
        boolean[][][] visited = new boolean[numRow][numCol][4]; // 保存箱子已达位置和推到该位置时移动方向
        int[] start = new int[4];
        LinkedList<int[]> container = new LinkedList<>();   // 保存箱子和玩家位置
        for (int row = 0; row < numRow; row++) {
            for (int col = 0; col < numCol; col++) {
                if (grid[row][col] == 'B') {
                    start[0] = row;
                    start[1] = col;
                } else if (grid[row][col] == 'S') {
                    start[2] = row;
                    start[3] = col;
                }
            }
        }
        container.add(start);
        while (!container.isEmpty()) {
            ans++;
            int size = container.size();
            for (int i = 0; i < size; i++) {
                int[] cur = container.pollFirst();
                int row = cur[0], col = cur[1];
                boolean[] reachable = judge(cur[0], cur[1], cur[2], cur[3], grid);
                for (int j = 0; j < 4; j++) {
                    int nextRow = row + dirs[j], nextCol = col + dirs[j + 1];
                    // 相反位置即推动时玩家的位置,必须为可达点(构建reachable时已检验)
                    // int pushRow = row - dirs[j], pushCol = col - dirs[j + 1];
                    if (check(nextRow, nextCol) &&
                            !visited[nextRow][nextCol][j] &&
                            grid[nextRow][nextCol] != '#' &&
                            reachable[j]) {
                        if (grid[nextRow][nextCol] == 'T') {
                            return ans;
                        }
                        container.add(new int[] {nextRow, nextCol, row, col});
                        visited[nextRow][nextCol][j] = true;
                    }
                }
            }
        }
        return -1;
    }

    // 判断箱子四个相邻方向是否可达
    boolean[] judge(int boxRow, int boxCol, int startRow, int startCol, char[][] grid) {
        int count = 0;
        boolean[][] visited = new boolean[numRow][numCol];
        boolean[] ans = new boolean[4]; // 从第i个方向推,该方向位置是否可达
        visited[startRow][startCol] = true;
        LinkedList<int[]> container = new LinkedList<>();
        container.add(new int[] {startRow, startCol});
        for (int i = 0; i < 4; i++) {   // 先排除不可能情况
            int prevRow = boxRow - dirs[i], prevCol = boxCol - dirs[i + 1];
            if (!check(prevRow, prevCol) || grid[prevRow][prevCol] == '#') {
                count++;
            }
        }
        while (!container.isEmpty() && count < 4) {   // BFS检查可达点
            int size = container.size();
            for (int i = 0; i < size && count < 4; i++) {
                int[] cur = container.pollFirst();
                int row = cur[0], col = cur[1];
                for (int j = 0; j < 4; j++) {
                    int nextRow = row + dirs[j], nextCol = col + dirs[j + 1];
                    if (nextRow == boxRow && nextCol == boxCol) {
                        ans[j] = true;
                        continue;
                    }
                    if (check(nextRow, nextCol) &&
                            !visited[nextRow][nextCol] &&
                            grid[nextRow][nextCol] != '#') {
                        visited[nextRow][nextCol] = true;
                        container.add(new int[] {nextRow, nextCol});
                    }
                }
            }
        }
        return ans;
    }

    boolean check(int row, int col) {   // 检查索引是否合法
        return row >= 0 && row < numRow && col >= 0 && col < numCol;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
