//给定一个包含了一些 0 和 1 的非空二维数组 grid 。 
//
// 一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。你可以假设 grid 的四个边缘都被 
//0（代表水）包围着。 
//
// 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为 0 。) 
//
// 
//
// 示例 1: 
//
// [[0,0,1,0,0,0,0,1,0,0,0,0,0],
// [0,0,0,0,0,0,0,1,1,1,0,0,0],
// [0,1,1,0,1,0,0,0,0,0,0,0,0],
// [0,1,0,0,1,1,0,0,1,0,1,0,0],
// [0,1,0,0,1,1,0,0,1,1,1,0,0],
// [0,0,0,0,0,0,0,0,0,0,1,0,0],
// [0,0,0,0,0,0,0,1,1,1,0,0,0],
// [0,0,0,0,0,0,0,1,1,0,0,0,0]]
// 
//
// 对于上面这个给定矩阵应返回 6。注意答案不应该是 11 ，因为岛屿只能包含水平或垂直的四个方向的 1 。 
//
// 示例 2: 
//
// [[0,0,0,0,0,0,0,0]] 
//
// 对于上面这个给定的矩阵, 返回 0。 
//
// 
//
// 注意: 给定的矩阵grid 的长度和宽度都不超过 50。 
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 矩阵 👍 550 👎 0


import java.util.LinkedList;
import java.util.Queue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int numRow, numCol;

    public int maxAreaOfIsland(int[][] grid) {  // 测试并查集
        boolean island = false;
        numRow = grid.length;
        numCol = grid[0].length;
        UF uf = new UF(numRow * numCol);
        for (int row = 0; row < numRow; row++) {
            for (int col = 0; col < numCol; col++) {
                if (grid[row][col] == 1) {
                    island = true;
                    if (row > 0 && grid[row - 1][col] == 1) {
                        uf.union(getIndex(row, col), getIndex(row - 1, col));
                    }
                    if (col > 0 && grid[row][col - 1] == 1) {
                        uf.union(getIndex(row, col), getIndex(row, col - 1));
                    }
                }
            }
        }
        if (!island) {
            return 0;
        }
        int square = 0;
        for (int i = 0; i < uf.size.length; i++) {
            square = Math.max(square, uf.size[i]);
        }
        return square;
    }

    private int getIndex(int row, int col) {
        return (row * numCol) + col;
    }

}

class UF {
    private int[] parent;      // 当前索引 -> 相应父节点的索引
    public int[] size;

    public UF(int num) {
        parent = new int[num];
        size = new int[num];
        for (int i = 0; i < num; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int find(int idx) {  // 找到节点所在集合的根节点
        while (idx != parent[idx]) {
            parent[idx] = parent[parent[idx]];  // 压缩路径(合并后查找高度大于1的节点则修改当前父节点指向, 直接指向根节点)
            idx = parent[idx];
        }
        return idx;
    }

    public void union(int idx1, int idx2) {
        int set1 = find(idx1), set2 = find(idx2);   // 对根节点合并
        if (set1 == set2) {
            return;
        }
        if (size[set1] < size[set2]) {  // 小集合连接到大集合作为子树
            parent[set1] = set2;
            size[set2] += size[set1];
        }
        else {
            parent[set2] = set1;
            size[set1] += size[set2];
        }
    }

    public int size(int idx) {
        return size[find(idx)];
    }
}

//leetcode submit region end(Prohibit modification and deletion)

class Solution {    // DFS(也可以用栈实现和DFS一样的效果)
    private final int[] dirs = {1, 0, -1, 0, 1};    // 4种移动方向
    private int numRow, numCol;
    private int[][] nums;

    public int maxAreaOfIsland(int[][] grid) {
        int ans = 0;
        numRow = grid.length;
        numCol = grid[0].length;
        nums = grid;
        for (int i = 0; i < numRow; i++) {
            for (int j = 0; j < numCol; j++) {
                ans = Math.max(ans, dfs(i, j));
            }
        }
        return ans;
    }

    private int dfs(int row, int col) {
        if (row < 0 || col < 0 || row == numRow || col == numCol || nums[row][col] == 0) {
            return 0;
        }
        nums[row][col] = 0; // 访问过的重置为0(后面访问直接返回)
        int ans = 1;
        for (int idx = 0; idx < 4; idx++) {
            ans += dfs(row + dirs[idx], col + dirs[idx + 1]);
        }
        return ans;
    }
}

class Solution {
    public int maxAreaOfIsland(int[][] grid) {  // BFS
        int numRow = grid.length, numCol = grid[0].length, maxSquare = 0;
        int[] dirs = {1, 0, -1, 0, 1};  // 4种移动方向
        for (int row = 0; row < numRow; row++) {
            for (int col = 0; col < numCol; col++) {
                int square = 0;
                // 每个点生成一个待访问位置的队列
                Queue<int[]> locs = new LinkedList<>();
                locs.add(new int[] {row, col});
                while (!locs.isEmpty()) {
                    int curRow = locs.peek()[0], curCol = locs.poll()[1];
                    if (curRow < 0 || curCol < 0 || curRow == numRow || curCol == numCol || grid[curRow][curCol] == 0) {
                        continue;
                    }
                    square++;
                    grid[curRow][curCol] = 0;   // 访问过的重置为0(后面访问直接返回)
                    for (int idx = 0; idx < 4; idx++) {
                        locs.add(new int[] {curRow + dirs[idx], curCol + dirs[idx + 1]});
                    }
                }
                maxSquare = Math.max(maxSquare, square);
            }
        }
        return maxSquare;
    }
}
