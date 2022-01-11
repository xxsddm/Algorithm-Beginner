//在一个 10⁶ x 10⁶ 的网格中，每个网格上方格的坐标为 (x, y) 。 
//
// 现在从源方格 source = [sx, sy] 开始出发，意图赶往目标方格 target = [tx, ty] 。数组 blocked 是封锁的方格列表
//，其中每个 blocked[i] = [xi, yi] 表示坐标为 (xi, yi) 的方格是禁止通行的。 
//
// 每次移动，都可以走到网格中在四个方向上相邻的方格，只要该方格 不 在给出的封锁列表 blocked 上。同时，不允许走出网格。 
//
// 只有在可以通过一系列的移动从源方格 source 到达目标方格 target 时才返回 true。否则，返回 false。 
//
// 
//
// 示例 1： 
//
// 
//输入：blocked = [[0,1],[1,0]], source = [0,0], target = [0,2]
//输出：false
//解释：
//从源方格无法到达目标方格，因为我们无法在网格中移动。
//无法向北或者向东移动是因为方格禁止通行。
//无法向南或者向西移动是因为不能走出网格。 
//
// 示例 2： 
//
// 
//输入：blocked = [], source = [0,0], target = [999999,999999]
//输出：true
//解释：
//因为没有方格被封锁，所以一定可以到达目标方格。
// 
//
// 
//
// 提示： 
//
// 
// 0 <= blocked.length <= 200 
// blocked[i].length == 2 
// 0 <= xi, yi < 10⁶ 
// source.length == target.length == 2 
// 0 <= sx, sy, tx, ty < 10⁶ 
// source != target 
// 题目数据保证 source 和 target 不在封锁列表内 
// 
// Related Topics 深度优先搜索 广度优先搜索 数组 哈希表 👍 89 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int numRow = 1000000, numCol = 1000000;

    bool isEscapePossible(vector<vector<int>>& blocked, vector<int>& source, vector<int>& target) { // 二维离散+BFS
        if (blocked.size() <= 1) {
            return true;
        }
        int dir[5] = {1, 0, -1, 0, 1};
        vector<int> rows, cols;
        unordered_map<int, int> row2idx, col2idx;   // 离散化重新构建索引体系
        for (vector<int> &p : blocked) {
            int row = p[0], col = p[1];
            // 加入封锁块和其四周的块
            rows.push_back(row), cols.push_back(col);
            for (int i = 0; i < 4; i++) {
                int nextRow = row + dir[i], nextCol = col + dir[i + 1];
                if (check(nextRow, nextCol)) {
                    rows.push_back(nextRow), cols.push_back(nextCol);
                }
            }
        }
        rows.push_back(source[0]), cols.push_back(source[1]);
        rows.push_back(target[0]), cols.push_back(target[1]);
        sort(rows.begin(), rows.end());
        sort(cols.begin(), cols.end());
        rows.erase(unique(rows.begin(), rows.end()), rows.end());
        cols.erase(unique(cols.begin(), cols.end()), cols.end());
        numRow = (int) rows.size(), numCol = (int) cols.size();
        bool block[numRow][numCol], visited[numRow][numCol];
        memset(block, false, sizeof(block));
        memset(visited, false, sizeof(visited));
        for (int i = 0; i < numRow; i++) {
            row2idx[rows[i]] = i;
        }
        for (int i = 0; i < numCol; i++) {
            col2idx[cols[i]] = i;
        }

        // 用新的索引体系BFS
        for (vector<int> &p : blocked) {
            block[row2idx[p[0]]][col2idx[p[1]]] = true;
        }
        queue<pair<int, int>> container;
        int targetx = row2idx[target[0]], targety = col2idx[target[1]];
        container.emplace(row2idx[source[0]], col2idx[source[1]]);
        while (!container.empty()) {
            int rowIdx = container.front().first, colIdx = container.front().second;
            container.pop();
            for (int i = 0; i < 4; i++) {
                int nextRowIdx = rowIdx + dir[i], nextColIdx = colIdx + dir[i + 1];
                if (!check(nextRowIdx, nextColIdx)
                    || visited[nextRowIdx][nextColIdx]
                    || block[nextRowIdx][nextColIdx]) {
                    continue;
                }
                visited[nextRowIdx][nextColIdx] = true;
                if (nextRowIdx == targetx && nextColIdx == targety) {
                    return true;
                }
                container.emplace(nextRowIdx, nextColIdx);
            }
        }
        return false;
    }

    bool check(int row, int col) {
        return row >= 0 && row < numRow && col >= 0 && col < numCol;
    }
};

//leetcode submit region end(Prohibit modification and deletion)

class Solution {
public:
    int limit, dir[5] = {1, 0, -1, 0, 1};
    long long move = 1000000;
    unordered_set<long long> block;

    bool isEscapePossible(vector<vector<int>>& blocked, vector<int>& source, vector<int>& target) { // BFS
        for (vector<int> &p : blocked) {
            block.insert(p[0] * move + p[1]);
        }
        if (block.size() <= 1) {
            return true;
        }
        // 包围圈内元素数量上限
        limit = (int) (block.size() * block.size()) / 2;
        int temp = check(source, target);
        if (temp == 2) {
            return true;
        }
        if (temp == 0) {
            return false;
        }
        return check(target, source) == 1;
    }

    // 通过可访问点的数量判断start是否被包围(还需考虑可直接访问另一点的情况)
    int check(const vector<int>& start, const vector<int> &other) {
        int startRow = start[0], startCol = start[1], endRow = other[0], endCol = other[1];
        unordered_set<long long> visited;
        queue<pair<int, int>> container;
        container.emplace(startRow, startCol);
        visited.insert(startRow * move + startCol);
        while (!container.empty()) {
            auto &p = container.front();
            int row = p.first, col = p.second;
            container.pop();
            for (int i = 0; i < 4; i++) {
                int nextRow = row + dir[i], nextCol = col + dir[i + 1];
                long long key = nextRow * move + nextCol;
                if (!check(nextRow, nextCol) || visited.count(key) || block.count(key)) {
                    continue;
                }
                // 考虑可直接访问另一点的情况
                if (nextRow == endRow && nextCol == endCol) {
                    return 2;
                }
                visited.insert(key);
                if (visited.size() > limit) {
                    return 1;
                }
                container.emplace(nextRow, nextCol);
            }
        }
        return 0;
    }

    bool check(int row, int col) {
        return row >= 0 && row < move && col >= 0 && col < move;
    }
};
