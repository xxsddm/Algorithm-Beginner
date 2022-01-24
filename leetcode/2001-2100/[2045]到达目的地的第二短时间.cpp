//城市用一个 双向连通 图表示，图中有 n 个节点，从 1 到 n 编号（包含 1 和 n）。图中的边用一个二维整数数组 edges 表示，其中每个
//edges[i] = [ui, vi] 表示一条节点 ui 和节点 vi 之间的双向连通边。每组节点对由 最多一条 边连通，顶点不存在连接到自身的边。穿过任意一条边的时
//间是 time 分钟。
//
// 每个节点都有一个交通信号灯，每 change 分钟改变一次，从绿色变成红色，再由红色变成绿色，循环往复。所有信号灯都 同时 改变。你可以在 任何时候 进入
//某个节点，但是 只能 在节点 信号灯是绿色时 才能离开。如果信号灯是 绿色 ，你 不能 在节点等待，必须离开。
//
// 第二小的值 是 严格大于 最小值的所有值中最小的值。
//
//
// 例如，[2, 3, 4] 中第二小的值是 3 ，而 [2, 2, 4] 中第二小的值是 4 。
//
//
// 给你 n、edges、time 和 change ，返回从节点 1 到节点 n 需要的 第二短时间 。
//
// 注意：
//
//
// 你可以 任意次 穿过任意顶点，包括 1 和 n 。
// 你可以假设在 启程时 ，所有信号灯刚刚变成 绿色 。
//
//
//
//
// 示例 1：
//
//        
//
//
//输入：n = 5, edges = [[1,2],[1,3],[1,4],[3,4],[4,5]], time = 3, change = 5
//输出：13
//解释：
//上面的左图展现了给出的城市交通图。
//右图中的蓝色路径是最短时间路径。
//花费的时间是：
//- 从节点 1 开始，总花费时间=0
//- 1 -> 4：3 分钟，总花费时间=3
//- 4 -> 5：3 分钟，总花费时间=6
//因此需要的最小时间是 6 分钟。
//
//右图中的红色路径是第二短时间路径。
//- 从节点 1 开始，总花费时间=0
//- 1 -> 3：3 分钟，总花费时间=3
//- 3 -> 4：3 分钟，总花费时间=6
//- 在节点 4 等待 4 分钟，总花费时间=10
//- 4 -> 5：3 分钟，总花费时间=13
//因此第二短时间是 13 分钟。
//
//
// 示例 2：
//
//
//
//
//输入：n = 2, edges = [[1,2]], time = 3, change = 2
//输出：11
//解释：
//最短时间路径是 1 -> 2 ，总花费时间 = 3 分钟
//最短时间路径是 1 -> 2 -> 1 -> 2 ，总花费时间 = 11 分钟
//
//
//
// 提示：
//
//
// 2 <= n <= 10⁴
// n - 1 <= edges.length <= min(2 * 10⁴, n * (n - 1) / 2)
// edges[i].length == 2
// 1 <= ui, vi <= n
// ui != vi
// 不含重复边
// 每个节点都可以从其他节点直接或者间接到达
// 1 <= time, change <= 10³
//
// Related Topics 广度优先搜索 图 数组 最短路 👍 16 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int secondMinimum(int n, vector<vector<int>>& edges, int time, int change) {
        int steps = 0;
        // 是否之前已访问过n
        bool reach = false;
        vector<int> container[n + 1];
        queue<int> cur;
        // 访问1, 2次的点
        bool first[n + 1], second[n + 1];
        memset(first, false, sizeof(first));
        memset(second, false, sizeof(second));
        for (vector<int> &edge : edges) {
            int node1 = edge[0], node2 = edge[1];
            container[node1].push_back(node2);
            container[node2].push_back(node1);
        }
        cur.push(1);
        while (true) {
            steps++;
            // 记录本轮已访问节点
            bool visited[n + 1];
            memset(visited, false, sizeof(visited));
            for (int i = 0, size = (int) cur.size(); i < size; i++) {
                int prev = cur.front();
                cur.pop();
                for (int &next : container[prev]) {
                    // 同一轮抵达相同位置或已访问两次的,可以省略
                    if (!visited[next] && !second[next]) {
                        if (next == n) {
                            if (!reach) {
                                reach = true;
                            }
                            else {
                                return count(steps, time, change);
                            }
                        }
                        (first[next] ? second[next] : first[next]) = true;
                        visited[next] = true;
                        cur.push(next);
                    }
                }
            }
        }
    }

    // 根据已走步数计算所需时间
    int count(int steps, int time, int change) {
        int ans = 0, delta = change << 1;
        int lower = change, upper = lower + change - 1;
        for (int i = 0; i < steps - 1; i++) {
            ans += time;
            while (ans > upper) {
                lower += max((ans - upper) / delta, 1) * delta;
                upper = lower + change - 1;
            }
            if (ans >= lower && ans <= upper) {
                ans += upper + 1 - ans;
                lower += delta;
                upper += delta;
            }
        }
        ans += time;
        return ans;
    }
};

//leetcode submit region end(Prohibit modification and deletion)
