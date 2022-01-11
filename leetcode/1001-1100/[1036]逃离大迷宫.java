//在一个 10⁶ x 10⁶ 的网格中，每个网格上方格的坐标为 (x, y) 。 
//
// 现在从源方格 source = [sx, sy] 开始出发，意图赶往目标方格 target = [tx, ty] 。数组 blocked 是封锁的方格列表
//，其中每个 blocked[i] = [xi, yi] 表示坐标为 (xi, yi) 的方格是禁止通行的。 
//
// 每次移动，都可以走到网格中在四个方向上相邻的方格，只要该方格 不 在给出的封锁列表 blocked 上。同时，不允许走出网格。 
//
// 只有在可以通过一系列的移动从源方格 source 到达目标方格 target 时才返回 true。否则，返回 false。 
//
// 
//
// 示例 1： 
//
// 
//输入：blocked = [[0,1],[1,0]], source = [0,0], target = [0,2]
//输出：false
//解释：
//从源方格无法到达目标方格，因为我们无法在网格中移动。
//无法向北或者向东移动是因为方格禁止通行。
//无法向南或者向西移动是因为不能走出网格。 
//
// 示例 2： 
//
// 
//输入：blocked = [], source = [0,0], target = [999999,999999]
//输出：true
//解释：
//因为没有方格被封锁，所以一定可以到达目标方格。
// 
//
// 
//
// 提示： 
//
// 
// 0 <= blocked.length <= 200 
// blocked[i].length == 2 
// 0 <= xi, yi < 10⁶ 
// source.length == target.length == 2 
// 0 <= sx, sy, tx, ty < 10⁶ 
// source != target 
// 题目数据保证 source 和 target 不在封锁列表内 
// 
// Related Topics 深度优先搜索 广度优先搜索 数组 哈希表 👍 89 👎 0

import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeSet;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int numRow = 1000000, numCol = 1000000;

    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {  // 二维离散+BFS
        if (blocked.length <= 1) {
            return true;
        }
        int[] dir = {1, 0, -1, 0, 1};
        TreeSet<Integer> rows = new TreeSet<>(), cols = new TreeSet<>();
        HashMap<Integer, Integer> row2idx = new HashMap<>(), col2idx = new HashMap<>(); // 离散化重新构建索引
        for (int[] p : blocked) {
            int row = p[0], col = p[1];
            // 加入封锁块和其四周的块
            rows.add(row);
            cols.add(col);
            for (int i = 0; i < 4; i++) {
                int nextRow = row + dir[i], nextCol = col + dir[i + 1];
                if (check(nextRow, nextCol)) {
                    rows.add(nextRow);
                    cols.add(nextCol);
                }
            }
        }
        rows.add(source[0]);
        cols.add(source[1]);
        rows.add(target[0]);
        cols.add(target[1]);
        numRow = rows.size();
        numCol = cols.size();
        boolean[][] block = new boolean[numRow][numCol], visited = new boolean[numRow][numCol];
        int idx = 0;
        for (int row : rows) {
            row2idx.put(row, idx++);
        }
        idx = 0;
        for (int col : cols) {
            col2idx.put(col, idx++);
        }
        for (int[] p : blocked) {
            block[row2idx.get(p[0])][col2idx.get(p[1])] = true;
        }
        LinkedList<int[]> container = new LinkedList<>();
        int targetx = row2idx.get(target[0]), targety = col2idx.get(target[1]);
        container.add(new int[] {row2idx.get(source[0]), col2idx.get(source[1])});
        while (!container.isEmpty()) {
            int rowIdx = container.peekFirst()[0], colIdx = container.pollFirst()[1];
            for (int i = 0; i < 4; i++) {
                int nextRowIdx = rowIdx + dir[i], nextColIdx = colIdx + dir[i + 1];
                if (!check(nextRowIdx, nextColIdx)
                        || visited[nextRowIdx][nextColIdx]
                        || block[nextRowIdx][nextColIdx]) {
                    continue;
                }
                visited[nextRowIdx][nextColIdx] = true;
                if (nextRowIdx == targetx && nextColIdx == targety) {
                    return true;
                }
                container.add(new int[] {nextRowIdx, nextColIdx});
            }
        }
        return false;
    }

    boolean check(int row, int col) {
        return row >= 0 && row < numRow && col >= 0 && col < numCol;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
