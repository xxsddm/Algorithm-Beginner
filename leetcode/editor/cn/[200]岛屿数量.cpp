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


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int numIslands(vector<vector<char>>& grid) {

    }
};

//leetcode submit region end(Prohibit modification and deletion)


class UF {
private:
    vector<int> parent;

public:
    vector<int> size;
    explicit UF(int num) {
        parent = vector<int>(num);
        size = vector(num, 1);
        for (int i = 0; i < num; i++) {
            parent[i] = i;
        }
    }

    int find(int idx) {
        while (idx != parent[idx]) {
            parent[idx] = parent[parent[idx]];  // 压缩路径(合并后查找高度大于1的节点则修改当前父节点指向, 直接指向根节点)
            idx = parent[idx];
        }
        return idx;
    }

    void merge(int idx1, int idx2) {
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
};

class Solution {
private:
    int numRow, numCol;

public:
    int numIslands(vector<vector<char>>& grid) {
        bool island = false;
        numRow = grid.size();
        numCol = grid[0].size();
        UF uf(numRow * numCol);
        for (int row = 0; row < numRow; row++) {
            for (int col = 0; col < numCol; col++) {
                if (grid[row][col] == '1') {
                    island = true;
                    if (row > 0 && grid[row - 1][col] == '1') {
                        uf.merge(getIndex(row, col), getIndex(row - 1, col));
                    }
                    if (col > 0 && grid[row][col - 1] == '1') {
                        uf.merge(getIndex(row, col), getIndex(row, col - 1));
                    }
                }
            }
        }
        if (!island) {
            return 0;
        }
        unordered_set<int> counter;
        for (int row = 0; row < numRow; row++) {
            for (int col = 0; col < numCol; col++) {
                if (grid[row][col] == '1') {
                    int idx = uf.find(getIndex(row, col));
                    counter.insert(idx);
                }
            }
        }
        return counter.size();
    }

    int getIndex(int row, int col) const {
        return (row * numCol) + col;
    }
};
