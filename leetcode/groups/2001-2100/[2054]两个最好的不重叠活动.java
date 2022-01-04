//给你一个下标从 0 开始的二维整数数组 events ，其中 events[i] = [startTimei, endTimei, valuei] 。第 
//i 个活动开始于 startTimei ，结束于 endTimei ，如果你参加这个活动，那么你可以得到价值 valuei 。你 最多 可以参加 两个时间不重叠 
//活动，使得它们的价值之和 最大 。 
//
// 请你返回价值之和的 最大值 。 
//
// 注意，活动的开始时间和结束时间是 包括 在活动时间内的，也就是说，你不能参加两个活动且它们之一的开始时间等于另一个活动的结束时间。更具体的，如果你参加一个
//活动，且结束时间为 t ，那么下一个活动必须在 t + 1 或之后的时间开始。 
//
// 
//
// 示例 1: 
//
// 
//
// 输入：events = [[1,3,2],[4,5,2],[2,4,3]]
//输出：4
//解释：选择绿色的活动 0 和 1 ，价值之和为 2 + 2 = 4 。
// 
//
// 示例 2： 
//
// 
//
// 输入：events = [[1,3,2],[4,5,2],[1,5,5]]
//输出：5
//解释：选择活动 2 ，价值和为 5 。
// 
//
// 示例 3： 
//
// 
//
// 输入：events = [[1,5,3],[1,5,1],[6,6,5]]
//输出：8
//解释：选择活动 0 和 2 ，价值之和为 3 + 5 = 8 。 
//
// 
//
// 提示： 
//
// 
// 2 <= events.length <= 10⁵ 
// events[i].length == 3 
// 1 <= startTimei <= endTimei <= 10⁹ 
// 1 <= valuei <= 10⁶ 
// 
// 👍 9 👎 0

import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxTwoEvents(int[][] events) {
        // maxFirst:当前已可作为第一个的event中最高价值, idx:考虑作为第二个的event的索引
        int ans = 0, maxFirst = 0, idx = 0, length = events.length;
        Arrays.sort(events, (event1, event2) -> {
            if (event1[0] == event2[0]) {
                return event1[1] - event2[1];
            }
            return event1[0] - event2[0];
        });
        // container保存当前尚未作为第一个的event
        PriorityQueue<int[]> container = new PriorityQueue<>(
                (event1, event2) -> (event1[1] - event2[1]));
        for (int[] event: events) {
            container.add(event);
        }
        while (idx < length) {
            // 当前container剩余event均不可作为第一个event,只可使用之前已可作为第一个的event
            while (idx < length && events[idx][0] <= container.peek()[1]) {
                ans = Math.max(ans, maxFirst + events[idx++][2]);
            }
            if (idx == length) {
                break;
            }
            // 从idx往后可以考虑更多的第一个event,则更新maxFirst
            while (events[idx][0] > container.peek()[1]) {
                maxFirst = Math.max(maxFirst, container.poll()[2]);
            }
            ans = Math.max(ans, maxFirst + events[idx][2]);
        }
        return ans;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
