//在一个二维的花园中，有一些用 (x, y) 坐标表示的树。由于安装费用十分昂贵，你的任务是先用最短的绳子围起所有的树。只有当所有的树都被绳子包围时，花园才能
//围好栅栏。你需要找到正好位于栅栏边界上的树的坐标。 
//
// 
//
// 示例 1: 
//
// 输入: [[1,1],[2,2],[2,0],[2,4],[3,3],[4,2]]
//输出: [[1,1],[2,0],[4,2],[3,3],[2,4]]
//解释:
//
// 
//
// 示例 2: 
//
// 输入: [[1,2],[2,2],[4,2]]
//输出: [[1,2],[2,2],[4,2]]
//解释:
//
//即使树都在一条直线上，你也需要先用绳子包围它们。
// 
//
// 
//
// 注意: 
//
// 
// 所有的树应当被围在一起。你不能剪断绳子来包围树或者把树分成一组以上。 
// 输入的整数在 0 到 100 之间。 
// 花园至少有一棵树。 
// 所有树的坐标都是不同的。 
// 输入的点没有顺序。输出顺序也没有要求。 
// Related Topics 几何 数组 数学 👍 74 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int x0, y0;

    vector<vector<int>> outerTrees(vector<vector<int>>& trees) {    // Jarvis
        if (trees.size() <= 3) {
            return trees;
        }
        vector<vector<int>> ans;
        deque<vector<int>> container;
        vector<int> start = trees[0];
        for (int i = 1, size = (int) trees.size(); i < size; i++) {
            if (start[0] > trees[i][0] || start[0] == trees[i][0] && start[1] > trees[i][1]) {
                start = trees[i];
            }
        }
        ans.push_back(start);
        x0 = start[0], y0 = start[1];
        for (auto &t : trees) {
            if (t[0] != x0 || t[1] != y0) {
                container.push_back(t);
            }
        }
        bool keep = addNode(ans, container);
        container.push_back(start);
        while (keep) {
            keep = addNode(ans, container);
        }
        return ans;
    }

    bool addNode(vector<vector<int>> &ans, deque<vector<int>> &container) {
        vector<int> &prevNode = ans.back(), node = container.front();
        int dx = node[0] - prevNode[0], dy = node[1] - prevNode[1], dist = dx * dx + dy * dy;
        container.pop_front();
        for (int i = 0, size = (int) container.size(); i < size; i++) {
            vector<int> nextNode = container.front();
            container.pop_front();
            if (turnRight(prevNode, node, nextNode, dist)) {
                container.push_back(node);
                node = nextNode;
            } else {
                container.push_back(nextNode);
            }
        }
        if (node[0] == x0 && node[1] == y0) {
            return false;
        }
        ans.push_back(node);
        return true;
    }

    bool turnRight(vector<int> &prevNode, vector<int> &node, vector<int> &nextNode, int &dist) {
        int dx1 = node[0] - prevNode[0], dx2 = nextNode[0] - node[0];
        int dy1 = node[1] - prevNode[1], dy2 = nextNode[1] - node[1];
        if (dx1 * dy2 < dx2 * dy1) {
            int dx3 = nextNode[0] - prevNode[0], dy3 = nextNode[1] - prevNode[1];
            dist = dx3 * dx3 + dy3 * dy3;
            return true;
        }
        if (dx1 * dy2 == dx2 * dy1) {
            int dx3 = nextNode[0] - prevNode[0], dy3 = nextNode[1] - prevNode[1];
            int temp = dx3 * dx3 + dy3 * dy3;
            if (dist > temp && (nextNode[0] != x0 || nextNode[1] != y0)
                || node[0] == x0 && node[1] == y0) {
                dist = temp;
                return true;
            }
        }
        return false;
    }
};


//leetcode submit region end(Prohibit modification and deletion)

class Solution {
public:
    vector<vector<int>> outerTrees(vector<vector<int>>& trees) {    // 计算余弦暴力Jarvis
        int length = (int) trees.size();
        if (length <= 3) {
            return trees;
        }
        const double EPSILON = 1e-6;
        // 索引i -> trees[i]是否已加入栅栏圈
        vector<bool> used(length);
        int start = 0;
        vector<int> firstTree = trees[0];
        for (int i = 1; i < length; i++) {
            vector<int> tree = trees[i];
            if (tree[0] < firstTree[0]) {
                firstTree = tree;
                start = i;
            }
            else if (tree[0] == firstTree[0]) {
                if (tree[1] < firstTree[1]) {
                    firstTree = tree;
                    start = i;
                }
            }
        }

        // 隐式加入第一个点(左下角点一定加入)
        // 加入第二个点
        vector<int> benchmark = {0, -1};
        double maxCos = - 1;
        int minDist = INT_MAX, next = 0, count = 1;
        for (int i = 0; i < length; i++) {
            if (i == start) {
                continue;
            }
            vector<int> vector = {trees[i][0] - firstTree[0], trees[i][1] - firstTree[1]};
            double newCos = cos(benchmark, vector);
            if (abs(newCos - maxCos) < EPSILON) {
                int dist = distSquare(firstTree, trees[i]);
                if (dist < minDist) {
                    minDist = dist;
                    next = i;
                }
            }
            else if (newCos > maxCos) {
                maxCos = newCos;
                minDist = distSquare(firstTree, trees[i]);
                next = i;
            }
        }
        used[next] = true;

        // 向量1:加入的倒数第二个点指向倒数第一个点; 向量2:倒数第一个点指向正在考虑的点
        // 加入后续点(选择向量夹角余弦值最小的,若余弦相同则选择最近点)
        vector<int> last = trees[next], secondLast = trees[start];
        while (!used[start]) {
            benchmark = {last[0] - secondLast[0], last[1] - secondLast[1]};
            maxCos = -1;
            minDist = INT_MAX;
            for (int i = 0; i < length; i++) {
                if (used[i]) {
                    continue;
                }
                vector<int> vector = {trees[i][0] - last[0], trees[i][1] - last[1]};
                double newCos = cos(benchmark, vector);
                if (abs(newCos - maxCos) < EPSILON) {
                    int dist = distSquare(last, trees[i]);
                    if (dist < minDist) {
                        minDist = dist;
                        next = i;
                    }
                } else if (newCos > maxCos) {
                    maxCos = newCos;
                    minDist = distSquare(last, trees[i]);
                    next = i;
                }
            }
            secondLast = last;
            last = trees[next];
            used[next] = true;
            count++;
        }

        int idx = 0;
        vector<vector<int>> ans(count);
        for (int i = 0; i < length; i++) {
            if (used[i]) {
                ans[idx++] = trees[i];
            }
        }

        return ans;
    }

    double cos(vector<int>& vector1, vector<int>& vector2) {    // 计算向量夹角余弦值
        int multiply = vector1[0] * vector2[0] + vector1[1] * vector2[1];
        double mode1 = sqrt(vector1[0] * vector1[0] + vector1[1] * vector1[1]);
        double mode2 = sqrt(vector2[0] * vector2[0] + vector2[1] * vector2[1]);
        return multiply / (mode1 * mode2);
    }

    int distSquare(vector<int>& vector1, vector<int>& vector2) {    // 计算两点距离的平方
        return (vector1[0] - vector2[0]) * (vector1[0] - vector2[0])
               + (vector1[1] - vector2[1]) * (vector1[1] - vector2[1]);
    }
};
