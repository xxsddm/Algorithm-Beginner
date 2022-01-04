//有 n 个网络节点，标记为 1 到 n。 
//
// 给你一个列表 times，表示信号经过 有向 边的传递时间。 times[i] = (ui, vi, wi)，其中 ui 是源节点，vi 是目标节点， w
//i 是一个信号从源节点传递到目标节点的时间。 
//
// 现在，从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1 。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：times = [[1,2,1]], n = 2, k = 1
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：times = [[1,2,1]], n = 2, k = 2
//输出：-1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= k <= n <= 100 
// 1 <= times.length <= 6000 
// times[i].length == 3 
// 1 <= ui, vi <= n 
// ui != vi 
// 0 <= wi <= 100 
// 所有 (ui, vi) 对都 互不相同（即，不含重复边） 
// 
// Related Topics 深度优先搜索 广度优先搜索 图 最短路 堆（优先队列） 
// 👍 298 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        int[][] timematrix = new int[n][n];
        for (int[] row: timematrix) Arrays.fill(row, -1);
        for (int[] time : times) timematrix[time[0] - 1][time[1] - 1] = time[2];
        int[] timearray = new int[n];
        Arrays.fill(timearray, -1);
        for (int end = 0; end < n; end++) timearray[end] = timematrix[k - 1][end];
        timearray[k - 1] = 0;
        boolean[] visited = new boolean[n];
        visited[k - 1] = true;
        int minIndex = 0, maxtime = 0, temp;

        for (int count = 0; count < n - 1; count++) {
            int mintimearray = Integer.MAX_VALUE;

            // 用优先队列会更简单, 但优先队列没有更新队列中数值的方法, 不会快很多
            for (int end = 0; end < n; end++) {
                if (!visited[end] && timearray[end] != -1 && timearray[end] < mintimearray) {
                    mintimearray = timearray[end];
                    minIndex = end;
                }
            }

            visited[minIndex] = true;

            for (int end = 0; end < n; end++) {
                if (visited[end]) continue;
                temp = timematrix[minIndex][end];
                if (temp != -1) {
                    if (timearray[end] == -1) {
                        timearray[end] = timearray[minIndex] + temp;
                    }
                    else if (timearray[minIndex] + temp < timearray[end]) {
                        timearray[end] = timearray[minIndex] + temp;
                    }
                }
            }
        }

        for (int time: timearray) {
            if (time == -1) return -1;
            else if (time > maxtime) maxtime = time;
        }

        return maxtime;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
