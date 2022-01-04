//给你一个数组 routes ，表示一系列公交线路，其中每个 routes[i] 表示一条公交线路，第 i 辆公交车将会在上面循环行驶。 
//
// 
// 例如，路线 routes[0] = [1, 5, 7] 表示第 0 辆公交车会一直按序列 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 
//-> ... 这样的车站路线行驶。 
// 
//
// 现在从 source 车站出发（初始时不在公交车上），要前往 target 车站。 期间仅可乘坐公交车。 
//
// 求出 最少乘坐的公交车数量 。如果不可能到达终点车站，返回 -1 。 
//
// 
//
// 示例 1： 
//
// 
//输入：routes = [[1,2,7],[3,6,7]], source = 1, target = 6
//输出：2
//解释：最优策略是先乘坐第一辆公交车到达车站 7 , 然后换乘第二辆公交车到车站 6 。 
// 
//
// 示例 2： 
//
// 
//输入：routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
//输出：-1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= routes.length <= 500. 
// 1 <= routes[i].length <= 10⁵ 
// routes[i] 中的所有值 互不相同 
// sum(routes[i].length) <= 10⁵ 
// 0 <= routes[i][j] < 10⁶ 
// 0 <= source, target < 10⁶ 
// 
// Related Topics 广度优先搜索 数组 哈希表 👍 248 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {  // BFS
        if (source == target) {
            return 0;
        }
        int length = routes.length, ans = 1;
        LinkedList<Integer> container = new LinkedList<>();
        boolean[] visited = new boolean[length], ending = new boolean[length];
        HashSet<Integer>[] next = (HashSet<Integer>[]) new HashSet[length];
        HashMap<Integer, LinkedList<Integer>> reachable = new HashMap<>();
        for (int i = 0; i < length; i++) {
            for (int node : routes[i]) {
                if (!reachable.containsKey(node)) {
                    reachable.put(node, new LinkedList<>());
                }
                reachable.get(node).add(i);
            }
        }
        for (int start : reachable.get(source)) {
            visited[start] = true;
            container.push(start);
        }
        if (!reachable.containsKey(target)) {
            return -1;
        }
        for (int end : reachable.get(target)) {
            ending[end] = true;
            if (visited[end]) {
                return ans;
            }
        }
        for (LinkedList<Integer> v : reachable.values()) {
            for (int node : v) {
                for (int nextNode : v) {
                    if (nextNode != node) {
                        if (next[node] == null) {
                            next[node] = new HashSet<>();
                        }
                        next[node].add(nextNode);
                    }
                }
            }
        }
        while (!container.isEmpty()) {
            ans++;
            for (int i = 0, size = container.size(); i < size; i++) {
                int node = container.pollFirst();
                if (next[node] == null) {
                    continue;
                }
                for (int nextNode : next[node]) {
                    if (visited[nextNode]) {
                        continue;
                    }
                    visited[nextNode] = true;
                    if (ending[nextNode]) {
                        return ans;
                    }
                    container.push(nextNode);
                }
            }
        }
        return -1;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
