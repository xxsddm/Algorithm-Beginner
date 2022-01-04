//给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。 
//
// 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。 
//
// 此外，你可以假设该网格的四条边均被水包围。 
//
// 
//
// 示例 1： 
//
// 
//输入：grid = [
//  ["1","1","1","1","0"],
//  ["1","1","0","1","0"],
//  ["1","1","0","0","0"],
//  ["0","0","0","0","0"]
//]
//输出：1
// 
//
// 示例 2： 
//
// 
//输入：grid = [
//  ["1","1","0","0","0"],
//  ["1","1","0","0","0"],
//  ["0","0","1","0","0"],
//  ["0","0","0","1","1"]
//]
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 300 
// grid[i][j] 的值为 '0' 或 '1' 
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 矩阵 👍 1316 👎 0

import java.util.HashSet;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int numIslands(char[][] grid) {

    }
}

//leetcode submit region end(Prohibit modification and deletion)


class Solution {
    int numRow, numCol;

    public int numIslands(char[][] grid) {  // 测试并查集
        numRow = grid.length;
        numCol = grid[0].length;
        UF uf = new UF(numRow * numCol);
        HashSet<Integer> counter = new HashSet<>();
        for (int row = 0; row < numRow; row++) {
            for (int col = 0; col < numCol; col++) {
                if (grid[row][col] == '1') {
                    if (row > 0 && grid[row - 1][col] == '1') {
                        uf.union(getIndex(row, col), getIndex(row - 1, col));
                    }
                    if (col > 0 && grid[row][col - 1] == '1') {
                        uf.union(getIndex(row, col), getIndex(row, col - 1));
                    }
                }
            }
        }
        for (int row = 0; row < numRow; row++) {
            for (int col = 0; col < numCol; col++) {
                if (grid[row][col] == '1') {
                    counter.add(uf.find(getIndex(row, col)));
                }
            }
        }
        return counter.size();
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
}
