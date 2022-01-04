//存在一个由 n 个节点组成的无向连通图，图中的节点按从 0 到 n - 1 编号。 
//
// 给你一个数组 graph 表示这个图。其中，graph[i] 是一个列表，由所有与节点 i 直接相连的节点组成。 
//
// 返回能够访问所有节点的最短路径的长度。你可以在任一节点开始和停止，也可以多次重访节点，并且可以重用边。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 
//输入：graph = [[1,2,3],[0],[0],[0]]
//输出：4
//解释：一种可能的路径为 [1,0,2,0,3] 
//
// 示例 2： 
//
// 
//
// 
//输入：graph = [[1],[0,2,4],[1,3,4],[2],[1,2]]
//输出：4
//解释：一种可能的路径为 [0,1,4,2,3]
// 
//
// 
//
// 提示： 
//
// 
// n == graph.length 
// 1 <= n <= 12 
// 0 <= graph[i].length < n 
// graph[i] 不包含 i 
// 如果 graph[a] 包含 b ，那么 graph[b] 也包含 a 
// 输入的图总是连通图 
// 
// Related Topics 位运算 广度优先搜索 图 动态规划 状态压缩 
// 👍 141 👎 0

import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int shortestPathLength(int[][] graph) {  // BFS状态压缩
        int length = graph.length, allVisited = (1 << length) - 1, count = 0;
        // 当前待考虑路径
        LinkedList<int[]> container = new LinkedList<>();
        // 节点,访问状态 -> 是否访问过
        boolean[][] paths = new boolean[length][1 << length];
        for (int start = 0; start < length; start++) {
            int state = 1 << start;
            container.add(new int[] {start, state});
            paths[start][state] = true;
        }
        outerLoop:
        while (!container.isEmpty()) {
            int size = container.size();
            for (int i = 0; i < size; i++) {
                int[] nodeState = container.pollFirst();
                int node = nodeState[0], state = nodeState[1];
                if (state == allVisited) {
                    break outerLoop;
                }
                for (int nextNode: graph[node]) {
                    int nextState = state | (1 << nextNode);
                    // 不考虑已访问过的相同[节点,状态]组合
                    if (!paths[nextNode][nextState]) {
                        container.add(new int[] {nextNode, nextState});
                        paths[nextNode][nextState] = true;
                    }
                }
            }
            // 每轮结束时自增(起点记0步)
            count++;

        }
        return count;
    }
}

//leetcode submit region end(Prohibit modification and deletion)

class Solution {
    public int shortestPathLength(int[][] graph) {
        Queue<int[]> container = new LinkedList<>();
        HashSet<int[]> node_visited = new HashSet<>();    // 用hashset会超时, 可能添加元素计算hash值和索引太慢

        for (int i = 0; i < graph.length; i++){
            node_visited.add(new int[]{i, 1 << i});
            container.offer(new int[]{i, 1 << i, 0});
        }

        while (!container.isEmpty()){
            int[] temp = container.poll();
            int prev = temp[0];

            for (int next: graph[prev]){
                int visited = temp[1] | 1 << next;
                if (visited == (1 << graph.length) - 1)    return temp[2] + 1;
                int[] judge = new int[]{next, visited};
                if (!node_visited.contains(judge)){
                    node_visited.add(judge);
                    container.offer(new int[]{next, visited, temp[2] + 1});
                }
            }
        }
        return 0;
    }
}
