//给你一张 无向 图，图中有 n 个节点，节点编号从 0 到 n - 1 （都包括）。同时给你一个下标从 0 开始的整数数组 values ，其中 
//values[i] 是第 i 个节点的 价值 。同时给你一个下标从 0 开始的二维整数数组 edges ，其中 edges[j] = [uj, vj, timej] 表示
//节点 uj 和 vj 之间有一条需要 timej 秒才能通过的无向边。最后，给你一个整数 maxTime 。 
//
// 合法路径 指的是图中任意一条从节点 0 开始，最终回到节点 0 ，且花费的总时间 不超过 maxTime 秒的一条路径。你可以访问一个节点任意次。一条合法
//路径的 价值 定义为路径中 不同节点 的价值 之和 （每个节点的价值 至多 算入价值总和中一次）。 
//
// 请你返回一条合法路径的 最大 价值。 
//
// 注意：每个节点 至多 有 四条 边与之相连。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：values = [0,32,10,43], edges = [[0,1,10],[1,2,15],[0,3,10]], maxTime = 49
//输出：75
//解释：
//一条可能的路径为：0 -> 1 -> 0 -> 3 -> 0 。总花费时间为 10 + 10 + 10 + 10 = 40 <= 49 。
//访问过的节点为 0 ，1 和 3 ，最大路径价值为 0 + 32 + 43 = 75 。
// 
//
// 示例 2： 
//
// 
//
// 
//输入：values = [5,10,15,20], edges = [[0,1,10],[1,2,10],[0,3,10]], maxTime = 30
//输出：25
//解释：
//一条可能的路径为：0 -> 3 -> 0 。总花费时间为 10 + 10 = 20 <= 30 。
//访问过的节点为 0 和 3 ，最大路径价值为 5 + 20 = 25 。
// 
//
// 示例 3： 
//
// 
//
// 
//输入：values = [1,2,3,4], edges = [[0,1,10],[1,2,11],[2,3,12],[1,3,13]], maxTime 
//= 50
//输出：7
//解释：
//一条可能的路径为：0 -> 1 -> 3 -> 1 -> 0 。总花费时间为 10 + 13 + 13 + 10 = 46 <= 50 。
//访问过的节点为 0 ，1 和 3 ，最大路径价值为 1 + 2 + 4 = 7 。 
//
// 示例 4： 
//
// 
//
// 
//输入：values = [0,1,2], edges = [[1,2,10]], maxTime = 10
//输出：0
//解释：
//唯一一条路径为 0 。总花费时间为 0 。
//唯一访问过的节点为 0 ，最大路径价值为 0 。
// 
//
// 
//
// 提示： 
//
// 
// n == values.length 
// 1 <= n <= 1000 
// 0 <= values[i] <= 10⁸ 
// 0 <= edges.length <= 2000 
// edges[j].length == 3 
// 0 <= uj < vj <= n - 1 
// 10 <= timej, maxTime <= 100 
// [uj, vj] 所有节点对 互不相同 。 
// 每个节点 至多有四条 边。 
// 图可能不连通。 
// 
// 👍 12 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int ans = 0, limit;
    // visited保存每个节点访问状态
    // 0:未访问, 1:访问一次, 2:访问两次及以上
    vector<int> values, visited;
    vector<vector<pair<int, int>>> next;

    int maximalPathQuality(vector<int>& values, vector<vector<int>>& edges, int maxTime) {
        int length = (int) values.size();
        this->values = values;
        limit = maxTime;
        visited = vector<int> (length);
        next = vector<vector<pair<int, int>>> (length);
        for (auto &edge: edges) {
            next[edge[0]].push_back(pair<int, int> {edge[1], edge[2]});
            next[edge[1]].push_back(pair<int, int> {edge[0], edge[2]});
        }
        if (next[0].empty()) {
            return values[0];
        }
        visited[0] = 1;
        backtrack(0, 0, values[0]);
        return ans;
    }

    // node:当前节点, cumtime:当前总耗时, cumvalue:当前总价值
    void backtrack(int node, int cumtime, int cumvalue) {
        if (node == 0) {
            ans = max(ans, cumvalue);
        }
        for (auto &pair: next[node]) {
            int nextNode = pair.first, cost = pair.second;
            // 从nextNode返回过node,不会继续走该路径
            if (visited[node] == 2 && visited[nextNode] == 2 || cumtime + cost > limit) {
                continue;
            }
            bool nextPlus = false, plus = false;
            if (visited[nextNode] == 1 && visited[node] == 1) {
                // 若在当前node原路返回,则排除后面继续该路径的可能,node记为2(避免重复nextNode->node)
                visited[nextNode]++;
                visited[node]++;
                nextPlus = true;
                plus = true;
            } else if (visited[nextNode] < 2) {
                // 若nextNode访问不足2次
                visited[nextNode]++;
                nextPlus = true;
            } else {
                // 若nextNode已访问2次,当前node也记为2次(避免重复nextNode->node)
                visited[node]++;
                plus = true;
            }
            backtrack(nextNode,
                      cumtime + cost,
                      cumvalue + (visited[nextNode] == 1 ? values[nextNode] : 0));
            // 撤销操作
            if (nextPlus) {
                visited[nextNode]--;
            }
            if (plus) {
                visited[node]--;
            }
        }
    }
};

//leetcode submit region end(Prohibit modification and deletion)
