//在一个 N x N 的坐标方格 grid 中，每一个方格的值 grid[i][j] 表示在位置 (i,j) 的平台高度。 
//
// 现在开始下雨了。当时间为 t 时，此时雨水导致水池中任意位置的水位为 t 。你可以从一个平台游向四周相邻的任意一个平台，但是前提是此时水位必须同时淹没这两
//个平台。假定你可以瞬间移动无限距离，也就是默认在方格内部游动是不耗时的。当然，在你游泳的时候你必须待在坐标方格里面。 
//
// 你从坐标方格的左上平台 (0，0) 出发。最少耗时多久你才能到达坐标方格的右下平台 (N-1, N-1)？ 
//
// 
//
// 示例 1: 
//
// 
//输入: [[0,2],[1,3]]
//输出: 3
//解释:
//时间为0时，你位于坐标方格的位置为 (0, 0)。
//此时你不能游向任意方向，因为四个相邻方向平台的高度都大于当前时间为 0 时的水位。
//
//等时间到达 3 时，你才可以游向平台 (1, 1). 因为此时的水位是 3，坐标方格中的平台没有比水位 3 更高的，所以你可以游向坐标方格中的任意位置
// 
//
// 示例2: 
//
// 
//输入: [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6
//]]
//输出: 16
//解释:
// 0  1  2  3  4
//24 23 22 21  5
//12 13 14 15 16
//11 17 18 19 20
//10  9  8  7  6
//
//最终的路线用加粗进行了标记。
//我们必须等到时间为 16，此时才能保证平台 (0, 0) 和 (4, 4) 是连通的
// 
//
// 
//
// 提示: 
//
// 
// 2 <= N <= 50. 
// grid[i][j] 是 [0, ..., N*N - 1] 的排列。 
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 二分查找 矩阵 堆（优先队列） 👍 187 👎 0


import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {    // 测试路径压缩快速合并并查集
    int numRow, numCol;

    public int swimInWater(int[][] grid) {
        numRow = grid.length;
        numCol = grid[0].length;
        CompressedQuickUnion unionFind = new CompressedQuickUnion(numRow * numCol);
        PriorityQueue<int[]> edges = new PriorityQueue<>((o1, o2) -> (o1[2] - o2[2]));
        for (int row = 0; row < numRow; row++) {    // 每个节点(非右, 下边)加入向右, 下的边(避免重复)
            for (int col = 0; col < numCol; col++) {
                if (row < numRow - 1) { // 向下连接
                    edges.add(new int[] {getIndex(row, col), getIndex(row + 1, col),
                            Math.max(grid[row + 1][col], grid[row][col])});
                }
                if (col < numCol - 1) { // 向右连接
                    edges.add(new int[] {getIndex(row, col), getIndex(row, col + 1),
                            Math.max(grid[row][col], grid[row][col + 1])});
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

public class CompressedQuickUnion { // 路径压缩快速合并并查集(基于算法4)
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
        while (idx != parent[idx]) {
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
