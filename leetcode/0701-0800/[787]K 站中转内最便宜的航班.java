//有 n 个城市通过一些航班连接。给你一个数组 flights ，其中 flights[i] = [fromi, toi, pricei] ，表示该航班都从城
//市 fromi 开始，以价格 toi 抵达 pricei。 
//
// 现在给定所有的城市和航班，以及出发城市 src 和目的地 dst，你的任务是找到出一条最多经过 k 站中转的路线，使得从 src 到 dst 的 价格最便
//宜 ，并返回该价格。 如果不存在这样的路线，则输出 -1。 
//
// 
//
// 示例 1： 
//
// 
//输入: 
//n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
//src = 0, dst = 2, k = 1
//输出: 200
//解释: 
//城市航班图如下
//
//
//从城市 0 到城市 2 在 1 站中转以内的最便宜价格是 200，如图中红色所示。 
//
// 示例 2： 
//
// 
//输入: 
//n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
//src = 0, dst = 2, k = 0
//输出: 500
//解释: 
//城市航班图如下
//
//
//从城市 0 到城市 2 在 0 站中转以内的最便宜价格是 500，如图中蓝色所示。 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 100 
// 0 <= flights.length <= (n * (n - 1) / 2) 
// flights[i].length == 3 
// 0 <= fromi, toi < n 
// fromi != toi 
// 1 <= pricei <= 10⁴ 
// 航班没有重复，且不存在自环 
// 0 <= src, dst, k < n 
// src != dst 
// 
// Related Topics 深度优先搜索 广度优先搜索 图 动态规划 最短路 堆（优先队列） 👍 297 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[] cost = new int[n];        // 每一步相应消费状态(最多走k+1步)
        int ans = 100000;       // Integer.MAX_VALUE会溢出, 导致负数
        for (int i = 0; i < n; i++) {
            cost[i] = 100000;
        }
        cost[src] = 0;      // 出发点src(此时走了0步)
        for (int i = 0; i < k + 1; i++) {
            int[] update = cost.clone();
            for (int[] flight: flights) {
                int start = flight[0], end = flight[1], price = flight[2];
                update[end] = Math.min(update[end], cost[start] + price);
            }
            ans = Math.min(ans, update[dst]);
            cost = update;
        }
        return ans != 100000 ? ans : -1;
    }
}

//leetcode submit region end(Prohibit modification and deletion)


class Solution {
    int[][] fromTo;
    int dst;
    int n;
    int price = 0;
    int cost = -1;
    boolean[] used;

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {     // DFS, 很慢
        fromTo = new int[n][n];
        boolean impossible = true;
        for (int[] flight: flights) {
            fromTo[flight[0]][flight[1]] = flight[2];
            if (flight[1] == dst) {
                impossible = false;
            }
        }
        if (impossible) {
            return -1;
        }
        this.dst = dst;
        this.n = n;
        used = new boolean[n];
        used[src] = true;
        backtrack(src, k);
        return cost;
    }

    private void backtrack(int start, int rest) {
        if (cost != -1 && price >= cost) {      // 价格不低于已有水平
            return;
        }
        if (start == dst) {                     // 更新价格
            cost = price;
            return;
        }
        if (rest == 0) {                        // 中转站次数不足
            if (fromTo[start][dst] != 0) {
                price += fromTo[start][dst];
                backtrack(dst, 0);
                price -= fromTo[start][dst];
            }
            return;
        }

        for (int end = 0; end < n; end++) {
            int temp = fromTo[start][end];
            if (temp == 0 || used[end]) {
                continue;
            }
            price += temp;
            used[end] = true;
            backtrack(end, rest - 1);
            price -= temp;
            used[end] = false;
        }
    }
}
