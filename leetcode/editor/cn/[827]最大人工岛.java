//给你一个大小为 n x n 二进制矩阵 grid 。最多 只能将一格 0 变成 1 。 
//
// 返回执行此操作后，grid 中最大的岛屿面积是多少？ 
//
// 岛屿 由一组上、下、左、右四个方向相连的 1 形成。 
//
// 
//
// 示例 1: 
//
// 
//输入: grid = [[1, 0], [0, 1]]
//输出: 3
//解释: 将一格0变成1，最终连通两个小岛得到面积为 3 的岛屿。
// 
//
// 示例 2: 
//
// 
//输入: grid = [[1, 1], [1, 0]]
//输出: 4
//解释: 将一格0变成1，岛屿的面积扩大为 4。 
//
// 示例 3: 
//
// 
//输入: grid = [[1, 1], [1, 1]]
//输出: 4
//解释: 没有0可以让我们变成1，面积依然为 4。 
//
// 
//
// 提示： 
//
// 
// n == grid.length 
// n == grid[i].length 
// 1 <= n <= 500 
// grid[i][j] 为 0 或 1 
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 矩阵 👍 117 👎 0

import java.util.Arrays;
import java.util.HashSet;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int numRow, numCol;
    int[] root, size, dirs = {1, 0, -1, 0, 1};

    public int largestIsland(int[][] grid) {    // 并查集
        int ans = 0;
        numRow = grid.length;
        numCol = grid[0].length;
        root = new int[numRow * numCol];
        size = new int[numRow * numCol];
        Arrays.fill(size, 1);
        for (int i = 0; i < numRow; i++) {
            for (int j = 0; j < numCol; j++) {
                int idx = loc2Idx(i, j);
                root[idx] = idx;
                if (grid[i][j] == 1) {
                    if (i > 0 && grid[i - 1][j] == 1) {
                        merge(idx, loc2Idx(i - 1, j));
                    }
                    if (j > 0 && grid[i][j - 1] == 1) {
                        merge(idx, loc2Idx(i, j - 1));
                    }
                }
            }
        }
        for (int i = 0; i < numRow; i++) {
            for (int j = 0; j < numCol; j++) {
                if (grid[i][j] == 1) {
                    ans = Math.max(ans, size[find(loc2Idx(i, j))]);
                }
            }
        }
        if (ans == 0) { // 若当前最大岛屿面积为0,则无需判断
            return 1;
        }
        // 考虑在非岛屿点添加(取0的点)
        for (int row = 0; row < numRow; row++) {
            for (int col = 0; col < numCol; col++) {
                if (grid[row][col] == 1) {
                    continue;
                }
                // 保存四个相邻方向不同岛屿的根节点(根节点不同则属于不同岛屿)
                HashSet<Integer> next = new HashSet<>();
                for (int k = 0; k < 4; k++) { // 考虑四个相邻方向
                    int nextRow = row + dirs[k], nextCol = col + dirs[k + 1];
                    if (nextRow >= 0 && nextRow < numRow &&
                            nextCol >= 0 && nextCol < numCol &&
                            grid[nextRow][nextCol] == 1) {
                        next.add(find(loc2Idx(nextRow, nextCol)));
                    }
                }
                if (next.isEmpty()) {
                    continue;
                }
                int temp = 1;
                for (int nextIdx: next) {
                    temp += size[nextIdx];
                }
                ans = Math.max(ans, temp);
            }
        }
        return ans;
    }

    void merge(int idx1, int idx2) {
        int root1 = find(idx1), root2 = find(idx2);
        if (root1 == root2) {
            return;
        }
        if (size[root1] <= size[root2]) {
            root[root1] = root2;
            size[root2] += size[root1];
        } else {
            root[root2] = root1;
            size[root1] += size[root2];
        }
    }

    int find(int idx) {
        while (idx != root[idx]) {
            root[idx] = root[root[idx]];
            idx = root[idx];
        }
        return idx;
    }

    int loc2Idx(int row, int col) { // 二维转换为一维
        return numCol * row + col;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
