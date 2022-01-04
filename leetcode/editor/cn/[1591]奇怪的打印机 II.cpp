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


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    bool isPrintable(vector<vector<int>>& targetGrid) {
        int numRow = (int) targetGrid.size(), numCol = (int) targetGrid[0].size(), maxColor = 0;
        // 记录每个颜色的上下左右边界
        int up[60], bottom[60], left[60], right[60];
        memset(up, -1, sizeof(up));
        memset(bottom, numRow, sizeof(bottom));
        memset(left, numCol, sizeof(left));
        memset(right, -1, sizeof(right));
        for (int row = 0; row < numRow; row++) {
            for (int col = 0; col < numCol; col++) {
                int color = targetGrid[row][col] - 1;
                maxColor = max(maxColor, color);
                up[color] = max(up[color], row);
                bottom[color] = min(bottom[color], row);
                left[color] = min(left[color], col);
                right[color] = max(right[color], col);
            }
        }
        vector<int> paths[maxColor + 1];
        int8_t visited[maxColor + 1];
        memset(visited, 0, sizeof(visited));
        // 每个颜色边界中包含的其他颜色即拓扑排序中当前颜色的下一颜色
        for (int color = 0; color <= maxColor; color++) {
            long long contain = (long) 1 << color;
            for (int row = bottom[color]; row <= up[color]; row++) {
                for (int col = left[color]; col <= right[color]; col++) {
                    int nextColor = targetGrid[row][col] - 1;
                    if (contain & (1 << nextColor)) {
                        continue;
                    }
                    contain ^= 1 << nextColor;
                    paths[color].push_back(nextColor);
                }
            }
        }
        for (int color = 0; color <= maxColor; color++) {
            if (visited[color] == 0 && !dfs(color, paths, visited)) {
                return false;
            }
        }
        return true;
    }

    // dfs拓扑排序检查是否有环
    bool dfs(int node, vector<int> paths[], int8_t visited[]) {
        visited[node] = 1;
        for (int nextNode: paths[node]) {
            if (visited[nextNode] == 0 && !dfs(nextNode, paths, visited)) {
                return false;
            } else if (visited[nextNode] == 1) {
                return false;
            }
        }
        visited[node] = 2;
        return true;
    }
};

//leetcode submit region end(Prohibit modification and deletion)
