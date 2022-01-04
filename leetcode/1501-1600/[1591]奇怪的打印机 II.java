//给你一个奇怪的打印机，它有如下两个特殊的打印规则： 
//
// 
// 每一次操作时，打印机会用同一种颜色打印一个矩形的形状，每次打印会覆盖矩形对应格子里原本的颜色。 
// 一旦矩形根据上面的规则使用了一种颜色，那么 相同的颜色不能再被使用 。 
// 
//
// 给你一个初始没有颜色的 m x n 的矩形 targetGrid ，其中 targetGrid[row][col] 是位置 (row, col) 的颜色。
// 
//
// 如果你能按照上述规则打印出矩形 targetGrid ，请你返回 true ，否则返回 false 。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：targetGrid = [[1,1,1,1],[1,2,2,1],[1,2,2,1],[1,1,1,1]]
//输出：true
// 
//
// 示例 2： 
//
// 
//
// 输入：targetGrid = [[1,1,1,1],[1,1,3,3],[1,1,3,4],[5,5,1,4]]
//输出：true
// 
//
// 示例 3： 
//
// 输入：targetGrid = [[1,2,1],[2,1,2],[1,2,1]]
//输出：false
//解释：没有办法得到 targetGrid ，因为每一轮操作使用的颜色互不相同。 
//
// 示例 4： 
//
// 输入：targetGrid = [[1,1,1],[3,1,3]]
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// m == targetGrid.length 
// n == targetGrid[i].length 
// 1 <= m, n <= 60 
// 1 <= targetGrid[row][col] <= 60 
// 
// Related Topics 图 拓扑排序 数组 矩阵 👍 21 👎 0


import java.util.Arrays;
import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    LinkedList<Integer>[] paths;
    byte[] visited;

    public boolean isPrintable(int[][] targetGrid) {
        int numRow = targetGrid.length, numCol = targetGrid[0].length, maxColor = 0;
        // 记录每个颜色的上下左右边界
        int[] up = new int[60], bottom = new int[60], left = new int[60], right = new int[60];
        Arrays.fill(up, -1);
        Arrays.fill(bottom, numRow);
        Arrays.fill(left, numCol);
        Arrays.fill(right, -1);
        for (int row = 0; row < numRow; row++) {
            for (int col = 0; col < numCol; col++) {
                int color = targetGrid[row][col] - 1;
                maxColor = Math.max(maxColor, color);
                up[color] = Math.max(up[color], row);
                bottom[color] = Math.min(bottom[color], row);
                left[color] = Math.min(left[color], col);
                right[color] = Math.max(right[color], col);
            }
        }
        paths = (LinkedList<Integer>[]) new LinkedList[maxColor + 1];
        visited = new byte[maxColor + 1];
        // 每个颜色边界中包含的其他颜色即拓扑排序中当前颜色的下一颜色
        for (int color = 0; color <= maxColor; color++) {
            long contain = 1L << color;
            for (int row = bottom[color]; row <= up[color]; row++) {
                for (int col = left[color]; col <= right[color]; col++) {
                    int nextColor = targetGrid[row][col] - 1;
                    if ((contain & (1L << nextColor)) != 0) {
                        continue;
                    }
                    if (paths[color] == null) {
                        paths[color] = new LinkedList<>();
                    }
                    contain ^= 1L << nextColor;
                    paths[color].add(nextColor);
                }
            }
        }
        for (int color = 0; color <= maxColor; color++) {
            if (visited[color] == 0 && !dfs(color)) {
                return false;
            }
        }
        return true;
    }

    // dfs拓扑排序检查是否有环
    boolean dfs(int node) {
        if (paths[node] == null) {
            visited[node] = 2;
            return true;
        }
        visited[node] = 1;
        for (int nextNode: paths[node]) {
            if (visited[nextNode] == 1 || visited[nextNode] == 0 && !dfs(nextNode)) {
                return false;
            }
        }
        visited[node] = 2;
        return true;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
