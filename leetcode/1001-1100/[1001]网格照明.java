//在大小为 n x n 的网格 grid 上，每个单元格都有一盏灯，最初灯都处于 关闭 状态。 
//
// 给你一个由灯的位置组成的二维数组 lamps ，其中 lamps[i] = [rowi, coli] 表示 打开 位于 grid[rowi][coli] 
//的灯。即便同一盏灯可能在 lamps 中多次列出，不会影响这盏灯处于 打开 状态。 
//
// 当一盏灯处于打开状态，它将会照亮 自身所在单元格 以及同一 行 、同一 列 和两条 对角线 上的 所有其他单元格 。 
//
// 另给你一个二维数组 queries ，其中 queries[j] = [rowj, colj] 。对于第 j 个查询，如果单元格 [rowj, colj]
// 是被照亮的，则查询结果为 1 ，否则为 0 。在第 j 次查询之后 [按照查询的顺序] ，关闭 位于单元格 grid[rowj][colj] 上及相邻 8 个
//方向上（与单元格 grid[rowi][coli] 共享角或边）的任何灯。 
//
// 返回一个整数数组 ans 作为答案， ans[j] 应等于第 j 次查询 queries[j] 的结果，1 表示照亮，0 表示未照亮。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 5, lamps = [[0,0],[4,4]], queries = [[1,1],[1,0]]
//输出：[1,0]
//解释：最初所有灯都是关闭的。在执行查询之前，打开位于 [0, 0] 和 [4, 4] 的灯。第 0 次查询检查 grid[1][1] 是否被照亮（蓝色方框）
//。该单元格被照亮，所以 ans[0] = 1 。然后，关闭红色方框中的所有灯。
//
//第 1 次查询检查 grid[1][0] 是否被照亮（蓝色方框）。该单元格没有被照亮，所以 ans[1] = 0 。然后，关闭红色矩形中的所有灯。
//
// 
//
// 示例 2： 
//
// 
//输入：n = 5, lamps = [[0,0],[4,4]], queries = [[1,1],[1,1]]
//输出：[1,1]
// 
//
// 示例 3： 
//
// 
//输入：n = 5, lamps = [[0,0],[0,4]], queries = [[0,4],[0,1],[1,4]]
//输出：[1,1,0]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 10⁹ 
// 0 <= lamps.length <= 20000 
// 0 <= queries.length <= 20000 
// lamps[i].length == 2 
// 0 <= rowi, coli < n 
// queries[j].length == 2 
// 0 <= rowj, colj < n 
// 
// Related Topics 数组 哈希表 👍 80 👎 0

import java.util.HashMap;
import java.util.HashSet;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int[] dirs = {1, 0 , -1, 0, 1, 1, -1, -1, 1};
    long base = (long) 1e9;
    HashMap<Integer, Integer> usedRow = new HashMap<>(), usedCol = new HashMap<>(),
            usedDiag1 = new HashMap<>(), usedDiag2 = new HashMap<>();
    HashSet<Long> visited = new HashSet<>();

    public int[] gridIllumination(int n, int[][] lamps, int[][] queries) {
        int[] ans = new int[queries.length];
        for (int[] lamp : lamps) {
            int row = lamp[0], col = lamp[1];
            long key = (long) row * base + col;
            if (!visited.contains(key)) {
                visited.add(key);
                usedRow.put(row, usedRow.getOrDefault(row, 0) + 1);
                usedCol.put(col, usedCol.getOrDefault(col, 0) + 1);
                usedDiag1.put(col - row, usedDiag1.getOrDefault(col - row, 0) + 1);
                usedDiag2.put(col + row, usedDiag2.getOrDefault(col + row, 0) + 1);
            }
        }
        for (int i = 0, size = queries.length; i < size; i++) {
            ans[i] = judge(queries[i][0], queries[i][1]);
        }
        return ans;
    }

    int judge(int row, int col) {
        int ans = 1;
        if (used(row, col)) {
            if (visited.remove((long) row * base + col)) {
                remove(row, col);
            }
        } else {
            ans = 0;
        }
        for (int i = 0; i < 8; i++) {
            int nextRow = row + dirs[i], nextCol = col + dirs[i + 1];
            if (visited.remove((long) nextRow * base + nextCol)) {
                remove(nextRow, nextCol);
            }
        }
        return ans;
    }

    void remove(int row, int col) {
        if (usedRow.get(row) == 1) {
            usedRow.remove(row);
        } else {
            usedRow.put(row, usedRow.get(row) - 1);
        }
        if (usedCol.get(col) == 1) {
            usedCol.remove(col);
        } else {
            usedCol.put(col, usedCol.get(col) - 1);
        }
        if (usedDiag1.get(col - row) == 1) {
            usedDiag1.remove(col - row);
        } else {
            usedDiag1.put(col - row, usedDiag1.get(col - row) - 1);
        }
        if (usedDiag2.get(col + row) == 1) {
            usedDiag2.remove(col + row);
        } else {
            usedDiag2.put(col + row, usedDiag2.get(col + row) - 1);
        }
    }

    boolean used(int row, int col) {
        return usedRow.containsKey(row) || usedCol.containsKey(col)
                || usedDiag1.containsKey(col - row) || usedDiag2.containsKey(col + row);
    }
}

//leetcode submit region end(Prohibit modification and deletion)
