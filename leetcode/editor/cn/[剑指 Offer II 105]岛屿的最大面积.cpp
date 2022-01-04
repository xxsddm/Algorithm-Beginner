//给定一个由 0 和 1 组成的非空二维数组 grid ，用来表示海洋岛屿地图。 
//
// 一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。你可以假设 grid 的四个边缘都被 
//0（代表水）包围着。 
//
// 找到给定的二维数组中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。 
//
// 
//
// 示例 1: 
//
// 
//
// 
//输入: grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1
//,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0
//,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
//输出: 6
//解释: 对于上面这个给定矩阵应返回 6。注意答案不应该是 11 ，因为岛屿只能包含水平或垂直的四个方向的 1 。 
//
// 示例 2: 
//
// 
//输入: grid = [[0,0,0,0,0,0,0,0]]
//输出: 0 
//
// 
//
// 提示： 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 50 
// grid[i][j] is either 0 or 1 
// 
//
// 
//
// 注意：本题与主站 695 题相同： https://leetcode-cn.com/problems/max-area-of-island/ 
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 矩阵 👍 3 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class UF {  // 并查集测试(DFS, BFS见java)
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
    int maxAreaOfIsland(vector<vector<int>>& grid) {
        bool island = false;
        numRow = grid.size();
        numCol = grid[0].size();
        UF uf(numRow * numCol);
        for (int row = 0; row < numRow; row++) {
            for (int col = 0; col < numCol; col++) {
                if (grid[row][col] == 1) {
                    island = true;
                    if (row > 0 && grid[row - 1][col] == 1) {
                        uf.merge(getIndex(row, col), getIndex(row - 1, col));
                    }
                    if (col > 0 && grid[row][col - 1] == 1) {
                        uf.merge(getIndex(row, col), getIndex(row, col - 1));
                    }
                }
            }
        }
        if (!island) {
            return 0;
        }
        int square = 0;
        for (int& i : uf.size) {
            square = max(square, i);
        }
        return square;
    }

    int getIndex(int row, int col) const {
        return (row * numCol) + col;
    }
};

//leetcode submit region end(Prohibit modification and deletion)
