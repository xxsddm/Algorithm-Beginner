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


//leetcode submit region begin(Prohibit modification and deletion)
struct edge {
    int node1;
    int node2;
    int weight;

    edge(int idx1, int idx2, int w):
            node1(idx1), node2(idx2), weight(w){}

    bool operator < (edge j) const {
        return weight > j.weight; // cpp默认大根堆
    }
};

class CompressedQuickUnion {
private:
    vector<int> parent;
    vector<int> size;
    int find(int idx) { // 找到节点所在集合的根节点
        while (idx != parent[idx]) {
            parent[idx] = parent[parent[idx]];  // 压缩路径(且修改父节点)
            idx = parent[idx];
        }
        return idx;
    }

public:
    explicit CompressedQuickUnion(int num):
            parent(vector<int>(num)), size(vector<int>(num, 1)){
        for (int i = 0; i < num; i++) {
            parent[i] = i;
        }
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

    bool connected(int idx1, int idx2) {
        return find(idx1) == find(idx2);
    }
};

class Solution {
private:
    int numRow, numCol;
    int getIndex(int row, int col) {
        return row * numCol + col;
    }
public:
    int minimumEffortPath(vector<vector<int>>& heights) {
        numRow = heights.size();
        numCol = heights[0].size();
        CompressedQuickUnion unionFind(numRow * numCol);
        priority_queue<edge> edges;
        for (int row = 0; row < numRow; row++) {    // 每个节点(非右, 下边)加入向右, 下的边(避免重复)
            for (int col = 0; col < numCol; col++) {
                if (row < numRow - 1) { // 向下连接
                    edges.push(edge(getIndex(row, col), getIndex(row + 1, col),
                                    abs(heights[row + 1][col] - heights[row][col])));
                }
                if (col < numCol - 1) { // 向右连接
                    edges.push(edge(getIndex(row, col), getIndex(row, col + 1),
                                    abs(heights[row][col] - heights[row][col + 1])));
                }
            }
        }
        int ans = 0;
        while (!unionFind.connected(0, numRow * numCol - 1)) {
            edge temp = edges.top();
            edges.pop();
            unionFind.merge(temp.node1, temp.node2);
            ans = temp.weight;
        }
        return ans;
    }
};

//leetcode submit region end(Prohibit modification and deletion)
