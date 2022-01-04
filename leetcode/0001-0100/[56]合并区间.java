//以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返
//回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。 
//
// 
//
// 示例 1： 
//
// 
//输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
//输出：[[1,6],[8,10],[15,18]]
//解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
// 
//
// 示例 2： 
//
// 
//输入：intervals = [[1,4],[4,5]]
//输出：[[1,5]]
//解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。 
//
// 
//
// 提示： 
//
// 
// 1 <= intervals.length <= 10⁴ 
// intervals[i].length == 2 
// 0 <= starti <= endi <= 10⁴ 
// 
// Related Topics 数组 排序 👍 1069 👎 0


import java.util.Arrays;
import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> (o1[0] - o2[0]));
        LinkedList<int[]> container = new LinkedList<>();
        int idx = 0;
        while (idx < intervals.length) {
            int leftlimit = intervals[idx][0], rightlimit = intervals[idx][1];
            idx++;
            while (idx < intervals.length && rightlimit >= intervals[idx][0]) {
                rightlimit = Math.max(rightlimit, intervals[idx][1]);   // 可以合并则考虑更新右边界
                idx++;
            }
            container.add(new int[] {leftlimit, rightlimit});
        }
        return container.toArray(new int[container.size()][2]);
    }
}

//leetcode submit region end(Prohibit modification and deletion)
