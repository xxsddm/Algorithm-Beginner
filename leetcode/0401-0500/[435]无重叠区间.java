//给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。 
//
// 注意: 
//
// 
// 可以认为区间的终点总是大于它的起点。 
// 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。 
// 
//
// 示例 1: 
//
// 
//输入: [ [1,2], [2,3], [3,4], [1,3] ]
//
//输出: 1
//
//解释: 移除 [1,3] 后，剩下的区间没有重叠。
// 
//
// 示例 2: 
//
// 
//输入: [ [1,2], [1,2], [1,2] ]
//
//输出: 2
//
//解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
// 
//
// 示例 3: 
//
// 
//输入: [ [1,2], [2,3] ]
//
//输出: 0
//
//解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
// 
// Related Topics 贪心 数组 动态规划 排序 👍 477 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, ((o1, o2) -> (o1[0] - o2[0])));
        int rightlimit = Integer.MIN_VALUE, count = 0;
        for (int[] interval: intervals) {
            if (interval[0] < rightlimit) {     // 有交集, 需删除新区间
                count++;
                rightlimit = Math.min(rightlimit, interval[1]);     // 删除交叉的区间, 可能删原有区间也可能删新区间
            }
            else {
                rightlimit = interval[1];       // 无交集则更新边界为新加入区间右边界
            }
        }
        return count;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
