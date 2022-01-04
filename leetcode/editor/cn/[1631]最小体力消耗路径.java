//你准备参加一场远足活动。给你一个二维 rows x columns 的地图 heights ，其中 heights[row][col] 表示格子 (row,
// col) 的高度。一开始你在最左上角的格子 (0, 0) ，且你希望去最右下角的格子 (rows-1, columns-1) （注意下标从 0 开始编号）。你
//每次可以往 上，下，左，右 四个方向之一移动，你想要找到耗费 体力 最小的一条路径。 
//
// 一条路径耗费的 体力值 是路径上相邻格子之间 高度差绝对值 的 最大值 决定的。 
//
// 请你返回从左上角走到右下角的最小 体力消耗值 。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：heights = [[1,2,2],[3,8,2],[5,3,5]]
//输出：2
//解释：路径 [1,3,5,3,5] 连续格子的差值绝对值最大为 2 。
//这条路径比路径 [1,2,2,2,5] 更优，因为另一条路径差值最大值为 3 。
// 
//
// 示例 2： 
//
// 
//
// 
//输入：heights = [[1,2,3],[3,8,4],[5,3,5]]
//输出：1
//解释：路径 [1,2,3,4,5] 的相邻格子差值绝对值最大为 1 ，比路径 [1,3,5,3,5] 更优。
// 
//
// 示例 3： 
//
// 
//输入：heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
//输出：0
//解释：上图所示路径不需要消耗任何体力。
// 
//
// 
//
// 提示： 
//
// 
// rows == heights.length 
// columns == heights[i].length 
// 1 <= rows, columns <= 100 
// 1 <= heights[i][j] <= 10⁶ 
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 二分查找 矩阵 堆（优先队列） 👍 233 👎 0


import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {    // 测试路径压缩快速合并并查集
    int numRow, numCol;

    public int minimumEffortPath(int[][] heights) {
        numRow = heights.length;
        numCol = heights[0].length;
        CompressedQuickUnion unionFind = new CompressedQuickUnion(numRow * numCol);
        PriorityQueue<int[]> edges = new PriorityQueue<>((o1, o2) -> (o1[2] - o2[2]));
        for (int row = 0; row < numRow; row++) {    // 每个节点(非右, 下边)加入向右, 下的边(避免重复)
            for (int col = 0; col < numCol; col++) {
                if (row < numRow - 1) { // 向下连接
                    edges.add(new int[] {getIndex(row, col), getIndex(row + 1, col),
                            Math.abs(heights[row + 1][col] - heights[row][col])});
                }
                if (col < numCol - 1) { // 向右连接
                    edges.add(new int[] {getIndex(row, col), getIndex(row, col + 1),
                            Math.abs(heights[row][col] - heights[row][col + 1])});
                }
            }
        }
        int ans = 0;
        while (!unionFind.connected(0, numRow * numCol - 1)) {
            int[] edge = edges.poll();
            unionFind.union(edge[0], edge[1]);
            ans = edge[2];
        }
        return ans;
    }

    private int getIndex(int row, int col) {
        return row * numCol + col;
    }
}

public class CompressedQuickUnion {
    int count;
    int[] parent;      // 当前索引 -> 相应父节点的索引
    int[] size;

    public CompressedQuickUnion(int num) {
        count = num;
        parent = new int[num];
        size = new int[num];
        for (int i = 0; i < num; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int find(int idx) {  // 找到节点所在集合的根节点
        while (idx != parent[parent[idx]]) {
            parent[idx] = parent[parent[idx]];  // 压缩路径(且修改父节点)
            idx = parent[idx];
        }

//         QuickUnion(未压缩路径版本)
//         while (idx != parent[idx]) {
//             idx = parent[idx];
//         }

        return idx;
    }

    public void union(int idx1, int idx2) {
        int set1 = find(idx1), set2 = find(idx2);   // 对根节点合并
        if (set1 != set2) {
            count--;
        }
        else {
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

    public boolean connected(int idx1, int idx2) {
        return find(idx1) == find(idx2);
    }

    public int count() {
        return count;
    }

    public int size(int idx) {
        return size[find(idx)];
    }
}

//leetcode submit region end(Prohibit modification and deletion)
