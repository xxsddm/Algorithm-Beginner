//在本问题中，有根树指满足以下条件的 有向 图。该树只有一个根节点，所有其他节点都是该根节点的后继。该树除了根节点之外的每一个节点都有且只有一个父节点，而根节
//点没有父节点。 
//
// 输入一个有向图，该图由一个有着 n 个节点（节点值不重复，从 1 到 n）的树及一条附加的有向边构成。附加的边包含在 1 到 n 中的两个不同顶点间，这条
//附加的边不属于树中已存在的边。 
//
// 结果图是一个以边组成的二维数组 edges 。 每个元素是一对 [ui, vi]，用以表示 有向 图中连接顶点 ui 和顶点 vi 的边，其中 ui 是 
//vi 的一个父节点。 
//
// 返回一条能删除的边，使得剩下的图是有 n 个节点的有根树。若有多个答案，返回最后出现在给定二维数组的答案。 
//
// 
//
// 示例 1： 
//
// 
//输入：edges = [[1,2],[1,3],[2,3]]
//输出：[2,3]
// 
//
// 示例 2： 
//
// 
//输入：edges = [[1,2],[2,3],[3,4],[4,1],[1,5]]
//输出：[4,1]
// 
//
// 
//
// 提示： 
//
// 
// n == edges.length 
// 3 <= n <= 1000 
// edges[i].length == 2 
// 1 <= ui, vi <= n 
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集 图 👍 268 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    vector<int> findRedundantDirectedConnection(vector<vector<int>>& edges) {
        // 记录各节点入度
        int n = (int) edges.size(), mark = 0, indegree[n + 1];
        vector<int> container[n + 1];   // 保存从节点出发的有向边的指向
        memset(indegree, 0, sizeof(indegree));
        vector<int> ans;
        for (auto &edge: edges) {
            indegree[edge[1]]++;
            container[edge[0]].push_back(edge[1]);
            if (indegree[edge[1]] == 2) {
                mark = edge[1]; // 记录入度为2的点
            }
        }
        if (mark != 0) {    // 若存在入度为2的点
            // 找到入度为0的点作为根节点
            // 检查删除掉指向入度2的点的某边后,根节点是否可到达入度2的点
            int start = -1;
            for (int i = 1; i <= n; i++) {
                if (indegree[i] == 0) {
                    start = i;
                    break;
                }
            }
            for (int i = n - 1; i >= 0; i--) {
                if (edges[i][1] == mark &&
                    dfs(start, edges[i][0], edges[i][1], container)) {
                    ans = edges[i];
                    break;
                }
            }
        } else {    // 若不存在入度为2的点(入度均为1)
            // 枚举根节点,若删除某边后,该有向边存在反向路径(u->v删除,但存在v->u路径)则该边所指向点可作为根节点
            for (int i = n - 1; i >= 0; i--) {
                if (dfs(edges[i][1], -1, edges[i][0], container)) {
                    ans = edges[i];
                    break;
                }
            }
        }
        return ans;
    }

    // 可以通过禁止ban节点指向end节点,视为删除某边
    bool dfs(int node, int ban, int end, vector<int> *container) {
        for (auto &nextNode: container[node]) {
            if (nextNode == end) {
                if (ban != node) {
                    return true;
                }
                continue;
            }
            if (dfs(nextNode, ban, end, container)) {
                return true;
            }
        }
        return false;
    }
};

//leetcode submit region end(Prohibit modification and deletion)
